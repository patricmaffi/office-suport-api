package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.modules.orderpackage.application.dto.OrderDto;
import org.pmf.services.office.modules.orderpackage.domain.Item;
import org.pmf.services.office.modules.orderpackage.domain.Order;
import org.pmf.services.office.modules.orderpackage.domain.OrderType;
import org.pmf.services.office.modules.orderpackage.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderFactory implements Factory<OrderDto, Order> {

    protected EntityInformationFactory entityInformationFactory;
    protected ItemFactory itemFactory;
    protected TransactionFactory transactionFactory;

    public OrderFactory(
            EntityInformationFactory entityInformationFactory,
            ItemFactory itemFactory,
            TransactionFactory transactionFactory
    ) {
        this.entityInformationFactory = entityInformationFactory;
        this.itemFactory = itemFactory;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public Order build(OrderDto model) {

        var order = new Order(
                OrderType.valueOf(model.getType().toString()),
                model.getMetadata()
        );

        order.setCustomer(entityInformationFactory.build(model.getCustomer()));
        order.setMerchant(entityInformationFactory.build(model.getMerchant()));

        List<Item> items = itemFactory.buildMany(model.getItems());
        items.forEach(order::addItem);

        List<Transaction> transactions = transactionFactory.buildMany(model.getTransactions());
        transactions.forEach(order::addTransaction);

        return order;
    }

    @Override
    public List<Order> buildMany(List<OrderDto> models) {
        List<Order> list = new ArrayList<>();

        models.forEach((OrderDto orderModel) -> list.add(build(orderModel)));

        return list;
    }
}
