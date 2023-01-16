package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.application.dto.OrderPackageDto;
import org.pmf.services.office.modules.orderpackage.domain.Order;
import org.pmf.services.office.modules.orderpackage.domain.ProviderRelation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPackageFactoryTests {

    @Mock
    OrderFactory orderFactory;

    @InjectMocks
    OrderPackageFactory orderPackageFactory;

    static OrderPackageDto modelSample() {
        return new OrderPackageDto(
                "FooCompany",
                UUID.randomUUID(),
                ProviderRelation.BILLED,
                new ArrayList<>(),
                new HashMap<>()
        );
    }

    @Test
    void testBuild() {
        var orderDto = modelSample();
        var orderPackage = orderPackageFactory.build(orderDto);

        Assertions.assertEquals(orderPackage.getCompany(), orderDto.getCompany());
        Assertions.assertTrue(orderPackage.orders().isEmpty());
        Assertions.assertTrue(orderPackage.metadata().isEmpty());
    }

    @Test
    void testBuildWithOrders() {
        var orderpackageDto = modelSample();

        List<Order> mockOrders = Arrays.asList(Mockito.mock(Order.class), Mockito.mock(Order.class));

        when(orderFactory.buildMany(orderpackageDto.getOrders())).thenReturn(mockOrders);
        var orderPackage = orderPackageFactory.build(orderpackageDto);

        Assertions.assertEquals(orderPackage.getCompany(), orderpackageDto.getCompany());
        Assertions.assertFalse(orderPackage.orders().isEmpty());
        Assertions.assertTrue(orderPackage.metadata().isEmpty());
    }

    @Test
    void testBuildMany() {
        var orderPackageDto_1 = modelSample();
        var orderPackageDto_2 = modelSample();

        var list = new ArrayList<OrderPackageDto>();
        list.add(orderPackageDto_1);
        list.add(orderPackageDto_2);

        var orderPackages = orderPackageFactory.buildMany(list);

        Assertions.assertFalse(orderPackages.isEmpty());
    }
}
