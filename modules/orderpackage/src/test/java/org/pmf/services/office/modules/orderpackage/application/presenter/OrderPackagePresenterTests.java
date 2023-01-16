package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.ProviderRelation;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class OrderPackagePresenterTests {

    OrderPackagePresenter orderPackagePresenter;
    OrderPresenter orderPresenter;
    EntityManager em;

    public OrderPackagePresenterTests() {

        this.orderPresenter = Mockito.mock(OrderPresenter.class);
        this.em = Mockito.mock(EntityManager.class);

        this.orderPackagePresenter = new OrderPackagePresenter(
                orderPresenter
        );
    }

    @Test
    void testPresent() {

        var orderPackage = new OrderPackage("fooCompany", UUID.randomUUID(), ProviderRelation.BILLED);

        var orderPackageView = orderPackagePresenter.present(orderPackage);

        Assertions.assertNotNull(orderPackageView);
        Assertions.assertTrue(orderPackageView.getOrders().isEmpty());
        Assertions.assertEquals("fooCompany", orderPackageView.getCompany());
        Assertions.assertNotNull(orderPackageView.getCreatedAt());
        Assertions.assertTrue(orderPackageView.getMetadata().isEmpty());
    }
}
