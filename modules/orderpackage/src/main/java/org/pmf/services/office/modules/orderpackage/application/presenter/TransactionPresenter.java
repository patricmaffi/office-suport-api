package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.Transaction;
import org.pmf.services.office.modules.orderpackage.application.view.TransactionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionPresenter {

    EntityInformationPresenter entityInformationPresenter;

    AdjustmentPresenter adjustmentPresenter;

    @Autowired
    public TransactionPresenter(EntityInformationPresenter entityInformationPresenter, AdjustmentPresenter adjustmentPresenter) {
        this.entityInformationPresenter = entityInformationPresenter;
        this.adjustmentPresenter = adjustmentPresenter;

    }

    List<TransactionView> presentMany(List<Transaction> transactions) {
        var transactionsView = new ArrayList<TransactionView>();

        if(transactions.isEmpty()) {
            return transactionsView;
        }

        transactions.forEach(transaction -> transactionsView.add(present(transaction)));
        return transactionsView;
    }

    TransactionView present(Transaction transaction) {
        var view = new TransactionView()
                .withId(transaction.getId())
                .withPaymentMethod(transaction.getPaymentMethod())
                .withBuyer(entityInformationPresenter.present(transaction.getPayer()))
                .withSeller(entityInformationPresenter.present(transaction.getPayee()))
                .withStatus(transaction.getStatus())
                .withTotal(transaction.getTotal())
                .withMetadata(transaction.getMetadata())
                .withCreatedAt(transaction.getCreatedAt())
                .withUpdatedAt(transaction.getUpdatedAt());

        if (!transaction.getAdjustments().isEmpty()) {
            view.withAdjustments(adjustmentPresenter.presentMany(transaction.getAdjustments()));
        }

        return view;
    }
}
