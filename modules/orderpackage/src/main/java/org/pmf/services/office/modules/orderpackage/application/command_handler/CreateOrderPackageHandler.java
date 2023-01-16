package org.pmf.services.office.modules.orderpackage.application.command_handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pmf.services.office.modules.orderpackage.application.command.CreateOrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.factory.OrderPackageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderPackageHandler {
    private static final Logger logger = LogManager.getLogger(CreateOrderPackageHandler.class);
    CreateOrderPackageService service;
    OrderPackageFactory orderPackageFactory;

    @Autowired
    public CreateOrderPackageHandler(CreateOrderPackageService service, OrderPackageFactory orderPackageFactory) {
        this.service = service;
        this.orderPackageFactory = orderPackageFactory;
    }

    public OrderPackage handle(CreateOrderPackage command) {
        var orderPackage = orderPackageFactory.build(command.getOrderPackage());
        return service.CreateOrderPackage(orderPackage);
    }
}
