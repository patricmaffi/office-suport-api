package org.pmf.services.office.modules.orderpackage.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.core.value_object.Money;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

class AdjustmentTests {

    static Adjustment adjustmentCouponSample() {
        return new Adjustment(AdjustmentType.COUPON, new Money(-30000));
    }

    static Adjustment adjustmentIncentiveSample() {
        return new Adjustment(AdjustmentType.INCENTIVE, new Money(20000));
    }

    @Test
    void testEmptyConstructor() {
        var adjustment = new Adjustment();
        var hash = adjustment.hashCode();
        Assertions.assertNotNull(adjustment);
    }

    @Test
    void testOrderConstructorWithMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("foo", "bar");

        var order = new Order(OrderType.PURCHASE, metadata);

        Assertions.assertEquals("bar", order.getMetadata().get("foo"));
    }

    @Test
    void testCouponAdjustment() {
        Assertions.assertEquals(adjustmentCouponSample().getTotal(), new Money(-30000));
    }

    @Test
    void testIncentiveAdjustment() {
        Assertions.assertEquals(adjustmentIncentiveSample().getTotal(), new Money(20000));
    }

    @Test
    void testEquality() {
        var adjustment_1 = adjustmentCouponSample();
        var adjustment_2 = adjustmentCouponSample();

        Assertions.assertEquals(adjustment_1, adjustment_2);
        Assertions.assertNotEquals(true, adjustment_1.equals(Mockito.mock(Object.class)));
    }
}
