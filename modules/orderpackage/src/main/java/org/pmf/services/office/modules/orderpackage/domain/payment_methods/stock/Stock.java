package org.pmf.services.office.modules.orderpackage.domain.payment_methods.stock;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.STOCK)
public class Stock extends PaymentMethod {

    public Stock() {
        super("", false, new HashMap<>());
    }

    @Override
    public String getType() {
        return Methods.STOCK;
    }
}
