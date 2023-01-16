package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankInformationTests {

    private static BankInformation modelSample() {
        return new BankInformation(
                "FooBank",
                "FooAccountNumber",
                "FooAgency",
                "FooAccountDigit",
                "FooAgencyDigit",
                BankAccountType.CHECKING
        );
    }

    @Test
    void testEmptyConstructor() {
        var bankInformation = modelSample();
        Assertions.assertNotNull(bankInformation);
    }

    @Test
    void testConstructor() {

        var bankInformation = modelSample();

        Assertions.assertNotNull(bankInformation);
        Assertions.assertEquals("FooAccountNumber", bankInformation.getAccountNumber());
        Assertions.assertEquals("FooBank", bankInformation.getBank());
        Assertions.assertEquals("FooAgency", bankInformation.getBankAgency());
        Assertions.assertEquals("FooAccountDigit", bankInformation.getAccountDigit());
        Assertions.assertEquals("FooAgencyDigit", bankInformation.getAgencyDigit());
        Assertions.assertEquals(BankAccountType.CHECKING, bankInformation.getType());
    }

    @Test
    void testEquality() {
        var bankInfo_1 = modelSample();
        var bankInfo_2 = modelSample();
        Assertions.assertEquals(bankInfo_1, bankInfo_2);
    }

    @Test
    void testInequality() {
        var bankInfo_1 = modelSample();
        var bankInfo_2 = new Object();
        Assertions.assertNotEquals(bankInfo_1, bankInfo_2);
        var hash = bankInfo_1.hashCode();
    }
}
