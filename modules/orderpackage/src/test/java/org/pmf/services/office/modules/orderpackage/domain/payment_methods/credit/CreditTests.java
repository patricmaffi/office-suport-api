package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit.CreditDto;

import java.util.HashMap;

class CreditTests {

    private static Credit creditSample() {
        var builder = new CreditBuilder();
        var creditDto = new CreditDto("fooGateway", false, "qwerty", new HashMap<>());
        return builder.build(creditDto);
    }

    private static Credit creditSample2() {
        var builder = new CreditBuilder();
        var creditDto = new CreditDto("johnGateway", false, "qwerty", new HashMap<>());
        return builder.build(creditDto);
    }

    private static CreditDto creditDtoSample() {
        return new CreditDto("barGateway", false, "john", new HashMap<>());
    }

    private static PaymentMethod creditBuilderFromModel() {
        var builder = new CreditBuilder();
        return builder.build(creditDtoSample());
    }

    @Test
    void testCreditCardBuilderFromModel() {
        Assertions.assertNotNull(creditBuilderFromModel());
    }

    @Test
    void testCreditCardBuilderFromModelEquality() {
        var credit1 = creditSample();
        var credit2 = creditSample();
        Assertions.assertEquals(credit2, credit1);
        Assertions.assertEquals(credit2.hashCode(), credit1.hashCode());
    }

    @Test
    void testCreditCardBuilderFromModelInequality() {
        var credit1 = creditSample();
        var credit2 = creditSample2();
        Assertions.assertNotEquals(credit2, credit1);
    }

    @Test
    void testCreditCardBuilderFromModelInequalityWithDifferentType() {
        var credit = creditSample();
        var fooObject = new Object();
        Assertions.assertNotEquals(credit, fooObject);
    }
}
