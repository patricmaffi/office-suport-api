package org.pmf.services.office.modules.orderpackage.domain.payment_methods.credit_card;

import java.io.Serializable;
import java.util.Objects;

public class BillingAddress implements Serializable {

    private String address;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;

    private BillingAddress() {
    }

    public BillingAddress(
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

    public boolean equals(Object candidate) {

        if (!(candidate instanceof BillingAddress)) {
            return false;
        }

        var candidateAddress = (BillingAddress) candidate;

        return Objects.equals(this.address, candidateAddress.getAddress())
                && Objects.equals(this.number, candidateAddress.getNumber())
                && Objects.equals(this.complement, candidateAddress.getComplement())
                && Objects.equals(this.city, candidateAddress.getCity())
                && Objects.equals(this.state, candidateAddress.getState())
                && Objects.equals(this.postalCode, candidateAddress.getPostalCode());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
