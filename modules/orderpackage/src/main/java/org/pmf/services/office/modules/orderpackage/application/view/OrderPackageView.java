package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.core.value_object.Money;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPackageView implements View {

    private String orderPackageCode;
    private UUID guid;
    private UUID correlationId;
    private String providerRelation;
    private String company;
    private Money total;
    private Money subTotal;
    private Long version;
    private List<OrderView> orders;
    private Map<String, Object> metadata;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public OrderPackageView withGuid(UUID id) {
        this.guid = id;
        return this;
    }

    public OrderPackageView withCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public OrderPackageView withOrderPackageCode(Integer orderPackageCode) {
        this.orderPackageCode = "OP-" + orderPackageCode;
        return this;
    }

    public OrderPackageView withCompany(String company) {
        this.company = company;
        return this;
    }

    public OrderPackageView withProviderRelation(String providerRelation) {
        this.providerRelation = providerRelation;
        return this;
    }

    public OrderPackageView withTotal(Money total) {
        this.total = total;
        return this;
    }

    public OrderPackageView withSubTotal(Money subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public OrderPackageView withVersion(Long version) {
        this.version = version;
        return this;
    }

    public OrderPackageView withOrders(List<OrderView> orders) {
        this.orders = orders;
        return this;
    }

    public OrderPackageView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public OrderPackageView withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrderPackageView withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getGuid() {
        return guid;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public String getOrderPackageCode() {
        return orderPackageCode;
    }

    public String getCompany() {
        return company;
    }

    public String getProviderRelation() {
        return providerRelation;
    }

    public Money getTotal() {
        return total;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public Long getVersion() {
        return version;
    }

    public List<OrderView> getOrders() {
        return orders;
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
