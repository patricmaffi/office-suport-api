package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.pmf.services.office.modules.orderpackage.application.validator.CpfCnpj;
import org.pmf.services.office.modules.orderpackage.domain.common.PersonType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ReceiptInformationDto {

    @NotBlank
    @Pattern(regexp = "^([0-9]{11}|[0-9]{14})$", message = "O cpf/cnpj deve conter somente números")
    @CpfCnpj
    private final String ssn;

    @NotNull
    private final PersonType personType;

    @Length(min = 1, max = 20)
    private final String fantasyName;

    @NotBlank
    @Length(min = 1, max = 255)
    private final String address;

    @NotBlank
    @Length(min = 1, max = 12)
    private final String number;

    @Length(min = 1, max = 255)
    private final String complement;

    @NotBlank
    @Length(min = 1, max = 40)
    private final String neighborhood;

    @NotBlank
    @Length(min = 2, max = 2)
    private final String state;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$", message = "O cep deve conter somente números com 8 dígitos")
    private final String postalCode;

    @NotBlank
    @Pattern(regexp = "^[0-9]{7}$", message = "O campo deve ser o código IBGE do estado. Somente números com 7 dígitos")
    @Length(min = 7, max = 7)
    private final String municipalCode;

    @Pattern(regexp = "^(\\s*|\\d+)$", message = "O campo deve conter somente números")
    private final String municipalSubscription;

    @JsonCreator
    public ReceiptInformationDto(
            @JsonProperty() String ssn,
            @JsonProperty() PersonType personType,
            @JsonProperty() String fantasyName,
            @JsonProperty() String address,
            @JsonProperty() String number,
            @JsonProperty() String complement,
            @JsonProperty() String neighborhood,
            @JsonProperty() String state,
            @JsonProperty() String postalCode,
            @JsonProperty() String municipalCode,
            @JsonProperty() String municipalSubscription
    ) {
        this.ssn = ssn;
        this.personType = personType;
        this.fantasyName = fantasyName;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.state = state;
        this.postalCode = postalCode;
        this.municipalCode = municipalCode;
        this.municipalSubscription = municipalSubscription;
    }

    public String getSsn() {
        return ssn;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getMunicipalCode() {
        return municipalCode;
    }

    public String getMunicipalSubscription() {
        return municipalSubscription;
    }
}
