package org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.TRANSFER)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transfer extends PaymentMethod {

    private final Document document;

    public Transfer() {
        super("", false, new HashMap<>());
        this.document = null;
    }

    public Transfer(String gateway, Boolean antecipated, Document document) {
        super(gateway, antecipated, new HashMap<>());
        this.document = document;
    }

    public String getType() {
        return Methods.TRANSFER;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object candidate) {
        if (!(candidate instanceof Transfer)) {
            return false;
        }

        var transfer = (Transfer) candidate;


        return gateway.equals(transfer.gateway)
                && getType().equals(transfer.getType())
                && document.equals(transfer.getDocument());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
