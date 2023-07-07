package org.example.application.valueObject;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTest {
    private Money money;
    private Money moneyZero;

    @BeforeEach
    void setUp() {
        this.money =  new Money(new BigDecimal("500.00"));
        this.moneyZero =  new Money(new BigDecimal("00.00"));

    }
    @Test
    void getValue() {
        Assertions.assertEquals(money.getValue(), new BigDecimal("500.00"));
    }

    @Test
    void isGreaterThanZero() {
        Assertions.assertEquals(true, money.isGreaterThanZero());
    }

    @Test
    void isLessThanZero() {
        Assertions.assertEquals(false, moneyZero.isGreaterThanZero());
    }

    @Test
    void isGreaterThan() {
        Money money = new Money(new BigDecimal("500.00"));
        var result = this.money.isGreaterThan(new Money(new BigDecimal("100.00")));
        Assertions.assertEquals(result, true);
    }

    @Test
    void add() {
        var added = this.money.add(new Money(new BigDecimal("10.00")));
        Assertions.assertEquals(added.getValue(), new BigDecimal("510.00"));
    }

    @Test
    void subtract() {
        var sub = this.money.subtract(new Money(new BigDecimal("10.00")));
        Assertions.assertEquals(sub.getValue(), new BigDecimal("490.00"));
    }

    @Test
    void multiply() {
        var sub = this.money.multiply(2);
        Assertions.assertEquals(sub.getValue(), new BigDecimal("1000.00"));
    }

    @Test
    void divide() {
        var div = this.money.divide(2);
        Assertions.assertEquals(div.getValue(), new BigDecimal("250.00"));
    }

    @Test
    void calculateDiscount() {
        Money money = new Money(new BigDecimal("500.00"));
        Double discount = 0.02;
        var result = money.calculateDiscount(money.getValue(), discount);

        assertEquals(result, new BigDecimal("490.00"));
    }
}