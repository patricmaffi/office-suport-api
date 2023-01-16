package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.ItemDto;
import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemFactoryTests {

    @Mock
    protected AdjustmentFactory adjustmentFactory;

    @InjectMocks
    protected ItemFactory itemFactory;

    public static ItemDto modelSample() {
        return new ItemDto("FooItem", "FooCode", new Money(10000), 1, new ArrayList<>(), new HashMap<>());
    }

    @Test
    void testBuild() {
        var itemDto = modelSample();
        var item = itemFactory.build(itemDto);

        Assertions.assertEquals(item.getName(), itemDto.getName());
        Assertions.assertEquals(item.getPriceUnit(), itemDto.getPriceUnit());

        Assertions.assertTrue(item.getAdjustments().isEmpty());
        Assertions.assertTrue(item.getMetadata().isEmpty());
    }

    @Test
    void testBuildWithAdjustments() {
        var itemDto = modelSample();

        List<Adjustment> adjustments = new ArrayList<>();
        var adjustment = Mockito.mock(Adjustment.class);
        adjustments.add(adjustment);
        when(adjustmentFactory.buildMany(itemDto.getAdjustments())).thenReturn(adjustments);

        var item = itemFactory.build(itemDto);

        Assertions.assertEquals(item.getName(), itemDto.getName());
        Assertions.assertEquals(item.getPriceUnit(), itemDto.getPriceUnit());

        Assertions.assertFalse(item.getAdjustments().isEmpty());
        Assertions.assertTrue(item.getMetadata().isEmpty());
    }

    @Test
    void testBuildMany() {
        var itemDto_1 = modelSample();
        var itemDto_2 = modelSample();

        var list = new ArrayList<ItemDto>();
        list.add(itemDto_1);
        list.add(itemDto_2);

        var items = this.itemFactory.buildMany(list);
        Assertions.assertFalse(items.isEmpty());
    }
}
