package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.common.DocumentType;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card.CreditCardDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CreditCardBuilder implements PaymentMethodBuilder {

    private String brand;
    private String holderName;
    private Installments installments;
    private String gateway;
    private Boolean antecipated;
    private Document document;
    private BillingAddress billingAddress;
    private String authorizationCode;
    Map<String, Object> metadata;

    public String getType() {
        return Methods.CREDIT_CARD;
    }

    public CreditCard build(PaymentMethodDto model) {

        var cardModel = (CreditCardDto) model;
        var builder = new CreditCardBuilder();

        var modelInstallments = new Installments(cardModel.getInstallments().getValue());

        if (cardModel.getBillingAddress() != null) {
            var modelBillingAddress = new BillingAddress(
                    cardModel.getBillingAddress().getAddress(),
                    cardModel.getBillingAddress().getNumber(),
                    cardModel.getBillingAddress().getComplement(),
                    cardModel.getBillingAddress().getCity(),
                    cardModel.getBillingAddress().getState(),
                    cardModel.getBillingAddress().getPostalCode()
            );

            builder.withBillingAddress(modelBillingAddress);
        }

        builder
                .withBrand(cardModel.getBrand())
                .withHolderName(cardModel.getHolderName())
                .withInstallments(modelInstallments)
                .withGateway(model.getGateway())
                .withAntecipated(model.getAntecipated())
                .withAuthorizationCode(cardModel.getAuthorizationCode())
                .withMetadata(model.getMetadata());

        // Optional fields
        if (cardModel.getDocument() != null) {
            var documentType = DocumentType.valueOf(cardModel.getDocument().getPersonType().toString());
            var modelDocument = new Document(cardModel.getDocument().getDocument(), documentType);
            builder.withDocument(modelDocument);
        }

        return builder.build();
    }

    public CreditCardBuilder withInstallments(Installments installments) {
        this.installments = installments;
        return this;
    }

    public CreditCardBuilder withGateway(String gateway) {
        this.gateway = gateway;
        return this;
    }

    public CreditCardBuilder withAntecipated(Boolean antecipated) {
        this.antecipated = antecipated;
        return this;
    }

    public CreditCardBuilder withBrand(String brand) {
        this.brand = brand.toUpperCase();
        return this;
    }

    public CreditCardBuilder withHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public CreditCardBuilder withBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public CreditCardBuilder withDocument(Document document) {
        this.document = document;
        return this;
    }

    public CreditCardBuilder withAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
        return this;
    }

    public CreditCardBuilder withMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public CreditCard build() {
        return new CreditCard(
                gateway,
                antecipated,
                brand,
                holderName,
                installments,
                document,
                billingAddress,
                authorizationCode,
                metadata
        );
    }
}
