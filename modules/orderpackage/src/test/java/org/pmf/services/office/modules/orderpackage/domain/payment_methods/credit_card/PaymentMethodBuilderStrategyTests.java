package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.exception.UnsupportedPaymentMethodException;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilderStrategy;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMethodBuilderStrategyTests {

    @Test
    void testCreateFromModelWithAvailableBuilder() {
        var builder = Mockito.mock(PaymentMethodBuilder.class);

        var strategy = new PaymentMethodBuilderStrategy(Collections.singletonList(builder));
        var paymentMethodDto = Mockito.mock(PaymentMethodDto.class);
        var paymentMethod = Mockito.mock(PaymentMethod.class);

        when(builder.getType()).thenReturn("fooType");
        when(builder.build(paymentMethodDto)).thenReturn(paymentMethod);
        when(paymentMethodDto.getType()).thenReturn("fooType");

        Assertions.assertEquals(paymentMethod, strategy.createFromModel(paymentMethodDto));
    }

    @Test
    void testCreateFromModelWithUnavailableBuilder() {
        var builder = Mockito.mock(PaymentMethodBuilder.class);

        var strategy = new PaymentMethodBuilderStrategy(Collections.singletonList(builder));
        var paymentMethodDto = Mockito.mock(PaymentMethodDto.class);

        when(builder.getType()).thenReturn("fooType");
        when(paymentMethodDto.getType()).thenReturn("barType");

        var exception = assertThrows(UnsupportedPaymentMethodException.class, () -> strategy.createFromModel(paymentMethodDto));
        Assertions.assertEquals("PaymentMethod \"" + paymentMethodDto.getType() +  "\" is not supported", exception.getMessage());
    }

}
