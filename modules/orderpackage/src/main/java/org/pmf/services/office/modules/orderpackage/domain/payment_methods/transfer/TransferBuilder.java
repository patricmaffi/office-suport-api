package org.pmf.services.office.modules.orderpackage.domain.payment_methods.transfer;

import org.pmf.services.office.modules.orderpackage.domain.common.Document;
import org.pmf.services.office.modules.orderpackage.domain.common.DocumentType;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.transfer.TransferDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.PaymentMethodBuilder;
import org.springframework.stereotype.Component;


@Component
public class TransferBuilder implements PaymentMethodBuilder {

    public String getType() {
        return Methods.TRANSFER;
    }

    public Transfer build(PaymentMethodDto model) {
        TransferDto transferModel = (TransferDto) model;
        return new Transfer(model.getGateway(), transferModel.getAntecipated(), buildDocument(transferModel.getDocument()));
    }

    Document buildDocument(DocumentDto model) {
        return new Document(model.getDocument(), DocumentType.valueOf(model.getPersonType().toString()));
    }
}
