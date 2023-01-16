package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.modules.orderpackage.application.dto.BankInformationDto;
import org.pmf.services.office.modules.orderpackage.domain.common.BankInformation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankInformationFactory implements Factory<BankInformationDto, BankInformation> {

    @Override
    public BankInformation build(BankInformationDto model) {

        if (model == null) return null;

        return new BankInformation(
                model.getBank(),
                model.getAccountNumber(),
                model.getBankAgency(),
                model.getAccountDigit(),
                model.getAgencyDigit(),
                model.getType()
        );
    }

    @Override
    public List<BankInformation> buildMany(List<BankInformationDto> models) {
        List<BankInformation> list = new ArrayList<>();
        if (models == null) return list;
        models.forEach((BankInformationDto entityModel) -> list.add(build(entityModel)));
        return list;
    }
}
