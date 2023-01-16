package org.pmf.services.office.modules.orderpackage.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.pmf.services.office.core.value_object.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity(name = "Item")
@Table(name = "\"item\"", schema = "office_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Audited
public class Item {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    @Column(name = "item_code")
    private String itemCode;

    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Column(columnDefinition = "json", name = "price_unit")
    @Type(type = "json")
    private Money priceUnit;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<Adjustment> adjustments;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> metadata;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public Item() {
        this.id = UUID.randomUUID();
        this.itemCode = null;
        this.quantity = 1;
        this.priceUnit = new Money();
        this.metadata = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public Item(
            String name,
            String itemCode,
            Money priceUnit,
            int quantity,
            List<Adjustment> adjustments
    ) {
        this();
        this.name = name;
        this.itemCode = itemCode;
        this.priceUnit = priceUnit;
        this.quantity = quantity;
        this.adjustments = adjustments;
    }

    public Item(
            String name,
            String itemCode,
            Money priceUnit,
            int quantity,
            List<Adjustment> adjustments,
            Map<String, Object> metadata
    ) {
        this(name, itemCode, priceUnit, quantity, adjustments);
        this.metadata = metadata;
    }

    public UUID getId() {
        return id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public Money getPriceUnit() {
        return priceUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return new Money(quantity * priceUnit.getAmount());
    }

    public Money getTotal() {
        return new Money(getSubTotal().getAmount() + getTotalAdjustments().getAmount());
    }

    /**
     * Total de incentivos hoje não são calculados como receita
     */
    public Money getTotalAdjustments() {
        var sum = new Money(0);
        this.adjustments
                .stream()
                .filter(a -> a.getType() != AdjustmentType.INCENTIVE)
                .forEach(a -> sum.sum(a.getTotal().getAmount()));
        return sum;
    }

    public List<Adjustment> getAdjustments() {
        return adjustments;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }

        Item candidate = (Item) obj;
        return this.id.equals(candidate.getId())
                && this.name.equals(candidate.getName())
                && this.priceUnit.equals(candidate.getPriceUnit())
                && this.adjustments.equals(candidate.getAdjustments());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
