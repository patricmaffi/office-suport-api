package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import java.util.HashMap;

@JsonTypeName(Methods.CREDIT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credit extends PaymentMethod {

    private final String context;

    public Credit(String gateway, Boolean antecipated, String context) {
        super(gateway, antecipated, new HashMap<>());
        this.context = context;
    }

    public String getType() {
        return Methods.CREDIT;
    }

    public String getContext() {
        return context;
    }

    @Override
    public boolean equals(Object candidate) {
        if (!(candidate instanceof Credit)) {
            return false;
        }

        var credit = (Credit) candidate;

        return gateway.equals(credit.gateway)
                && getContext().equals(credit.getContext())
                && getType().equals(credit.getType());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
