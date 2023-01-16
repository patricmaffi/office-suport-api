package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.billed;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

public class BilledDto extends PaymentMethodDto {

    public BilledDto() {
        super("", false, new HashMap<>());
    }

    public String getType() {
        return Methods.BILLED;
    }
}
