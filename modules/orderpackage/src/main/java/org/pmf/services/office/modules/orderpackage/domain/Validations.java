package org.pmf.services.office.modules.orderpackage.domain;

import org.pmf.services.office.modules.orderpackage.domain.exception.DomainException;

public class Validations {

    private Validations() {
    }

    public static void validateMinMax(int value, int min, int max, String message) {
        if (value < min || value > max) throw new DomainException(message);
    }
}
