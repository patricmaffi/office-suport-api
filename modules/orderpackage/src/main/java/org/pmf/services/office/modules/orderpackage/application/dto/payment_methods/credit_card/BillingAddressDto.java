package org.pmf.services.office.modules.orderpackage.application.dto.payment_methods.credit_card;

public class BillingAddressDto {

    private final String address;
    private final String number;
    private final String complement;
    private final String city;
    private final String state;
    private final String postalCode;

    public BillingAddressDto(
            String address,
            String number,
            String complement,
            String city,
            String state,
            String postalCode
    ) {
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
