package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.PaymentMethodDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;
import org.pmf.services.office.modules.orderpackage.application.dto.DocumentDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class TransferDto extends PaymentMethodDto {

    @Valid
    @NotNull
    private final DocumentDto document;

    @NotNull
    private final String type;

    public TransferDto(@JsonProperty("gateway") String gateway,
                       @JsonProperty("antecipated") Boolean antecipated,
                       @JsonProperty("document") DocumentDto document,
                       Map<String, Object> metadata) {
        super(gateway, antecipated, metadata);
        this.document = document;
        this.type = Methods.TRANSFER;
    }

    public String getType() {
        return type;
    }

    public DocumentDto getDocument() {
        return document;
    }
}
