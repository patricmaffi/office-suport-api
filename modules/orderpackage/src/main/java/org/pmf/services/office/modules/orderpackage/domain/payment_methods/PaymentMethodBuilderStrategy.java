package org.pmf.services.office.modules.orderpackage.domain.payment_methods;

import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.exception.UnsupportedPaymentMethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodBuilderStrategy {

    List<PaymentMethodBuilder> paymentMethodBuilders;

    @Autowired
    public PaymentMethodBuilderStrategy(List<PaymentMethodBuilder> paymentMethodBuilders) {
        this.paymentMethodBuilders = paymentMethodBuilders;
    }

    public PaymentMethod createFromModel(PaymentMethodDto model) {

        PaymentMethod paymentMethod = null;

        for (PaymentMethodBuilder paymentMethodBuilder : paymentMethodBuilders) {
            if (paymentMethodBuilder.getType().equals(model.getType())) {
                paymentMethod = paymentMethodBuilder.build(model);
            }
        }

        if (paymentMethod == null) throw new UnsupportedPaymentMethodException(model.getType());

        return paymentMethod;
    }
}
