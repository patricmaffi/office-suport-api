package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTests {

    private static Address addressSample() {
        return new Address(
                "Foo Street",
                "0",
                "apt 1",
                "Foo city",
                "Foo state",
                "FooPostalCode"
        );
    }

    @Test
    void testEmptyConstructor() {
        var address = addressSample();
        Assertions.assertNotNull(address);
    }

    @Test
    void testConstructor() {

        var address = addressSample();

        Assertions.assertNotNull(address);
        Assertions.assertEquals("Foo Street", address.getStreet());
        Assertions.assertEquals("0", address.getNumber());
        Assertions.assertEquals("apt 1", address.getComplement());
        Assertions.assertEquals("Foo city", address.getCity());
        Assertions.assertEquals("Foo state", address.getState());
        Assertions.assertEquals("FooPostalCode", address.getPostalCode());
    }

    @Test
    void testEquality() {
        var address_1 = addressSample();
        var address_2 = addressSample();
        Assertions.assertEquals(address_1, address_2);
    }

    @Test
    void testInequality() {
        var address_1 = addressSample();
        var address_2 = new Object();
        Assertions.assertNotEquals(address_1, address_2);
        var hash = address_1.hashCode();
    }
}
