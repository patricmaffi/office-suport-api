package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit.CreditDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;


@Component
public class CreditBuilder implements PaymentMethodBuilder {

    public static final String TYPE = Methods.CREDIT;

    public String getType() {
        return TYPE;
    }

    public Credit build(PaymentMethodDto model) {
        CreditDto creditModel = (CreditDto) model;
        return new Credit(creditModel.getGateway(), creditModel.getAntecipated(), creditModel.getContext());
    }
}
