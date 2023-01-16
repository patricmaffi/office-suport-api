package org.pmf.services.office.modules.orderpackage.domain.common;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import java.util.Map;
import java.util.Objects;

@TypeDef(name = "json", typeClass = JsonStringType.class)
public class EntityInformation {

    Map<String, Object> metadata;
    private String email;
    private String name;
    private String phone;
    private String ssn;
    private PersonType personType;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private ReceiptInformation receiptInformation;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private BankInformation bankInformation;

    public EntityInformation() {
    }

    public EntityInformation(
            String email,
            String name,
            String phone,
            String ssn,
            PersonType personType,
            ReceiptInformation receiptInformation,
            BankInformation bankInformation,
            Map<String, Object> metadata
    ) {
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

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof EntityInformation)) {
            return false;
        }

        var entity = (EntityInformation) candidate;

        return Objects.equals(this.getEmail(), entity.getEmail())
                && Objects.equals(this.getName(), entity.getName())
                && Objects.equals(this.getPhone(), entity.getPhone())
                && Objects.equals(this.getSsn(), entity.getSsn())
                && Objects.equals(this.getPersonType(), entity.getPersonType())
                && Objects.equals(this.getReceiptInformation(), entity.getReceiptInformation())
                && Objects.equals(this.getMetadata(), entity.getMetadata());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
