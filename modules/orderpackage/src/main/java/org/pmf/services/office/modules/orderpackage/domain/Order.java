package org.pmf.services.office.modules.orderpackage.domain;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.*;
import org.hibernate.envers.Audited;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "Order")
@Table(name = "\"order\"", schema = "office_service")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Audited
public class Order {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderpackage_id")
    private OrderPackage orderPackage;

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private EntityInformation customer;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private EntityInformation merchant;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> metadata;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public Order() {
        this.id = UUID.randomUUID();
        this.items = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.metadata = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public Order(OrderType type) {
        this();
        this.type = type;
    }

    public Order(OrderType type, Map<String, Object> metadata) {
        this(type);
        this.metadata = metadata;
    }

    public UUID getId() {
        return id;
    }

    public OrderPackage getOrderPackage() {
        return orderPackage;
    }

    public void setOrderPackage(OrderPackage orderPackage) {
        this.orderPackage = orderPackage;
    }

    public OrderType getType() {
        return type;
    }

    public EntityInformation getCustomer() {
        return customer;
    }

    public void setCustomer(EntityInformation entity) {
        this.customer = entity;
    }

    public EntityInformation getMerchant() {
        return merchant;
    }

    public void setMerchant(EntityInformation entity) {
        this.merchant = entity;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setOrder(null);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setOrder(this);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Transaction> getTransactions() {
        return transactions;
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

    public Money getTotal() {
        var total = new Money(0);
        this.items.forEach(i -> total.sum(i.getTotal().getAmount()));
        return total;
    }

    public Money getSubTotal() {
        var subTotal = new Money(0);
        items.forEach(i -> subTotal.sum(i.getSubTotal().getAmount()));
        return subTotal;
    }

    public Money getTotalAdjustments() {
        var subTotal = new Money(0);
        this.items.forEach(i -> subTotal.sum(i.getTotalAdjustments().getAmount()));
        return subTotal;
    }
}
