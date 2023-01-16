package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.ItemDto;
import org.pmf.services.office.modules.orderpackage.domain.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemFactory implements Factory<ItemDto, Item> {

    protected AdjustmentFactory adjustmentFactory;

    public ItemFactory(AdjustmentFactory adjustmentFactory) {
        this.adjustmentFactory = adjustmentFactory;
    }

    @Override
    public Item build(ItemDto model) {
        return new Item(
                model.getName(),
                model.getItemCode(),
                new Money(model.getPriceUnit().getCurrency(), model.getPriceUnit().getAmount()),
                model.getQuantity(),
                adjustmentFactory.buildMany(model.getAdjustments()),
                model.getMetadata()
        );
    }

    @Override
    public List<Item> buildMany(List<ItemDto> models) {
        List<Item> list = new ArrayList<>();
        models.forEach((ItemDto itemModel) -> list.add(build(itemModel)));
        return list;
    }
}
