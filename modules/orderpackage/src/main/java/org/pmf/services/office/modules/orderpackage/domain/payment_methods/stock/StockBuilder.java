package org.pmf.services.office.modules.orderpackage.domain.payment_methods.stock;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;

@Component
public class StockBuilder implements PaymentMethodBuilder {

    public String getType() {
        return Methods.STOCK;
    }

    public Stock build(PaymentMethodDto model) {return new Stock();}
}
