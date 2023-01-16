package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.pmf.services.office.modules.orderpackage.domain.common.PersonType;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class EntityInformationDto {

    private final Map<String, Object> metadata;

    @NotEmpty
    @Email
    private final String email;

    @NotBlank
    @Length(min = 2, max = 255)
    private final String name;

    private final String phone;

    private final String ssn;

    private final PersonType personType;

    @Valid
    private final ReceiptInformationDto receiptInformationDto;

    @Valid
    private final BankInformationDto bankInformationDto;

    @JsonCreator
    public EntityInformationDto(
            @JsonProperty() String email,
            @JsonProperty() String name,
            @JsonProperty() String phone,
            @JsonProperty() String ssn,
            @JsonProperty("personType") PersonType personType,
            @JsonProperty("receiptInformation") ReceiptInformationDto receiptInformationDto,
            @JsonProperty("bankInformation") BankInformationDto bankInformationDto,
            @JsonProperty() Map<String, Object> metadata
    ) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.receiptInformationDto = receiptInformationDto;
        this.bankInformationDto = bankInformationDto;
        this.metadata = metadata;
        this.ssn = ssn;
        this.personType = personType;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSsn() {return ssn;}

    public PersonType getPersonType() {return personType;}

    public ReceiptInformationDto getReceiptInformationDto() {
        return receiptInformationDto;
    }

    public BankInformationDto getBankInformationDto() {
        return bankInformationDto;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
