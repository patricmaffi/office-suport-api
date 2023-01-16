package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card;

import org.hibernate.validator.constraints.Range;

public class InstallmentsDto {

    @Range(min = 1, max = 12)
    private final int value;

    public InstallmentsDto(int installments) {
        this.value = installments;
    }

    public int getValue() {
        return value;
    }
}
