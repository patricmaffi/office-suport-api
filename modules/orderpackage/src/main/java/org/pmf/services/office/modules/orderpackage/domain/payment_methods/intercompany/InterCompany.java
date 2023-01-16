package org.pmf.services.office.modules.orderpackage.domain.payment_methods.intercompany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.INTERCOMPANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterCompany extends PaymentMethod {

    public InterCompany() {
        super("", false, new HashMap<>());
    }

    @Override
    public String getType() {
        return Methods.INTERCOMPANY;
    }
}
