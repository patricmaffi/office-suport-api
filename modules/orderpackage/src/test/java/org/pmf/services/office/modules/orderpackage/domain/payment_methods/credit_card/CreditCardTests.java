package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentTypeDto;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.common.DocumentType;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card.BillingAddressDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card.CreditCardDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card.InstallmentsDto;

import java.util.HashMap;

class CreditCardTests {

    private static CreditCard cardBuilderSample() {
        var builder = new CreditCardBuilder();

        var billingAddress = new BillingAddress(
                "FooStreet",
                "1",
                "101",
                "BarCity",
                "FooState",
                "1234567"
        );

        var document = new Document("123456789101", DocumentType.NATURAL);

        return builder.withBillingAddress(billingAddress)
                .withBrand("VISA")
                .withDocument(document)
                .withHolderName("John doe")
                .withInstallments(new Installments(12))
                .withGateway("pagarme")
                .withAntecipated(false)
                .build();
    }

    private static CreditCard cardBuilderSample2() {
        var builder = new CreditCardBuilder();

        var billingAddress = new BillingAddress(
                "Max street",
                "1",
                "101",
                "MilhasCity",
                "FooState",
                "422424"
        );

        var document = new Document("123456789101", DocumentType.NATURAL);

        return builder.withBillingAddress(billingAddress)
                .withBrand("VISA")
                .withDocument(document)
                .withHolderName("John doe")
                .withInstallments(new Installments(10))
                .withGateway("pagarme")
                .build();
    }

    public static CreditCardDto creditCardModel() {

        var billingAddress = new BillingAddressDto(
                "FooStreet",
                "1",
                "101",
                "BarCity",
                "FooState",
                "1234567"
        );

        var document = new DocumentDto("123456789101", DocumentTypeDto.NATURAL);

        return new CreditCardDto(
                "VISA",
                "John doe",
                new InstallmentsDto(12),
                "pagarme",
                document,
                billingAddress,
                "000",
                false,
                new HashMap<>()
                );
    }

    private static PaymentMethod cardBuilderFromModel() {
        var builder = new CreditCardBuilder();
        return builder.build(creditCardModel());
    }

    @Test
    void testCreditCardBuilder() {
        var sample = cardBuilderSample();
        Assertions.assertNotNull(sample);
        Assertions.assertEquals(Methods.CREDIT_CARD, sample.getType());
    }

    @Test
    void testCreditCardBuilderFromModel() {
        Assertions.assertNotNull(cardBuilderFromModel());
    }

    @Test
    void testCreditCardBuilderFromModelEquality() {
        var card1 = cardBuilderSample();
        var card2 = cardBuilderFromModel();
        Assertions.assertEquals(card2, card1);
    }

    @Test
    void testCreditCardEquality() {
        var card1 = cardBuilderSample();
        var card2 = cardBuilderSample();
        Assertions.assertEquals(card2, card1);
    }

    @Test
    void testCreditCardInequality() {
        var card1 = cardBuilderSample();
        var card2 = cardBuilderSample2();
        Assertions.assertNotEquals(card2, card1);
    }
}
