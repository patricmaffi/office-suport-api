package org.pmf.services.office.modules.orderpackage.domain.common;

public class Address {

    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;

    private Address() {
    }

    public Address(
            String street,
            String number,
            String complement,
            String city,
            String state,
            String postalCode
    ) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof Address)) {
            return false;
        }

        var address = (Address) candidate;

        return this.street.equals(address.getStreet())
                && this.number.equals(address.getNumber())
                && this.complement.equals(address.getComplement())
                && this.city.equals(address.getCity())
                && this.state.equals(address.getState())
                && this.postalCode.equals(address.getPostalCode());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
