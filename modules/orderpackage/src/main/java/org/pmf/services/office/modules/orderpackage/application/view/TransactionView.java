package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.TransactionStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionView {
    private UUID id;
    private PaymentMethod paymentMethod;
    private List<AdjustmentView> adjustments;
    private Money total;
    private EntityInformationView payer;
    private EntityInformationView payee;
    private TransactionStatus status;
    private Map<String, Object> metadata;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public TransactionView withId(UUID id) {
        this.id = id;
        return this;
    }

    public TransactionView withPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public TransactionView withAdjustments(List<AdjustmentView> adjustments) {
        this.adjustments = adjustments;
        return this;
    }

    public TransactionView withTotal(Money total) {
        this.total = total;
        return this;
    }

    public TransactionView withBuyer(EntityInformationView payer) {
        this.payer = payer;
        return this;
    }

    public TransactionView withSeller(EntityInformationView payee) {
        this.payee = payee;
        return this;
    }

    public TransactionView withStatus(TransactionStatus status) {
        this.status = status;
        return this;
    }

    public TransactionView withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public TransactionView withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public TransactionView withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Money getTotal() {
        return total;
    }

    public List<AdjustmentView> getAdjustments() {
        return adjustments;
    }

    public EntityInformationView getPayer() {
        return payer;
    }

    public EntityInformationView getPayee() {
        return payee;
    }

    public TransactionStatus getStatus() {
        return status;
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
