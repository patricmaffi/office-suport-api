package org.pmf.services.office.modules.orderpackage.domain.factory;

import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;
import org.pmf.services.office.modules.orderpackage.application.dto.EntityInformationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EntityInformationFactory implements Factory<EntityInformationDto, EntityInformation> {

    protected ReceiptInformationFactory receiptInformationFactory;
    protected BankInformationFactory bankInformationFactory;

    public EntityInformationFactory(
            ReceiptInformationFactory receiptInformationFactory,
            BankInformationFactory bankInformationFactory
    ) {
        this.receiptInformationFactory = receiptInformationFactory;
        this.bankInformationFactory = bankInformationFactory;
    }

    @Override
    public EntityInformation build(EntityInformationDto model) {
        return new EntityInformation(
                model.getEmail(),
                model.getName(),
                model.getPhone(),
                model.getSsn(),
                model.getPersonType(),
                model.getReceiptInformationDto() != null ? receiptInformationFactory.build(model.getReceiptInformationDto()) : null,
                model.getBankInformationDto() != null ? bankInformationFactory.build(model.getBankInformationDto()) : null,
                model.getMetadata()
        );
    }

    @Override
    public List<EntityInformation> buildMany(List<EntityInformationDto> models) {
        List<EntityInformation> list = new ArrayList<>();
        models.forEach((EntityInformationDto entityModel) -> list.add(build(entityModel)));
        return list;
    }
}
