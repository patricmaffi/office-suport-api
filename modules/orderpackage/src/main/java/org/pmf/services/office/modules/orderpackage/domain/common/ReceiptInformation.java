package org.pmf.services.office.modules.orderpackage.domain.common;

public class ReceiptInformation {

    private String ssn;
    private PersonType personType;
    private String fantasyName;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String state;
    private String postalCode;
    private String municipalCode;
    private String municipalSubscription;


    public ReceiptInformation() {
    }

    public ReceiptInformation(
            String ssn,
            PersonType personType,
            String fantasyName,
            String address,
            String number,
            String complement,
            String neighborhood,
            String state,
            String postalCode,
            String municipalCode,
            String municipalSubscription
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

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof ReceiptInformation)) {
            return false;
        }

        var info = (ReceiptInformation) candidate;

        var equality = this.getAddress().equals(info.getAddress())
                && getState().equals(info.getState())
                && getNeighborhood().equals(info.getNeighborhood())
                && getPersonType().equals(info.getPersonType())
                && getPostalCode().equals(info.getPostalCode())
                && getSsn().equals(info.getSsn());

        if (this.getPersonType() == PersonType.LEGAL) {

            if(getMunicipalSubscription() != null) {
                equality = equality && getMunicipalSubscription().equals(info.getMunicipalSubscription());
            }

            return equality
                    && this.getFantasyName().equals(info.getFantasyName())
                    && this.getMunicipalCode().equals(info.getMunicipalCode());
        }

        return equality;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
