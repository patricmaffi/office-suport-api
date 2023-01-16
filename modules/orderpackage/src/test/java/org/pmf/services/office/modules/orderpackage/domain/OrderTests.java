package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.common.DocumentType;
import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformationTests;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer.Transfer;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class OrderTests {

    static Order orderSaleSample() {
        return new Order(OrderType.SALE);
    }

    static Order orderPurchaseSample() {
        return new Order(OrderType.PURCHASE);
    }

    @Test
    void testEmptyConstructor() {
        var order = new Order();
        Assertions.assertNotNull(order);
    }

    @Test
    void testDefaultValues() {
        var order = orderSaleSample();
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
    }

    @Test
    void testCustomerAndMerchant() {
        var order = orderSaleSample();

        var merchant = EntityInformationTests.modelSample();
        var customer = EntityInformationTests.modelSample();

        Assertions.assertNull(order.getMerchant());
        Assertions.assertNull(order.getCustomer());

        order.setMerchant(merchant);
        order.setCustomer(customer);

        Assertions.assertNotNull(order.getMerchant());
        Assertions.assertEquals(order.getMerchant(), merchant);
        Assertions.assertNotNull(order.getCustomer());
        Assertions.assertEquals(order.getCustomer(), customer);
    }

    @Test
    void testSetOrderPackage() {
        var order = orderSaleSample();
        var orderPackageMock = Mockito.mock(OrderPackage.class);

        Assertions.assertNull(order.getOrderPackage());
        order.setOrderPackage(orderPackageMock);
        Assertions.assertNotNull(order.getOrderPackage());
        Assertions.assertEquals(order.getOrderPackage(), orderPackageMock);
    }


    @Test
    void testOrderConstructorWithMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("foo", "bar");

        var order = new Order(OrderType.PURCHASE, metadata);

        Assertions.assertEquals("bar", order.getMetadata().get("foo"));
    }

    @Test
    void testTotalWithoutItems() {
        Assertions.assertEquals(orderSaleSample().getTotal(), new Money(0));
    }

    @Test
    void testSubTotalWithoutItems() {
        Assertions.assertEquals(orderSaleSample().getSubTotal(), new Money(0));
    }

    @Test
    void testTotalWithItems() {
        Order order = orderSaleSample();
        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, new ArrayList<>());
        order.addItem(item);
        Assertions.assertEquals(order.getTotal(), new Money(10000));
    }

    @Test
    void testSubTotalWithItems() {
        Order order = orderSaleSample();
        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, new ArrayList<>());
        order.addItem(item);
        Assertions.assertEquals(order.getSubTotal(), new Money(10000));
    }

    @Test
    void testSubTotalWithItemsAndAdjustments() {
        Order order = orderSaleSample();

        var adjustment_1 = new Adjustment(AdjustmentType.COUPON, Money.from(-2000));
        var adjustment_2 = new Adjustment(AdjustmentType.INCENTIVE, Money.from(50000));

        var adjustments = Arrays.asList(adjustment_1, adjustment_2);

        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, adjustments);
        order.addItem(item);

        Assertions.assertEquals(order.getTotal(), new Money(8000));
        Assertions.assertEquals(order.getSubTotal(), new Money(10000));
        Assertions.assertEquals(order.getTotalAdjustments(), new Money(-2000));
    }

    @Test
    void testAddTransactionToOrder() {
        Order order = orderSaleSample();

        var transaction = new Transaction(
                new Transfer("paymee", false, new Document("00000000000", DocumentType.NATURAL)),
                new Money(10000),
                EntityInformationTests.modelSample(),
                EntityInformationTests.modelSample(),
                new ArrayList<>(),
                new HashMap<>()
        );

        Assertions.assertEquals(0, order.getTransactions().size());
        order.addTransaction(transaction);
        Assertions.assertEquals(1, order.getTransactions().size());
    }

    @Test
    void testItemRemove() {
        Order order = orderSaleSample();
        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, new ArrayList<>());
        order.addItem(item);
        Assertions.assertEquals(1, order.getItems().size());
        order.removeItem(item);
        Assertions.assertEquals(0, order.getItems().size());
    }

    @Test
    void assertDefaultTimeStamps() {
        var order = orderSaleSample();
        Assertions.assertNotNull(order.getCreatedAt());
        Assertions.assertNull(order.getUpdatedAt());
    }
}
