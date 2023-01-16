package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.modules.orderpackage.domain.common.ReceiptInformation;
import org.pmf.services.office.modules.orderpackage.application.dto.ReceiptInformationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiptInformationFactory implements Factory<ReceiptInformationDto, ReceiptInformation> {
    @Override
    public ReceiptInformation build(ReceiptInformationDto model) {

        if (model == null) return null;

        return new ReceiptInformation(
                model.getSsn(),
                model.getPersonType(),
                model.getFantasyName(),
                model.getAddress(),
                model.getNumber(),
                model.getComplement(),
                model.getNeighborhood(),
                model.getState(),
                model.getPostalCode(),
                model.getMunicipalCode(),
                model.getMunicipalSubscription()
        );
    }

    @Override
    public List<ReceiptInformation> buildMany(List<ReceiptInformationDto> models) {
        List<ReceiptInformation> list = new ArrayList<>();
        if (models == null) return list;
        models.forEach((ReceiptInformationDto entityModel) -> list.add(build(entityModel)));
        return list;
    }
}
