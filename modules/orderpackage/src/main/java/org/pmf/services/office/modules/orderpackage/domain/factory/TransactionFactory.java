package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.TransactionDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.PaymentMethod;
import org.pmf.services.office.modules.orderpackage.domain.Transaction;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionFactory implements Factory<TransactionDto, Transaction> {

    protected PaymentMethodBuilderStrategy paymentMethodBuilderStrategy;
    protected EntityInformationFactory entityInformationFactory;
    protected AdjustmentFactory adjustmentFactory;

    @Autowired
    public TransactionFactory(
            PaymentMethodBuilderStrategy paymentMethodBuilderStrategy,
            EntityInformationFactory entityInformationFactory,
            AdjustmentFactory adjustmentFactory
    ) {
        this.paymentMethodBuilderStrategy = paymentMethodBuilderStrategy;
        this.entityInformationFactory = entityInformationFactory;
        this.adjustmentFactory = adjustmentFactory;
    }

    @Override
    public Transaction build(TransactionDto model) {

        return new Transaction(
                buildPaymentMethod(model.getPaymentMethod()),
                new Money(model.getTotal().getCurrency(), model.getTotal().getAmount()),
                entityInformationFactory.build(model.getPayer()),
                entityInformationFactory.build(model.getPayee()),
                adjustmentFactory.buildMany(model.getAdjustments()),
                model.getMetadata()
        );
    }

    @Override
    public List<Transaction> buildMany(List<TransactionDto> models) {
        List<Transaction> list = new ArrayList<>();

        if(models == null || models.isEmpty()) {
            return list;
        }

        models.forEach((TransactionDto transactionModel) -> list.add(build(transactionModel)));
        return list;
    }

    private PaymentMethod buildPaymentMethod(PaymentMethodDto model) {
        return paymentMethodBuilderStrategy.createFromModel(model);
    }
}
