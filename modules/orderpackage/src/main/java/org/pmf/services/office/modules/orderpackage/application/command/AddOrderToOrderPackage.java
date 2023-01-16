package org.pmf.services.office.modules.orderpackage.application.command;

import org.pmf.services.office.modules.orderpackage.application.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public class AddOrderToOrderPackage {
    private final List<OrderDto> orders;
    private final UUID idOrderPackage;

    public AddOrderToOrderPackage(UUID idOrderPackage, List<OrderDto> orders) {
        this.orders = orders;
        this.idOrderPackage = idOrderPackage;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
    public UUID getIdOrderPackage() {return idOrderPackage;}
}
