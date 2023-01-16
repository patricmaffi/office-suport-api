package org.pmf.services.office.modules.orderpackage.application.command;

import org.pmf.services.office.modules.orderpackage.application.dto.OrderPackageDto;

public class CreateOrderPackage {
    private final OrderPackageDto orderPackage;

    public CreateOrderPackage(OrderPackageDto orderPackage) {
        this.orderPackage = orderPackage;
    }

    public OrderPackageDto getOrderPackage() {
        return orderPackage;
    }
}
