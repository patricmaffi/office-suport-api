package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.application.view.OrderPackageSummaryView;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPackageSummaryPresenter implements Presenter {

    public OrderPackageSummaryView present(OrderPackage orderPackage) {
        return new OrderPackageSummaryView()
                .withId(orderPackage.getOrderPackageCode())
                .withId(orderPackage.getGuid())
                .withTotalSell(orderPackage.totalSell().toBigDecimal())
                .withTotalPurchase(orderPackage.totalPurchase().toBigDecimal())
                .withSubTotal(orderPackage.subTotal().toBigDecimal())
                .withTotal(orderPackage.total().toBigDecimal())
                .withTotalAdjustments(orderPackage.totalAdjustments().toBigDecimal())
                .withCompany(orderPackage.getCompany())
                .withIdTransaction(orderPackage.orders().get(0).getTransactions().get(0).getMetadata().get("idtransactions").toString());
    }
    public List<OrderPackageSummaryView> presentMany(List<OrderPackage> orderPackages) {
        List<OrderPackageSummaryView> list = new ArrayList<>();
        orderPackages.forEach((OrderPackage op) -> list.add(present(op)));
        return list;
    }
}
