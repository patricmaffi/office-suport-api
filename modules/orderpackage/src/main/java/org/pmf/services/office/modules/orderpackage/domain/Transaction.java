package org.pmf.services.office.modules.orderpackage.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "Transaction")
@Table(name = "\"transaction\"", schema = "office_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Audited
public class Transaction {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Column(columnDefinition = "json", name = "payment_method")
    @Type(type = "json")
    private PaymentMethod paymentMethod;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private Money total;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private EntityInformation payer;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private EntityInformation payee;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

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

    public Transaction() {
        this.id = UUID.randomUUID();
        this.adjustments = new ArrayList<>();
        this.status = TransactionStatus.COMPLETED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.metadata = new HashMap<>();
    }

    public Transaction(
            PaymentMethod paymentMethod,
            Money total,
            EntityInformation payer,
            EntityInformation payee,
            List<Adjustment> adjustments,
            Map<String, Object> metadata
    ) {
        this();
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.payer = payer;
        this.payee = payee;
        this.adjustments = adjustments;
        this.metadata = metadata;
    }

    public UUID getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Money getTotal() {
        return total;
    }

    public EntityInformation getPayer() {
        return payer;
    }

    public EntityInformation getPayee() {
        return payee;
    }

    public TransactionStatus getStatus() {
        return status;
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
}
