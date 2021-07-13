package com.java.example.transfers.command;

import com.java.example.transfers.domain.Account;
import com.java.example.transfers.exception.InvalidAmountException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CompleteTransferCommand {
    @NotEmpty(message = "sourceAccountNumber cannot be null or empty")
    private String sourceAccountNumber;
    @NotEmpty(message = "destinationAccountNumber cannot be null or empty")
    private String destinationAccountNumber;
    @NotEmpty(message = "amount cannot be null or empty")
    private String amount;

    private Account source;
    private Account destination;
    private BigDecimal amountValue;

    public void updateAccountBalanceDetails(Account source, Account destination) {
        this.source = source;
        this.destination = destination;
        this.transformAmountValue();
    }

    private void transformAmountValue() {
        try {
            amountValue = new BigDecimal(amount);
        }
        catch (NumberFormatException e) {
            throw new InvalidAmountException("amount is not valid",e);
        }
    }

}
