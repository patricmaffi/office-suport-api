package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.modules.orderpackage.application.validator.CpfCnpj;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DocumentDto {

    @NotBlank
    @Pattern(regexp = "^([0-9]{11}|[0-9]{14})$", message = "O cpf/cnpj deve conter somente números")
    @CpfCnpj
    private final String document;

    @Valid
    @NotNull
    private final DocumentTypeDto personType;

    @JsonCreator
    public DocumentDto(
            @JsonProperty("document") String document,
            @JsonProperty("personType") DocumentTypeDto personType
    ) {
        this.document = document;
        this.personType = personType;
    }

    public String getDocument() {
        return document;
    }

    public DocumentTypeDto getPersonType() {
        return personType;
    }
}