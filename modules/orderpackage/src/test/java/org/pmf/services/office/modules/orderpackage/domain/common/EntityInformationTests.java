package org.pmf.services.office.modules.orderpackage.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class EntityInformationTests {

    public static EntityInformation modelSample() {

        var receiptInformation = new ReceiptInformation(
                "FooSsn",
                PersonType.NATURAL,
                "FooCommercialName",
                "FooAddress",
                "321",
                null,
                "FooNeighborhood",
                "FooState",
                "FooPostalCode",
                "FooMunicipalCode",
                "FooMunicipalSubscription"
        );

        var bankInformation = new BankInformation(
                "001",
                "fooBank",
                "FooAgency",
                "FooAccountDigit",
                "fooAgencyDigit",
                BankAccountType.CHECKING
        );

        return new EntityInformation(
                "foo@email.com",
                "John Doe",
                "FooNumber",
                "0123456789",
                PersonType.NATURAL,
                receiptInformation,
                bankInformation,
                new HashMap<>()
        );
    }

    @Test
    void testEmptyConstructor() {
        var entityInformation = new EntityInformation();
        Assertions.assertNotNull(entityInformation);
    }

    @Test
    void testConstructor() {
        var entityInformation = modelSample();

        Assertions.assertNotNull(entityInformation);
        Assertions.assertEquals("foo@email.com", entityInformation.getEmail());
        Assertions.assertEquals("John Doe", entityInformation.getName());
        Assertions.assertEquals("FooNumber", entityInformation.getPhone());
        Assertions.assertNotNull(entityInformation.getReceiptInformation());
        Assertions.assertNotNull(entityInformation.getBankInformation());
        Assertions.assertTrue(entityInformation.getMetadata().isEmpty());
    }

    @Test
    void testEquality() {
        var entity_1 = modelSample();
        var entity_2 = modelSample();
        Assertions.assertEquals(entity_1, entity_2);
    }

    @Test
    void testInequality() {
        var entity_1 = modelSample();
        var entity_2 = new Object();
        Assertions.assertNotEquals(entity_1, entity_2);
        var hash = entity_1.hashCode();
    }
}
