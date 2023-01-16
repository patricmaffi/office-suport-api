package org.pmf.services.office.modules.orderpackage.domain.payment_methods.billed;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;

@Component
public class BilledBuilder implements PaymentMethodBuilder {

    public String getType() {
        return Methods.BILLED;
    }

    public Billed build(PaymentMethodDto model) {
        return new Billed();
    }
}
