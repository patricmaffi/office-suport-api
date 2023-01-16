package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackageCollectionPresenter implements Presenter {

    OrderPackagePresenter orderPackagePresenter;

    @Autowired
    public OrderPackageCollectionPresenter(
            OrderPackagePresenter orderPackagePresenter
    ) {
        this.orderPackagePresenter = orderPackagePresenter;
    }

    public List<OrderPackageView> present(List<OrderPackage> orderPackages) {
        var collection = new ArrayList<OrderPackageView>();
        orderPackages.forEach(orderPackage -> collection.add(orderPackagePresenter.present(orderPackage)));

        return collection;
    }
}
