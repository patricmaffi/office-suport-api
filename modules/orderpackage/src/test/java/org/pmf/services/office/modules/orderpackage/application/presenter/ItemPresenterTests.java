package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.pmf.services.office.modules.orderpackage.domain.Item;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemPresenterTests {

    ItemPresenter itemPresenter;

    public ItemPresenterTests() {
        this.itemPresenter = new ItemPresenter(Mockito.mock(AdjustmentPresenter.class));
    }

    @Test
    void testPresent() {
        var itemMock = Mockito.mock(Item.class);
        var adjustmentMock = Mockito.mock(Adjustment.class);
        var adjustments = new ArrayList<Adjustment>();
        adjustments.add(adjustmentMock);
        when(itemMock.getAdjustments()).thenReturn(adjustments);

        var itemView = itemPresenter.present(itemMock);

        Assertions.assertNotNull(itemView);
        Assertions.assertNull(itemView.getPriceUnit());
        Assertions.assertEquals(0, itemView.getQuantity());
        Assertions.assertNull(itemView.getName());
        Assertions.assertNull(itemView.getItemCode());
        Assertions.assertNull(itemView.getId());
        Assertions.assertNull(itemView.getUpdatedAt());
        Assertions.assertNull(itemView.getCreatedAt());
        Assertions.assertEquals(0, itemView.getMetadata().size());
        Assertions.assertEquals(0, itemView.getAdjustments().size());
    }

    @Test
    void testPresentMany() {
        var itemMock_1 = Mockito.mock(Item.class);
        var itemMock_2 = Mockito.mock(Item.class);
        var itemViews = itemPresenter.presentMany(Arrays.asList(itemMock_1, itemMock_2));

        Assertions.assertEquals(2, itemViews.size());
    }
}
