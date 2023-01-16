package org.pmf.services.office.modules.orderpackage.domain.payment_methods.billed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.billed.BilledDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

class BilledTests {

    private static Billed modelSample() {
        var builder = new BilledBuilder();
        var billedDto = new BilledDto();

        return builder.build(billedDto);
    }

    @Test
    void testBuilderFromModel() {
        var billed = modelSample();
        Assertions.assertNotNull(billed);
        Assertions.assertEquals(Methods.BILLED, billed.getType());
    }

    @Test
    void testBuilderType() {
        var builder = new BilledBuilder();
        Assertions.assertEquals(Methods.BILLED, builder.getType());
    }
}
