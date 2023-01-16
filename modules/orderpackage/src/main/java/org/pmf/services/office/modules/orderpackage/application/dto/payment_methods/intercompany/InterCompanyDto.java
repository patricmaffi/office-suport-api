package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.intercompany;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

public class InterCompanyDto extends PaymentMethodDto {

    public InterCompanyDto() {
        super("", false, new HashMap<>());
    }

    public String getType() {
        return Methods.INTERCOMPANY;
    }
}
