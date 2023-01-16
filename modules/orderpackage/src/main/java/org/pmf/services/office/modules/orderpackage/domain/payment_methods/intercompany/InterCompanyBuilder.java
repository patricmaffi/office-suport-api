package org.pmf.services.office.modules.orderpackage.domain.payment_methods.intercompany;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;


@Component
public class InterCompanyBuilder implements PaymentMethodBuilder {

    public String getType() {
        return Methods.INTERCOMPANY;
    }

    public InterCompany build(PaymentMethodDto model) {
        return new InterCompany();
    }
}
