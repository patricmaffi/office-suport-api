package org.pmf.services.office.modules.orderpackage.application.presenter;

import org.pmf.services.office.modules.orderpackage.domain.common.EntityInformation;
import org.pmf.services.office.modules.orderpackage.application.view.EntityInformationView;
import org.springframework.stereotype.Component;


@Component
public class EntityInformationPresenter {

    EntityInformationView present(EntityInformation entityInformation) {
        return new EntityInformationView(
                entityInformation.getEmail(),
                entityInformation.getName(),
                entityInformation.getPhone(),
                entityInformation.getSsn(),
                entityInformation.getPersonType(),
                entityInformation.getReceiptInformation(),
                entityInformation.getBankInformation(),
                entityInformation.getMetadata()
        );
    }
}
