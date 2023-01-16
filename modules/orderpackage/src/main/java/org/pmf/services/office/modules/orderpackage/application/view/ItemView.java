package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.core.value_object.Money;

import java.time.LocalDateTime;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemView {

    private UUID id;
    private String name;
    private String itemCode;
    private Money priceUnit;
    private Money total;
    private Money subTotal;
    private int quantity;
    private List<AdjustmentView> adjustments = new ArrayList<>();
    private Map<String, Object> metadata = new HashMap<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public ItemView withId(UUID id) {
        this.id = id;
        return this;
    }

    public ItemView withName(String name) {
        this.name = name;
        return this;
    }

    public ItemView withItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public ItemView withPriceUnit(Money priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public ItemView withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemView withTotal(Money total) {
        this.total = total;
        return this;
    }

    public ItemView withSubtotal(Money subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public ItemView withAdjustments(List<AdjustmentView> adjustments) {
        this.adjustments = adjustments;
        return this;
    }

    public ItemView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public ItemView withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ItemView withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getId() {
        return id;
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

    public Money getTotal() {
        return total;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<AdjustmentView> getAdjustments() {
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
