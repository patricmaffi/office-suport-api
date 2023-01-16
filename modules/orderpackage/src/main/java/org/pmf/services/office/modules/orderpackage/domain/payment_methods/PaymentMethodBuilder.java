package org.pmf.services.office.modules.orderpackage.domain.payment_methods;

import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;

public interface PaymentMethodBuilder {
    PaymentMethod build(PaymentMethodDto model);
    String getType();
}
