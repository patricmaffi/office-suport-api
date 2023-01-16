package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentTypeDto;
import org.pmf.services.office.modules.orderpackage.application.dto.TransactionDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilderStrategy;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.transfer.TransferDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionFactoryTests {

    @Mock
    protected PaymentMethodBuilderStrategy paymentMethodBuilderStrategy;

    @Mock
    protected EntityInformationFactory entityInformationFactory;

    @Mock
    protected AdjustmentFactory adjustmentFactory;

    @InjectMocks
    protected TransactionFactory transactionFactory;

    @Mock
    PaymentMethod paymentMethod;

    @Mock
    EntityInformation entityInformation;

    @Mock
    List<Adjustment> adjustments;

    public TransactionDto transactionDto;

    public TransactionFactoryTests() {
        this.transactionDto = modelSample();
    }

    public static TransactionDto modelSample() {

        var transferDto = new TransferDto(
                "FooGatway",
                false,
                new DocumentDto("fooDocument", DocumentTypeDto.NATURAL),
                new HashMap<>()
        );

        var entityInformationDto = EntityInformationFactoryTests.modelSample();

        return new TransactionDto(
                transferDto,
                Money.from(10),
                entityInformationDto,
                entityInformationDto,
                new ArrayList<>(),
                new HashMap<>()
        );
    }

    @BeforeEach
    void setUpFactories() {
        when(paymentMethodBuilderStrategy.createFromModel(any(PaymentMethodDto.class))).thenReturn(paymentMethod);
        when(entityInformationFactory.build(transactionDto.getPayer())).thenReturn(entityInformation);
        when(adjustmentFactory.buildMany(transactionDto.getAdjustments())).thenReturn(adjustments);
    }

    @Test
    void testBuildWithTransfer() {
        var transaction = transactionFactory.build(transactionDto);
        verify(paymentMethodBuilderStrategy, atLeastOnce()).createFromModel(transactionDto.getPaymentMethod());

        Assertions.assertEquals(transaction.getTotal(), transactionDto.getTotal());
        Assertions.assertEquals(transaction.getTotal(), transactionDto.getTotal());

        Assertions.assertEquals(transaction.getPaymentMethod(), paymentMethod);
        Assertions.assertTrue(transaction.getMetadata().isEmpty());
    }

    @Test
    void testBuild() {
        var transaction = transactionFactory.build(transactionDto);
        verify(paymentMethodBuilderStrategy, atLeastOnce()).createFromModel(transactionDto.getPaymentMethod());

        Assertions.assertEquals(transaction.getTotal(), transactionDto.getTotal());
        Assertions.assertEquals(transaction.getTotal(), transactionDto.getTotal());

        Assertions.assertEquals(transaction.getPaymentMethod(), paymentMethod);
        Assertions.assertTrue(transaction.getMetadata().isEmpty());
    }

    @Test
    void testBuildMany() {
        var transactionDto_1 = transactionDto;
        var transactionDto_2 = transactionDto;

        var list = new ArrayList<TransactionDto>();
        list.add(transactionDto_1);
        list.add(transactionDto_2);

        var transactions = this.transactionFactory.buildMany(list);
        Assertions.assertEquals(2, transactions.size());
    }
}
