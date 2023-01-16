package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.application.dto.OrderDto;
import org.pmf.services.office.modules.orderpackage.application.dto.OrderTypeDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class OrderFactoryTests {

    @Mock
    protected EntityInformationFactory entityInformationFactory;

    @Mock
    protected ItemFactory itemFactory;

    @Mock
    protected TransactionFactory transactionFactory;

    @InjectMocks
    OrderFactory orderFactory;

    public static OrderDto modelSample() {

        var entityInformationDto = EntityInformationFactoryTests.modelSample();

        return new OrderDto(
                OrderTypeDto.PURCHASE,
                entityInformationDto,
                entityInformationDto,
                new ArrayList<>(),
                new ArrayList<>(),
                new HashMap<>()
        );
    }

    @Test
    void testBuild() {
        OrderDto orderDto = modelSample();
        var order = orderFactory.build(orderDto);

        Assertions.assertEquals(order.getType().toString(), orderDto.getType().toString());
    }

    @Test
    void testBuildMany() {
        var order_1 = modelSample();
        var order_2 = modelSample();

        var list = new ArrayList<OrderDto>();
        list.add(order_1);
        list.add(order_2);

        var orders = orderFactory.buildMany(list);

        Assertions.assertFalse(orders.isEmpty());
    }
}
