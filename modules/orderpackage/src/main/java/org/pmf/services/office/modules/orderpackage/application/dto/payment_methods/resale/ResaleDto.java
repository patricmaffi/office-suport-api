package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.resale;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

public class ResaleDto extends PaymentMethodDto {

    public ResaleDto() {
        super("", false, new HashMap<>());
    }

    public String getType() {
        return Methods.RESALE;
    }
}
