package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.billed.BilledDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit.CreditDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card.CreditCardDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.intercompany.InterCompanyDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.resale.ResaleDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.stock.StockDto;
import org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.transfer.TransferDto;
import org.pmf.services.office.modules.orderpackage.domain.payment_methods.Methods;

import javax.validation.Valid;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(name = Methods.CREDIT_CARD, value = CreditCardDto.class),
                @JsonSubTypes.Type(name = Methods.TRANSFER, value = TransferDto.class),
                @JsonSubTypes.Type(name = Methods.CREDIT, value = CreditDto.class),
                @JsonSubTypes.Type(name = Methods.INTERCOMPANY, value = InterCompanyDto.class),
                @JsonSubTypes.Type(name = Methods.BILLED, value = BilledDto.class),
                @JsonSubTypes.Type(name = Methods.RESALE, value = ResaleDto.class),
                @JsonSubTypes.Type(name = Methods.STOCK, value = StockDto.class)
        })
@Valid
public abstract class PaymentMethodDto {

    private String gateway;
    private Boolean antecipated = false;

    protected PaymentMethodDto(String gateway, Boolean antecipated, Map<String, Object> metadata) {
        this.gateway = gateway == null ? "" : gateway;
        this.antecipated = antecipated != null && antecipated;
        this.metadata = metadata;
    }

    public String getGateway() {
        return gateway;
    }

    public abstract String getType();

    public Boolean getAntecipated() {return antecipated;}

    Map<String, Object> metadata;
    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
