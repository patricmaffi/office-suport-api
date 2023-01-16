package org.pmf.services.office.modules.orderpackage.domain.payment_methods.intercompany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.intercompany.InterCompanyDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

class IntercompanyTests {

    private static InterCompany modelSample() {
        var builder = new InterCompanyBuilder();
        var interCompanyDto = new InterCompanyDto();

        return builder.build(interCompanyDto);
    }

    @Test
    void testInterCompanyBuilderFromModel() {
        var interCompany = modelSample();
        Assertions.assertNotNull(interCompany);
        Assertions.assertEquals(Methods.INTERCOMPANY, interCompany.getType());
    }

    @Test
    void testBuilderType() {
        var builder = new InterCompanyBuilder();
        Assertions.assertEquals(Methods.INTERCOMPANY, builder.getType());
    }
}
