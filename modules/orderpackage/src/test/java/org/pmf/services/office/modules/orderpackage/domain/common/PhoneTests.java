package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneTests {

    public static Phone modelSample() {
        return new Phone(
                "FooNumber",
                PhoneType.MOBILE
        );
    }

    private static Phone phoneSampleWithCountryCode() {
        return new Phone(
                "FooNumber",
                PhoneType.MOBILE,
                "BRA"
        );
    }

    @Test
    void testEmptyConstructor() {
        var phone = modelSample();
        Assertions.assertNotNull(phone);
    }

    @Test
    void testDefaultConstructor() {
        var phone = modelSample();

        Assertions.assertNotNull(phone);
        Assertions.assertEquals("FooNumber", phone.phoneNumber());
        Assertions.assertEquals(PhoneType.MOBILE, phone.type());
    }

    @Test
    void testConstructorWithCountryCode() {
        var phone = phoneSampleWithCountryCode();

        Assertions.assertNotNull(phone);
        Assertions.assertEquals("FooNumber", phone.phoneNumber());
        Assertions.assertEquals(PhoneType.MOBILE, phone.type());
        Assertions.assertEquals("BRA", phone.countryCode());
    }

    @Test
    void testEquality() {
        var phone_1 = modelSample();
        var phone_2 = modelSample();
        Assertions.assertEquals(phone_1, phone_2);
    }

    @Test
    void testInequality() {
        var phone_1 = modelSample();
        var phone_2 = new Object();
        Assertions.assertNotEquals(phone_1, phone_2);
        var hash = phone_1.hashCode();
    }
}
