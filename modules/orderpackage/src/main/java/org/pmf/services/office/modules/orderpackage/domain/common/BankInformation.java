package org.pmf.services.office.modules.orderpackage.domain.common;

import java.io.Serializable;

public class BankInformation implements Serializable {
    private String bank;
    private String accountNumber;
    private String bankAgency;
    private String accountDigit;
    private String agencyDigit;
    private BankAccountType type;

    private BankInformation() {
    }

    public BankInformation(
            String bank,
            String accountNumber,
            String bankAgency,
            String accountDigit,
            String agencyDigit,
            BankAccountType type
    ) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.bankAgency = bankAgency;
        this.accountDigit = accountDigit;
        this.agencyDigit = agencyDigit;
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankAgency() {
        return bankAgency;
    }

    public String getAccountDigit() {
        return accountDigit;
    }

    public String getAgencyDigit() {
        return agencyDigit;
    }

    public BankAccountType getType() {
        return type;
    }

    @Override
    public boolean equals(Object candidate) {

        if (!(candidate instanceof BankInformation)) {
            return false;
        }

        var document = (BankInformation) candidate;

        return this.accountNumber.equals(document.getAccountNumber()) && this.bank.equals(document.getBank());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}