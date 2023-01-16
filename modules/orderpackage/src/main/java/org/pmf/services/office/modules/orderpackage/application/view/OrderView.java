package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.OrderType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderView implements View {

    private UUID id;
    private OrderType type;
    private EntityInformationView customer;
    private EntityInformationView merchant;
    private Money subTotal;
    private Money total;
    private Money totalAdjustments;
    private List<ItemView> items;
    private List<TransactionView> transactions;
    private Map<String, Object> metadata;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;

    public OrderView withId(UUID id) {
        this.id = id;
        return this;
    }

    public OrderView withType(OrderType type) {
        this.type = type;
        return this;
    }

    public OrderView withCustomer(EntityInformationView customer) {
        this.customer = customer;
        return this;
    }

    public OrderView withMerchant(EntityInformationView merchant) {
        this.merchant = merchant;
        return this;
    }

    public OrderView withItems(List<ItemView> items) {
        this.items = items;
        return this;
    }

    public OrderView withSubTotal(Money subtotal) {
        this.subTotal = subtotal;
        return this;
    }

    public OrderView withTotal(Money total) {
        this.total = total;
        return this;
    }

    public OrderView withTotalAdjustments(Money totalAdjustments) {
        this.totalAdjustments = totalAdjustments;
        return this;
    }

    public OrderView withTransactions(List<TransactionView> transactions) {
        this.transactions = transactions;
        return this;
    }

    public OrderView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public OrderView withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrderView withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public OrderType getType() {
        return type;
    }

    public EntityInformationView getCustomer() {
        return customer;
    }

    public EntityInformationView getMerchant() {
        return merchant;
    }

    public List<ItemView> getItems() {
        return items;
    }

    public List<TransactionView> getTransactions() {
        return transactions;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public Money getTotal() {
        return total;
    }

    public Money getTotalAdjustments() {
        return totalAdjustments;
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
