package org.example;

import org.example.application.service.PriceCalculatorService;
import org.example.application.service.impl.PriceCalculatorServiceImpl;
import org.example.application.valueObject.Money;
import org.example.in.command.CreditCheckCommand;
import org.example.out.model.PersonType;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(new BigDecimal("500.00"));
        CreditCheckCommand command = new CreditCheckCommand(PersonType.valueOf("BRAZILIAN"), money);


        PriceCalculatorService priceCalculatorService = new PriceCalculatorServiceImpl();
        var finalPrice = priceCalculatorService.finalValue(command);

        System.out.println(finalPrice.toString());
    }
}