package org.example.in.command;

import org.example.application.valueObject.Money;
import org.example.out.model.PersonType;

import java.math.BigDecimal;

public class CreditCheckCommand {
    private PersonType personType;
    private Money amount;

    public CreditCheckCommand(PersonType personTypeDTO, Money amount) {
        this.personType = personTypeDTO;
        this.amount = amount;
    }

    public CreditCheckCommand() {
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
