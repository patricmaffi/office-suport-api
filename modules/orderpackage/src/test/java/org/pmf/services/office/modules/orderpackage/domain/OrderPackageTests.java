package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.core.value_object.Money;

import java.util.ArrayList;
import java.util.UUID;

public class OrderPackageTests {

    public static OrderPackage modelSample() {
        return new OrderPackage("XPTO", UUID.randomUUID(), ProviderRelation.BILLED);
    }

    @Test
    void testEmptyConstructor() {
        var orderPackage = new OrderPackage();
        Assertions.assertNotNull(orderPackage);
    }

    @Test
    void testDefaultValues() {
        var orderPackage = modelSample();
        Assertions.assertNotNull(orderPackage);
        Assertions.assertEquals(0, orderPackage.getOrderPackageCode());
        Assertions.assertNotNull(orderPackage.getGuid());
        Assertions.assertEquals(36, orderPackage.getGuid().toString().length());
        Assertions.assertSame("XPTO", orderPackage.getCompany());
        Assertions.assertSame(0L, orderPackage.getVersion());
        Assertions.assertNotNull(orderPackage.getGuid());
        Assertions.assertTrue(orderPackage.metadata().isEmpty());
    }

    @Test
    void testWithEmptyOrders() {
        var orderPackage = new OrderPackage();
        Assertions.assertTrue(orderPackage.orders().isEmpty());
        Assertions.assertEquals(Money.from(0), orderPackage.totalSell());
        Assertions.assertEquals(Money.from(0), orderPackage.totalPurchase());
        Assertions.assertEquals(Money.from(0), orderPackage.subTotalSell());
        Assertions.assertEquals(Money.from(0), orderPackage.totalAdjustments());
    }

    @Test
    void testWithOneSellOrder() {
        var orderPackage = new OrderPackage();
        var saleOrder = OrderTests.orderSaleSample();
        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, new ArrayList<>());

        saleOrder.addItem(item);
        orderPackage.addOrder(saleOrder);

        Assertions.assertFalse(orderPackage.orders().isEmpty());
        Assertions.assertEquals(Money.from(10000), orderPackage.totalSell());
        Assertions.assertEquals(Money.from(0), orderPackage.totalPurchase());
        Assertions.assertEquals(Money.from(10000), orderPackage.subTotalSell());
        Assertions.assertEquals(Money.from(0), orderPackage.subTotalPurchase());
        Assertions.assertEquals(Money.from(0), orderPackage.totalAdjustments());
        Assertions.assertEquals(Money.from(10000), orderPackage.subTotal());
    }

    @Test
    void testWithOnePurchaseOrder() {
        var orderPackage = new OrderPackage();
        var saleOrder = OrderTests.orderPurchaseSample();
        var item = new Item("XPTO", "FooCode", Money.from(10000), 1, new ArrayList<>());

        saleOrder.addItem(item);
        orderPackage.addOrder(saleOrder);

        Assertions.assertFalse(orderPackage.orders().isEmpty());
        Assertions.assertEquals(Money.from(0), orderPackage.totalSell());
        Assertions.assertEquals(Money.from(10000), orderPackage.totalPurchase());
        Assertions.assertEquals(Money.from(0), orderPackage.subTotalSell());
        Assertions.assertEquals(Money.from(10000), orderPackage.subTotalPurchase());
        Assertions.assertEquals(Money.from(0), orderPackage.totalAdjustments());
        Assertions.assertEquals(Money.from(-10000), orderPackage.subTotal());
    }

    @Test
    void assertDefaultTimeStamps() {
        var orderPackage = new OrderPackage();

        Assertions.assertNotNull(orderPackage.getCreatedAt());
        Assertions.assertNull(orderPackage.getUpdatedAt());
    }
}
