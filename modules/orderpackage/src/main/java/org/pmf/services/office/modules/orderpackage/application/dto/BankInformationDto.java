package org.pmf.services.office.modules.orderpackage.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pmf.services.office.modules.orderpackage.domain.common.BankAccountType;

import javax.validation.constraints.NotBlank;

public class BankInformationDto {

    @NotBlank
    private final String bank;

    @NotBlank
    private final String accountNumber;

    private final String bankAgency;
    private final String accountDigit;
    private final String agencyDigit;
    private final BankAccountType type;

    @JsonCreator
    public BankInformationDto(
            @JsonProperty("bank") String bank,
            @JsonProperty("accountNumber")String accountNumber,
            @JsonProperty("bankAgency")String bankAgency,
            @JsonProperty("accountDigit")String accountDigit,
            @JsonProperty("agencyDigit") String agencyDigit,
            @JsonProperty("type") BankAccountType type
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
}
