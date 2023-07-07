package org.example.application.service;

import org.example.application.valueObject.Money;
import org.example.in.command.CreditCheckCommand;

import java.math.BigDecimal;
import java.util.HashMap;

public interface PriceCalculatorService {
    public HashMap<String, BigDecimal> finalValue(CreditCheckCommand command);
}
