package org.pmf.services.office.modules.orderpackage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.billed.Billed;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit.Credit;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card.CreditCard;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.intercompany.InterCompany;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.resale.Resale;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.stock.Stock;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer.Transfer;

import java.io.Serializable;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCard.class, name = Methods.CREDIT_CARD),
        @JsonSubTypes.Type(value = Transfer.class, name = Methods.TRANSFER),
        @JsonSubTypes.Type(value = Credit.class, name = Methods.CREDIT),
        @JsonSubTypes.Type(value = InterCompany.class, name = Methods.INTERCOMPANY),
        @JsonSubTypes.Type(value = Billed.class, name = Methods.BILLED),
        @JsonSubTypes.Type(value = Resale.class, name = Methods.RESALE),
        @JsonSubTypes.Type(value = Stock.class, name = Methods.STOCK)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PaymentMethod implements Serializable {
    protected String gateway;
    private Boolean antecipated = false;

    protected PaymentMethod(String gateway, Boolean antecipated, Map<String, Object> metadata) {
        this.gateway = gateway;
        this.antecipated = antecipated;
        this.metadata = metadata;
    }
    protected PaymentMethod(String gateway, Map<String, Object> metadata) {
        this.gateway = gateway;
        this.antecipated = false;
        this.metadata = metadata;
    }

    public abstract String getType();

    public String getGateway() {
        return gateway;
    }

    public Boolean getAntecipated() {
        return antecipated;
    }

    Map<String, Object> metadata;
    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
