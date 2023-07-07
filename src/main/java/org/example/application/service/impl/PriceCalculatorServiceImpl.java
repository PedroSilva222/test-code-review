package org.example.application.service.impl;

import org.example.application.service.PriceCalculatorService;
import org.example.application.valueObject.Money;
import org.example.in.command.CreditCheckCommand;
import org.example.out.model.PersonType;

import java.math.BigDecimal;
import java.util.HashMap;

public class PriceCalculatorServiceImpl implements PriceCalculatorService {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final Double DISCOUNTBRAZILIAN = 0.02;

    public static final Integer PURCHASEINSTALLMENTBRAZILIAN = 3;
    public static final Double DISCOUNTNONBRAZILIAN = 0.10;

    @Override
    public HashMap<String, BigDecimal> finalValue(CreditCheckCommand command) {
        HashMap<String, BigDecimal> paymentOptions = new HashMap<>();
        Money money = new Money(command.getAmount().getValue());

        if (!command.getAmount().isGreaterThanZero()) {
            throw new IllegalArgumentException("The Amount cannot be Zero or less one!");
        }

        if (command.getPersonType().compareTo(PersonType.BRAZILIAN) == 0 &&
                command.getAmount().isGreaterThan(new Money(ONE_HUNDRED))) {

            BigDecimal inCashDiscount = money.calculateDiscount(command.getAmount().getValue(), DISCOUNTBRAZILIAN);

            Money installment = money.divide(PURCHASEINSTALLMENTBRAZILIAN);

            paymentOptions.put("INSTALLMENT 3x", installment.getValue());
            paymentOptions.put("INCASHDISCOUNT", new Money(inCashDiscount).getValue());

            return paymentOptions;
        }

        if (command.getPersonType().compareTo(PersonType.BRAZILIAN) == 0 ) {
            paymentOptions.put("INCASHDISCOUNT", command.getAmount().getValue());
        }

        BigDecimal inCashDiscount = money.calculateDiscount(command.getAmount().getValue(), DISCOUNTNONBRAZILIAN);

        paymentOptions.put("INCASHDISCOUNT", new Money(inCashDiscount).getValue());

        return paymentOptions;
    }
}
