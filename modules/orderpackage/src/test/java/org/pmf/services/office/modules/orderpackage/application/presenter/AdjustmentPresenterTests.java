package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.mockito.Mockito;

import java.util.Arrays;

class AdjustmentPresenterTests {

    AdjustmentPresenter adjustmentPresenter;

    public AdjustmentPresenterTests() {
        this.adjustmentPresenter = new AdjustmentPresenter();
    }

    @Test
    void testPresent() {
        var adjustmentMock = Mockito.mock(Adjustment.class);

        var adjustmentView = adjustmentPresenter.present(adjustmentMock);

        Assertions.assertNotNull(adjustmentView);
        Assertions.assertNull(adjustmentView.getTotal());
        Assertions.assertNull(adjustmentView.getType());
        Assertions.assertEquals(0, adjustmentView.getMetadata().size());
    }

    @Test
    void testPresentMany() {
        var adjustmentMock_1 = Mockito.mock(Adjustment.class);
        var adjustmentMock_2 = Mockito.mock(Adjustment.class);
        var adjustments = Arrays.asList(adjustmentMock_1, adjustmentMock_2);

        var adjustmentViews = adjustmentPresenter.presentMany(adjustments);
        Assertions.assertEquals(2, adjustmentViews.size());
    }
}
