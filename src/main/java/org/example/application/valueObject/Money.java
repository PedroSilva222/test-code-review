package org.example.application.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal value;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Boolean isGreaterThanZero() {
        return this.value != null && this.value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Boolean isGreaterThan(Money money) {
        return this.value != null && this.value.compareTo(money.getValue()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.value.add(money.getValue())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.value.subtract(money.getValue())));
    }

    public Money multiply(int quantity) {
        return new Money(setScale(this.value.multiply(BigDecimal.valueOf(quantity))));
    }

    public Money divide(int quantity) {
        return new Money(setScale(this.value.divide(BigDecimal.valueOf(quantity),  RoundingMode.HALF_UP)));
    }
    public BigDecimal calculateDiscount(BigDecimal subTotal, Double descount) {

        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
        BigDecimal decimalDiscountPercent = new BigDecimal(Double.toString(descount));

        BigDecimal discountAmount = subTotal.multiply(decimalDiscountPercent);
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalWithDiscount = subTotal.subtract(discountAmount);

        return totalWithDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}