package org.example.application.service.impl;

import org.example.application.service.PriceCalculatorService;
import org.example.application.valueObject.Money;
import org.example.in.command.CreditCheckCommand;
import org.example.out.model.PersonType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceCalculatorServiceImplTest {
    private PriceCalculatorService priceCalcualtor;

    @BeforeAll
    void setUp() {
        this.priceCalcualtor = new PriceCalculatorServiceImpl();
    }

    @Test
    @DisplayName("Should be able to calculate the final price with valid params for Brazilian")
    void finalPriceBrazilian() {
        Money money = new Money(new BigDecimal("500.00"));
        CreditCheckCommand command = new CreditCheckCommand(PersonType.valueOf("BRAZILIAN"), money);

        var result = priceCalcualtor.finalValue(command);

        Assertions.assertEquals(result.get("INSTALLMENT 3x" ), new BigDecimal("166.67"));
        Assertions.assertEquals(result.get("INCASHDISCOUNT" ), new BigDecimal("490.00"));
    }

    @Test
    @DisplayName("Should be able to calculate the final price with valid params for Brazilian")
    void finalPriceNonBrazilian() {
        Money money = new Money(new BigDecimal("500.00"));
        CreditCheckCommand command = new CreditCheckCommand(PersonType.valueOf("NONBRAZILIAN"), money);

        var result = priceCalcualtor.finalValue(command);

        Assertions.assertEquals(result.get("INCASHDISCOUNT" ), new BigDecimal("450.00"));
    }

    @Test
    @DisplayName("Should not to be able to calculate the final price with invalid params < 0")
    void finalPriceErrValueLessThanZero() {
        Money money = new Money(new BigDecimal("00.00"));
        CreditCheckCommand command = new CreditCheckCommand(PersonType.valueOf("BRAZILIAN"), money);

        Throwable err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            priceCalcualtor.finalValue(command);
        });

        Assertions.assertEquals(err.getMessage(), "The Amount cannot be Zero or less one!");

    }


}