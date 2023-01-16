package org.pmf.services.office.modules.orderpackage.application.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.pmf.services.office.modules.orderpackage.domain.common.BankInformation;
import org.pmf.services.office.modules.orderpackage.domain.common.PersonType;
import org.pmf.services.office.modules.orderpackage.domain.common.ReceiptInformation;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityInformationView {

    private String email;
    private String name;
    private String phone;
    private String ssn;
    private PersonType personType;
    private ReceiptInformation receiptInformation;
    private BankInformation bankInformation;
    Map<String, Object> metadata;

    public EntityInformationView() {
    }

    public EntityInformationView(String email, String name, String phone
            , String ssn, PersonType personType
            , ReceiptInformation receiptInformation, BankInformation bankInformation
            , Map<String, Object> metadata) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.ssn = ssn;
        this.personType = personType;
        this.receiptInformation = receiptInformation;
        this.bankInformation = bankInformation;
        this.metadata = metadata;
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

    public ReceiptInformation getReceiptInformation() {
        return receiptInformation;
    }

    public BankInformation getBankInformation() {
        return bankInformation;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
