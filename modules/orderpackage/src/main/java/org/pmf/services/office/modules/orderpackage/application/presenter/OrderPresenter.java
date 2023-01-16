package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.Order;
import org.pmf.services.office.modules.orderpackage.application.view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPresenter implements Presenter {

    ItemPresenter itemPresenter;
    TransactionPresenter transactionPresenter;
    EntityInformationPresenter entityInformationPresenter;

    @Autowired
    public OrderPresenter(ItemPresenter itemPresenter, TransactionPresenter transactionPresenter, EntityInformationPresenter entityInformationPresenter) {
        this.itemPresenter = itemPresenter;
        this.transactionPresenter = transactionPresenter;
        this.entityInformationPresenter = entityInformationPresenter;
    }

    public List<OrderView> presentMany(List<Order> orders) {
        var ordersView = new ArrayList<OrderView>();
        orders.forEach(item -> ordersView.add(present(item)));
        return ordersView;
    }

    public OrderView present(Order order) {
        return new OrderView()
                .withId(order.getId())
                .withType(order.getType())
                .withCustomer(entityInformationPresenter.present(order.getCustomer()))
                .withMerchant(entityInformationPresenter.present(order.getMerchant()))
                .withItems(itemPresenter.presentMany(order.getItems()))
                .withTransactions(transactionPresenter.presentMany(order.getTransactions()))
                .withSubTotal(order.getSubTotal())
                .withTotal(order.getTotal())
                .withTotalAdjustments(order.getTotalAdjustments())
                .withMetadata(order.getMetadata())
                .withCreatedAt(order.getCreatedAt());
    }
}
