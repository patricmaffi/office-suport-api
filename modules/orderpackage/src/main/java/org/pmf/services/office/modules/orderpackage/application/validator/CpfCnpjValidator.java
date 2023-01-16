package org.pmf.services.office.modules.orderpackage.application.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    private static final int[] weightCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calculateDigit(String str, int[] weight) {
        var sum = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            var digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) return false;

        int digit1 = calculateDigit(cnpj.substring(0, 12), weightCNPJ);
        int digit2 = calculateDigit(cnpj.substring(0, 12) + digit1, weightCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digit1 + digit2);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return (isValidCpf(value) || isValidCNPJ(value))
                && !Pattern.matches("^(\\d)\\1{10}$|^(\\d)\\2{13}$", value);
    }

    private boolean isValidCpf(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) return false;

        int digit1 = calculateDigit(cpf.substring(0, 9), weightCPF);
        int digit2 = calculateDigit(cpf.substring(0, 9) + digit1, weightCPF);
        return cpf.equals(cpf.substring(0, 9) + digit1 + digit2);
    }
}