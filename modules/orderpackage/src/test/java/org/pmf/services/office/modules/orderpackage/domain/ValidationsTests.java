package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.domain.exception.DomainException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationsTests {

    @Test
    void testMinMaxSuccessful() {
      Validations.validateMinMax(2, 1, 3, "FooMsg");
    }

    @Test
    void testMinMaxFailed() {
        Exception exception = assertThrows(
                DomainException.class,
                () -> Validations.validateMinMax(1, 2, 3, "FooMsg")
        );

        Assertions.assertEquals("FooMsg", exception.getMessage());
    }
}
