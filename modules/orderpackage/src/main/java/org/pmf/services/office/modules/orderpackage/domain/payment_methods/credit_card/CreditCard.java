package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.Map;

@JsonTypeName(Methods.CREDIT_CARD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard extends PaymentMethod {

    private String holderName;
    private Installments installments;
    private Document document;
    private BillingAddress billingAddress;
    private String brand;
    private String authorizationCode;

    public CreditCard(
            String gateway,
            Boolean antecipated,
            String brand,
            String holderName,
            Installments installments,
            Document document,
            BillingAddress billingAddress,
            String authorizationCode,
            Map<String, Object> metadata
    ) {
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

    public Installments getInstallments() {
        return installments;
    }

    public Document getDocument() {
        return document;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    @Override
    public boolean equals(Object candidate) {
        if (!(candidate instanceof CreditCard)) {
            return false;
        }

        var card = (CreditCard) candidate;

        boolean equal = brand.equals(card.getBrand())
                && holderName.equals(card.getHolderName())
                && installments.equals(card.getInstallments())
                && gateway.equals(card.getGateway());

        // Optional fields
        if (billingAddress != null) {
            equal = equal && billingAddress.equals(card.billingAddress);
        }

        if (document != null) {
            equal = equal && document.equals(card.document);
        }

        return equal;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
