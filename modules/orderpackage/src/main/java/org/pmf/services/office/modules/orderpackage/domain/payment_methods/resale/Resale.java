package org.pmf.services.office.modules.orderpackage.domain.payment_methods.resale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.RESALE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resale extends PaymentMethod {

    public Resale() {
        super("", false, new HashMap<>());
    }

    @Override
    public String getType() {
        return Methods.RESALE;
    }
}
