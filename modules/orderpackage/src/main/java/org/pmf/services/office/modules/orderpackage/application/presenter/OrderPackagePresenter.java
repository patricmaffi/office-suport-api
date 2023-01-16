package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackagePresenter implements Presenter {

    OrderPresenter orderPresenter;

    @Autowired
    public OrderPackagePresenter(OrderPresenter orderPresenter) {
        this.orderPresenter = orderPresenter;
    }

    public OrderPackageView present(OrderPackage orderPackage) {
//        final List<Number> revisions = reader.getRevisions(OrderPackage.class, orderPackage.getOrderPackageCode());
//        var version = revisions.isEmpty() ? 1L : (long) (revisions.size() - 1);

        return new OrderPackageView()
                .withOrderPackageCode(orderPackage.getOrderPackageCode())
                .withGuid(orderPackage.getGuid())
                .withCorrelationId(orderPackage.getCorrelationId())
                .withCompany(orderPackage.getCompany())
                .withProviderRelation(orderPackage.getProviderRelation().toString())
                .withVersion(orderPackage.getVersion()+1)
                .withTotal(orderPackage.total())
                .withSubTotal(orderPackage.subTotal())
                .withOrders(orderPresenter.presentMany(orderPackage.orders()))
                .withMetadata(orderPackage.metadata())
                .withCreatedAt(orderPackage.getCreatedAt())
                .withUpdatedAt(orderPackage.getUpdatedAt());
    }

    public List<OrderPackageView> presentMany(List<OrderPackage> models) {

        List<OrderPackageView> list = new ArrayList<>();
        models.forEach((OrderPackage orderPackage) -> list.add(present(orderPackage)));
        return list;
    }
}
