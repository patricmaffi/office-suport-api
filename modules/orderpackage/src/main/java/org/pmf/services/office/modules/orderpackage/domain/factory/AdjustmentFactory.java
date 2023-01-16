package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.core.value_object.Money;
import org.pmf.services.office.modules.orderpackage.application.dto.AdjustmentDto;
import org.pmf.services.office.modules.orderpackage.domain.Adjustment;
import org.pmf.services.office.modules.orderpackage.domain.AdjustmentType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdjustmentFactory implements Factory<AdjustmentDto, Adjustment> {

    @Override
    public Adjustment build(AdjustmentDto model) {
        return new Adjustment(
                AdjustmentType.valueOf(model.getType().toString()),
                new Money(model.getTotal().getCurrency(), model.getTotal().getAmount()),
                model.getMetadata()
        );
    }

    @Override
    public List<Adjustment> buildMany(List<AdjustmentDto> models) {
        List<Adjustment> list = new ArrayList<>();
        if (models == null) return list;
        models.forEach((AdjustmentDto adjustmentModel) -> list.add(build(adjustmentModel)));
        return list;
    }
}
