package org.pmf.services.office.modules.orderpackage.application.command_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.services.office.modules.orderpackage.application.command.AddOrderToOrderPackage;
import org.pmf.services.office.modules.orderpackage.application.presenter.OrderPackagePresenter;
import org.pmf.services.office.modules.orderpackage.application.query.GetOrderPackage;
import org.pmf.services.office.modules.orderpackage.application.query_handler.GetOrderPackageHandler;
import org.pmf.services.office.modules.orderpackage.domain.Order;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.pmf.services.office.modules.orderpackage.domain.factory.OrderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddOrderToOrderPackageHandler {
    private static final Logger logger = LogManager.getLogger(AddOrderToOrderPackageHandler.class);
    OrderPackageRepository repository;
    OrderPackagePresenter orderPackagePresenter;
    OrderFactory orderFactory;
    GetOrderPackageHandler getOrderPackageHandler;

    @Autowired
    public AddOrderToOrderPackageHandler(OrderPackageRepository repository
            , OrderPackagePresenter orderPackagePresenter, OrderFactory orderFactory
            , GetOrderPackageHandler getOrderPackageHandler) {
        this.repository = repository;
        this.orderPackagePresenter = orderPackagePresenter;
        this.orderFactory = orderFactory;
        this.getOrderPackageHandler = getOrderPackageHandler;
    }

    public OrderPackage handle(AddOrderToOrderPackage command) {
        List<Order> orders = orderFactory.buildMany(command.getOrders());

        var query = new GetOrderPackage(command.getIdOrderPackage());
        var orderPackage = getOrderPackageHandler.handle(query);
        orders.forEach(orderPackage::addOrder);

        var orderPackageDB = repository.saveAndFlush(orderPackage);
        var view = orderPackagePresenter.present(orderPackageDB);
        return orderPackageDB;
    }
}
