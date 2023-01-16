package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class CreditCardDto extends PaymentMethodDto {

    @NotNull
    private final String brand;

    @NotEmpty
    @Length(min = 2, max = 255)
    private final String holderName;

    @NotNull
    @Valid
    private final InstallmentsDto installments;

    @Valid
    private final DocumentDto document;

    @Valid
    private final BillingAddressDto billingAddress;

    @Length(min = 1, max = 36)
    private final String authorizationCode;

    @JsonCreator
    public CreditCardDto(
            @JsonProperty("brand") String brand,
            @JsonProperty("holderName") String holderName,
            @JsonProperty("installments") InstallmentsDto installments,
            @JsonProperty("gateway") String gateway,
            @JsonProperty("document") DocumentDto document,
            @JsonProperty("billingAddress") BillingAddressDto billingAddress,
            @JsonProperty("authorizationCode") String authorizationCode,
            @JsonProperty("antecipated") Boolean antecipated,
            @JsonProperty("metadata") Map<String, Object> metadata)
    {
        super(gateway, antecipated, metadata);
        this.brand = brand;
        this.holderName = holderName;
        this.installments = installments;
        this.document = document;
        this.billingAddress = billingAddress;
        this.authorizationCode = authorizationCode;
    }

    public String getType() {
        return Methods.CREDIT_CARD;
    }

    public String getBrand() {
        return brand;
    }

    public String getHolderName() {
        return holderName;
    }

    public InstallmentsDto getInstallments() {
        return installments;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public BillingAddressDto getBillingAddress() {
        return billingAddress;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }
}
