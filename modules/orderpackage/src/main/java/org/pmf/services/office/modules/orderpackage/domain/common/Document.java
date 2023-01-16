package org.pmf.services.office.modules.orderpackage.domain.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document implements Serializable {
    private String value;
    private DocumentType personType;

    private Document() {
    }

    public Document(String value, DocumentType personType) {
        this.value = value;
        this.personType = personType;
    }

    public String getValue() {
        return value;
    }

    public DocumentType getPersonType() {
        return personType;
    }

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof Document)) {
            return false;
        }

        var document = (Document) candidate;

        return this.value.equals(document.getValue()) && this.personType.equals(document.getPersonType());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}