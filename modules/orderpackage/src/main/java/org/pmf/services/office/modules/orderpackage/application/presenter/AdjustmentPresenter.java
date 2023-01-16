package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.pmf.services.office.modules.orderpackage.application.view.AdjustmentView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdjustmentPresenter {

    public List<AdjustmentView> presentMany(List<Adjustment> adjustments) {
        var adjustmentsView = new ArrayList<AdjustmentView>();
        adjustments.forEach(adjustment -> adjustmentsView.add(present(adjustment)));
        return adjustmentsView;
    }

    public AdjustmentView present(Adjustment adjustment) {
        return new AdjustmentView()
                .withType(adjustment.getType())
                .withTotal(adjustment.getTotal())
                .withMetadata(adjustment.getMetadata());
    }
}
