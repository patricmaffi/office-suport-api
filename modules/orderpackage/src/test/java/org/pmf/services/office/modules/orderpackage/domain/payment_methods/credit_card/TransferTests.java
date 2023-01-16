package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentTypeDto;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer.Transfer;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer.TransferBuilder;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.transfer.TransferDto;

import java.util.HashMap;

class TransferTests {

    private static Transfer transferSample() {
        var builder = new TransferBuilder();
        var documentDto = new DocumentDto("123456789101", DocumentTypeDto.NATURAL);
        var transferDto = new TransferDto("fooGateway", false, documentDto, new HashMap<>());
        return builder.build(transferDto);
    }

    private static Transfer transferSample2() {
        var builder = new TransferBuilder();
        var documentDto = new DocumentDto("000000000000", DocumentTypeDto.NATURAL);
        var transferDto = new TransferDto("barGateway", false, documentDto, new HashMap<>());
        return builder.build(transferDto);
    }

    private static TransferDto transferDtoSample() {
        var documentDto = new DocumentDto("000000000000", DocumentTypeDto.NATURAL);
        return new TransferDto("barGateway", false, documentDto, new HashMap<>());
    }

    private static PaymentMethod transferBuilderFromModel() {
        var builder = new TransferBuilder();
        return builder.build(transferDtoSample());
    }

    @Test
    void testCreditCardBuilderFromModel() {
        Assertions.assertNotNull(transferBuilderFromModel());
    }

    @Test
    void testCreditCardBuilderFromModelEquality() {
        var transfer1 = transferSample();
        var transfer2 = transferSample();
        Assertions.assertEquals(transfer2, transfer1);
    }

    @Test
    void testCreditCardInequality() {
        var transfer1 = transferSample();
        var transfer2 = transferSample2();
        Assertions.assertNotEquals(transfer2, transfer1);
    }
}
