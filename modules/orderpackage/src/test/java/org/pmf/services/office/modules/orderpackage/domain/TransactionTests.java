package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionTests {

    static Order TransactionSample() {
        return new Order(OrderType.SALE);
    }

    @Test
    void testEmptyConstructor() {
        var transaction = new Transaction();
        Assertions.assertNotNull(transaction);
    }

    @Test
    void testDefaultValues() {
        var transaction = new Transaction();
        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getId());
        Assertions.assertTrue(transaction.getAdjustments().isEmpty());
        Assertions.assertTrue(transaction.getMetadata().isEmpty());
        Assertions.assertNull(transaction.getOrder());
        Assertions.assertNull(transaction.getPayee());
        Assertions.assertNull(transaction.getPayer());
        Assertions.assertEquals(TransactionStatus.COMPLETED, transaction.getStatus());
        Assertions.assertNotNull(transaction.getCreatedAt());
        Assertions.assertNull(transaction.getUpdatedAt());
    }
}
