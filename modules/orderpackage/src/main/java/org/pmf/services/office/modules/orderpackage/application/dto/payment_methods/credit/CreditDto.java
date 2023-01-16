package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit;

import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class CreditDto extends PaymentMethodDto {

    @Valid
    @NotBlank
    private final String context;

    public CreditDto() {
        super("", false, new HashMap<>());
        this.context = null;
    }

    public CreditDto(String gateway, Boolean antecipated, String context, Map<String, Object> metadata) {
        super(gateway, antecipated, metadata);
        this.context = context;
    }

    public String getType() {
        return Methods.CREDIT;
    }

    public String getContext() {
        return context;
    }
}
