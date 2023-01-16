package org.pmf.services.office.modules.orderpackage.domain.event;

import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageView;

public class OrderPackageCreatedEvent {
    private final OrderPackageView orderPackage;

    public OrderPackageCreatedEvent(OrderPackageView orderPackage) {
        this.orderPackage = orderPackage;
    }

    public OrderPackageView getOrderPackage() {
        return orderPackage;
    }
}
