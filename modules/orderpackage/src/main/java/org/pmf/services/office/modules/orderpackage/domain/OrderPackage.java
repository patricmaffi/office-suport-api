package org.pmf.services.office.modules.orderpackage.domain;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.pmf.services.office.core.value_object.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "OrderPackage")
@Table(name = "`order_package`", schema = "office_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Audited
public class OrderPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_package_code")
    private Integer orderPackageCode;

    @Column(columnDefinition = "BINARY(16)")
    private UUID guid;

    @Column(columnDefinition = "BINARY(16)", name = "correlation_id")
    private UUID correlationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_relation")
    private ProviderRelation providerRelation;

    @Version
    private Long version;

    @Column(nullable = false)
    private String company;

    @OneToMany(mappedBy = "orderPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> metadata;

    @CreationTimestamp
    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public OrderPackage() {
        this.orderPackageCode = 0;
        this.guid = UUID.randomUUID();
        this.version = 0L;
        this.orders = new ArrayList<>();
        this.metadata = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public OrderPackage(String company, UUID correlationId, ProviderRelation providerRelation) {
        this();
        this.company = company;
        this.correlationId = correlationId;
        this.providerRelation = providerRelation;
    }

    public OrderPackage(String company, UUID correlationId, ProviderRelation providerRelation, Map<String, Object> metadata) {
        this(company, correlationId, providerRelation);
        this.metadata = metadata;
    }

    public Integer getOrderPackageCode() {
        return orderPackageCode;
    }

    public UUID getGuid() {
        return guid;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public ProviderRelation getProviderRelation() {
        return providerRelation;
    }

    public Long getVersion() {
        return version;
    }

    public String getCompany() {
        return company;
    }

    public List<Order> orders() {
        return orders;
    }

    public Map<String, Object> metadata() {
        return metadata;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setOrderPackage(this);
    }

    private List<Order> sells() {
        return this.orders
                .stream()
                .filter((Order o) -> o.getType() == OrderType.SALE)
                .collect(Collectors.toList());
    }

    private List<Order> purchases() {
        return this.orders
                .stream()
                .filter((Order o) -> o.getType() == OrderType.PURCHASE)
                .collect(Collectors.toList());
    }

    private List<Order> purchasesCancellation() {
        return this.orders
                .stream()
                .filter((Order o) -> o.getType() == OrderType.PURCHASE_CANCELLATION)
                .collect(Collectors.toList());
    }

    private List<Order> sellsCancellation() {
        return this.orders
                .stream()
                .filter((Order o) -> o.getType() == OrderType.SALE_CANCELLATION)
                .collect(Collectors.toList());
    }

    public Money totalSell() {
        var total = new Money(0);
        this.sellsCancellation().forEach((Order o) -> total.sum(o.getTotal()));
        if(total.getAmount() > 0) return total;
        this.sells().forEach((Order o) -> total.sum(o.getTotal()));

        return total;
    }

    public Money totalPurchase() {
        var total = new Money(0);
        this.purchasesCancellation().forEach((Order o) -> total.sum(o.getTotal()));
        if(!this.purchasesCancellation().isEmpty()) return total;
        this.purchases().forEach((Order o) -> total.sum(o.getTotal()));
        return total;
    }

    public Money total() {
        return Money.from(totalSell().getAmount() - totalPurchase().getAmount());
    }

    public Money subTotalSell() {
        var total = new Money(0);
        this.sellsCancellation().forEach((Order o) -> total.sum(o.getTotal()));
        if(total.getAmount() > 0) return total;
        this.sells().forEach((Order o) -> total.sum(o.getSubTotal()));
        return total;
    }

    public Money subTotalPurchase() {
        var total = new Money(0);
        this.purchasesCancellation().forEach((Order o) -> total.sum(o.getTotal()));
        if(!this.purchasesCancellation().isEmpty()) return total;
        this.purchases().forEach((Order o) -> total.sum(o.getSubTotal()));
        return total;
    }

    public Money subTotal() {
        return Money.from(subTotalSell().getAmount() - subTotalPurchase().getAmount());
    }

    public Money totalAdjustments() {
        var total = new Money(0);
        this.orders().forEach((Order o) -> total.sum(o.getTotalAdjustments()));
        return total;
    }
}
