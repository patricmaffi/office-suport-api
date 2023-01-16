package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit.CreditDto;
import org.mockito.Mockito;

class CreditBuilderTests {

    CreditBuilder creditBuilder;

    public CreditBuilderTests() {
        this.creditBuilder = new CreditBuilder();
    }

    @Test
    void testGetType() {
        Assertions.assertEquals(CreditBuilder.TYPE, creditBuilder.getType());
    }

    @Test
    void testBuild() {
        var creditDto = Mockito.mock(CreditDto.class);
        var credit = creditBuilder.build(creditDto);
        Assertions.assertNotNull(credit);
    }
}
