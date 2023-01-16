package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.Item;
import org.pmf.services.office.modules.orderpackage.application.view.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemPresenter {

    AdjustmentPresenter adjustmentPresenter;

    @Autowired
    public ItemPresenter(AdjustmentPresenter adjustmentPresenter) {
        this.adjustmentPresenter = adjustmentPresenter;
    }

    List<ItemView> presentMany(List<Item> items) {
        var itemsView = new ArrayList<ItemView>();
        items.forEach(item -> itemsView.add(present(item)));
        return itemsView;
    }

    ItemView present(Item item) {
        var view = new ItemView()
                .withId(item.getId())
                .withName(item.getName())
                .withItemCode(item.getItemCode())
                .withPriceUnit(item.getPriceUnit())
                .withTotal(item.getTotal())
                .withSubtotal(item.getSubTotal())
                .withQuantity(item.getQuantity())
                .withMetadata(item.getMetadata())
                .withCreatedAt(item.getCreatedAt())
                .withUpdatedAt(item.getUpdatedAt());

        if (!item.getAdjustments().isEmpty()) {
            view.withAdjustments(adjustmentPresenter.presentMany(item.getAdjustments()));
        }

        return view;
    }
}
