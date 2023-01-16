package org.pmf.services.office.core.value_object;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    private static final String DEFAULT_CURRENCY = "BRL";
    private String currency;
    private int amount;

    public Money() {
        this.currency = DEFAULT_CURRENCY;
        this.amount = 0;
    }

    public Money(int amount) {
        this.currency = DEFAULT_CURRENCY;
        this.amount = amount;
    }

    public Money(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money from(int value) {
        return new Money(value);
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public void sum(Money amount) {
        this.amount += amount.getAmount();
    }
    public void subtract(Money amount) {
        this.amount -= amount.getAmount();
    }

    public void sum(int amount) {
        this.amount += amount;
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(getAmount() / 100f).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {

        boolean isNegative = this.amount < 0;
        float newAmount = isNegative ? (this.amount * -1) : this.amount;

        var formatted = String.format("%s%.2f", this.currency, (newAmount / 100));

        if (isNegative) {
            formatted = "-" + formatted;
        }

        return formatted;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (!getClass().equals(other.getClass())) return false;
        var money = (Money) other;

        return getAmount() == (money).getAmount() && getCurrency().equals(money.getCurrency());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
