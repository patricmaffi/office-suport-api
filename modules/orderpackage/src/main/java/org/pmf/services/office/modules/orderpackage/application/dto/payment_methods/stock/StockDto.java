package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.stock;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

public class StockDto extends PaymentMethodDto {

    public StockDto() {
        super("", false, new HashMap<>());
    }

    public String getType() {
        return Methods.STOCK;
    }
}
