package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.modules.orderpackage.domain.Validations;

import java.io.Serializable;

public class Installments implements Serializable {

    private int value;

    @JsonCreator
    public Installments(@JsonProperty("value") int installments) {
        this.value = installments;
        validate();
    }

    public int getValue() {
        return value;
    }

    private void validate() {
        Validations.validateMinMax(
                value,
                1, 12,
                String.format("The installments value but be between %s and %s", 1, 12)
        );
    }

    @Override
    public boolean equals(Object candidate) {
        if (!(candidate instanceof Installments)) {
            return false;
        }
        var installments = (Installments) candidate;
        return this.value == installments.getValue();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
