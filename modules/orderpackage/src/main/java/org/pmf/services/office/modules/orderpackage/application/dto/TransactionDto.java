package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class TransactionDto {

    @NotNull
    @Valid
    private final PaymentMethodDto paymentMethod;

    @NotNull
    @Valid
    private final Money total;

    @NotNull
    @Valid
    private final EntityInformationDto payer;

    @NotNull
    @Valid
    private final EntityInformationDto payee;

    @Valid
    private final List<AdjustmentDto> adjustments;

    Map<String, Object> metadata;

    @JsonCreator
    public TransactionDto(
            @JsonProperty("paymentMethod") PaymentMethodDto paymentMethod,
            @JsonProperty("total") Money total,
            @JsonProperty("payer") EntityInformationDto payer,
            @JsonProperty("payee") EntityInformationDto payee,
            @JsonProperty("adjustments") List<AdjustmentDto> adjustments,
            @JsonProperty("metadata") Map<String, Object> metadata
    ) {
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.payer = payer;
        this.payee = payee;
        this.adjustments = adjustments;
        this.metadata = metadata;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public PaymentMethodDto getPaymentMethod() {
        return paymentMethod;
    }

    public Money getTotal() {
        return total;
    }

    public EntityInformationDto getPayer() {
        return payer;
    }

    public EntityInformationDto getPayee() {
        return payee;
    }

    public List<AdjustmentDto> getAdjustments() {
        return adjustments;
    }
}
