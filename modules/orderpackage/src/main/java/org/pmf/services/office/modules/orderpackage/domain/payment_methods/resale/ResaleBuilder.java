package org.pmf.services.office.modules.orderpackage.domain.payment_methods.resale;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;

@Component
public class ResaleBuilder implements PaymentMethodBuilder {

    public String getType() {
        return Methods.RESALE;
    }

    public Resale build(PaymentMethodDto model) {
        return new Resale();
    }
}
