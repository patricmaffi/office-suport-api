package org.pmf.services.office.modules.orderpackage.domain.common;

public class Phone {

    private String phoneNumber;
    private PhoneType phoneType;
    private String countryCode;

    private Phone() {
    }

    public Phone(String phoneNumber) {
        var defaultCountryCode = "55";
        var defaultPhoneType = PhoneType.UNKNOWN;

        this.countryCode = defaultCountryCode;
        this.phoneType = defaultPhoneType;
        this.phoneNumber = phoneNumber;
    }

    public Phone(String phoneNumber, PhoneType phoneType) {
        this(phoneNumber);
        this.phoneType = phoneType;
    }

    public Phone(String phoneNumber, PhoneType phoneType, String countryCode) {
        this(phoneNumber);
        this.phoneType = phoneType;
        this.countryCode = countryCode;
    }

    public PhoneType type() {
        return phoneType;
    }

    public String countryCode() {
        return countryCode;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof Phone)) {
            return false;
        }

        var phone = (Phone) candidate;

        return this.phoneNumber().equals(phone.phoneNumber()) && this.type().equals(phone.type());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}