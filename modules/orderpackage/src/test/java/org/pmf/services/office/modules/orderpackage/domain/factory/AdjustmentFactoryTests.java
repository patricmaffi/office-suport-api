package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.AdjustmentTypeDto;
import org.pmf.services.office.modules.orderpackage.application.dto.AdjustmentDto;

import java.util.ArrayList;
import java.util.HashMap;

class AdjustmentFactoryTests {

    AdjustmentFactory adjustmentFactory;

    public AdjustmentFactoryTests() {
        this.adjustmentFactory = new AdjustmentFactory();
    }

    static AdjustmentDto modelSample() {
        var adjustmentType = AdjustmentTypeDto.COUPON;
        return new AdjustmentDto(adjustmentType, Money.from(-2000), new HashMap<>());
    }

    @Test
    void testBuild() {
        AdjustmentDto adjustmentDto = modelSample();
        var adjustment = adjustmentFactory.build(adjustmentDto);

        Assertions.assertEquals(adjustment.getType().toString(), adjustmentDto.getType().toString());
        Assertions.assertEquals(adjustment.getTotal(), adjustmentDto.getTotal());
        Assertions.assertTrue(adjustment.getMetadata().isEmpty());
    }

    @Test
    void testBuildMany() {
        var factory = new AdjustmentFactory();
        var adjustmentDto_1 = modelSample();
        var adjustmentDto_2 = modelSample();

        var list = new ArrayList<AdjustmentDto>();
        list.add(adjustmentDto_1);
        list.add(adjustmentDto_2);

        var adjustments = factory.buildMany(list);

        Assertions.assertFalse(adjustments.isEmpty());
    }
}
