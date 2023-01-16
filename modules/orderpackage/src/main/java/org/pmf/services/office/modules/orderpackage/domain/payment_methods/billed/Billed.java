package org.pmf.services.office.modules.orderpackage.domain.payment_methods.billed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.BILLED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Billed extends PaymentMethod {

    public Billed() {
        super("", false, new HashMap<>());
    }

    @Override
    public String getType() {
        return Methods.BILLED;
    }
}
