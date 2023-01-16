package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class OrderPackageCollectionPresenterTests {

    OrderPackageCollectionPresenter orderPackageCollectionPresenter;

    public OrderPackageCollectionPresenterTests() {
        var orderPackagePresenter = Mockito.mock(OrderPackagePresenter.class);
        this.orderPackageCollectionPresenter = new OrderPackageCollectionPresenter(orderPackagePresenter);
    }

    @Test
    void testPresent() {
        var orderPackageMock_1 = Mockito.mock(OrderPackage.class);
        var orderPackageMock_2 = Mockito.mock(OrderPackage.class);
        var listOrderPackages = Arrays.asList(orderPackageMock_1, orderPackageMock_2);
        var orderPackageCollectionView = orderPackageCollectionPresenter.present(listOrderPackages);

        Assertions.assertNotNull(orderPackageCollectionView);
    }
}
