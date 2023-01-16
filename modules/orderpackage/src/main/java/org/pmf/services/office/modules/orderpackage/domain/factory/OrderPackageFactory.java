package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.modules.orderpackage.application.dto.OrderPackageDto;
import org.pmf.services.office.modules.orderpackage.domain.Order;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackageFactory implements Factory<OrderPackageDto, OrderPackage> {

    protected OrderFactory orderFactory;

    @Autowired
    public OrderPackageFactory(OrderFactory orderFactory) {
        this.orderFactory = orderFactory;
    }

    public OrderPackage build(OrderPackageDto model) {
        var orderPackage = new OrderPackage(
                model.getCompany(),
                model.getCorrelationId(),
                model.getProviderRelation(),
                model.getMetadata()
        );
        List<Order> orders = orderFactory.buildMany(model.getOrders());
        orders.forEach(orderPackage::addOrder);
        return orderPackage;
    }

    public List<OrderPackage> buildMany(List<OrderPackageDto> models) {
        List<OrderPackage> list = new ArrayList<>();
        models.forEach((OrderPackageDto orderPackageDto) -> list.add(build(orderPackageDto)));
        return list;
    }
}
