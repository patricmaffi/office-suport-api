package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceiptInformationTests {

    private static ReceiptInformation receiptInformationSample(PersonType personType) {

        return new ReceiptInformation(
                "FooSsn",
                personType,
                "FooFantasyName",
                "FooAddress",
                "FooNumber",
                "FooComplement",
                "FooNeighborhood",
                "FooState",
                "FooPostalCode",
                "FooMunicipalCode",
                "FooMunicipalSubscription"
        );
    }

    @Test
    void testEmptyConstructor() {
        var receiptInformation = receiptInformationSample(PersonType.NATURAL);
        Assertions.assertNotNull(receiptInformation);
    }

    @Test
    void testConstructor() {

        var receiptInformation = receiptInformationSample(PersonType.NATURAL);

        Assertions.assertNotNull(receiptInformation);
        Assertions.assertEquals("FooSsn", receiptInformation.getSsn());
        Assertions.assertEquals(PersonType.NATURAL, receiptInformation.getPersonType());
        Assertions.assertEquals("FooFantasyName", receiptInformation.getFantasyName());
        Assertions.assertEquals("FooAddress", receiptInformation.getAddress());
        Assertions.assertEquals("FooNumber", receiptInformation.getNumber());
        Assertions.assertEquals("FooComplement", receiptInformation.getComplement());
        Assertions.assertEquals("FooNeighborhood", receiptInformation.getNeighborhood());
        Assertions.assertEquals("FooState", receiptInformation.getState());
        Assertions.assertEquals("FooPostalCode", receiptInformation.getPostalCode());
        Assertions.assertEquals("FooMunicipalCode", receiptInformation.getMunicipalCode());
        Assertions.assertEquals("FooMunicipalSubscription", receiptInformation.getMunicipalSubscription());
    }

    @Test
    void testEquality() {
        var receipt_1 = receiptInformationSample(PersonType.NATURAL);
        var receipt_2 = receiptInformationSample(PersonType.NATURAL);
        Assertions.assertEquals(receipt_1, receipt_2);
    }

    @Test
    void testEqualityWithLegalPersonType() {
        var receipt_1 = receiptInformationSample(PersonType.LEGAL);
        var receipt_2 = receiptInformationSample(PersonType.LEGAL);
        Assertions.assertEquals(receipt_1, receipt_2);
    }

    @Test
    void testInequality() {
        var receipt_1 = receiptInformationSample(PersonType.NATURAL);
        var receipt_2 = new Object();
        Assertions.assertNotEquals(receipt_1, receipt_2);
        var hash = receipt_1.hashCode();
    }
}
