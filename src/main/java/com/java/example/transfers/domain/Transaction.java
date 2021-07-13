package com.java.example.transfers.domain;

import com.java.example.transfers.command.CompleteTransferCommand;
import com.java.example.transfers.events.TransferCompleted;
import com.java.example.transfers.exception.InvalidAmountException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Document("transactions")
public class Transaction extends AggregateRoot {

    private String id;
    private Account source;
    private Account destination;
    private Amount amount;

    public Transaction(CompleteTransferCommand cmd) {

        validate(cmd);
        this.id = UUID.randomUUID().toString();
        this.source = new Account(cmd.getSource().getId(),new AccountNumber(cmd.getSource().getAccountNumber().getValue())
                ,new Balance(cmd.getSource().getBalance().getValue().subtract(cmd.getAmountValue())));
        this.destination = new Account(cmd.getDestination().getId(),
                new AccountNumber(cmd.getDestination().getAccountNumber().getValue()),
                new Balance(cmd.getDestination().getBalance().getValue().add(cmd.getAmountValue())));
        this.amount = new Amount(cmd.getAmountValue());

        registerEvent(new TransferCompleted(this.id,this.source, this.destination,this.amount));
    }

    private void validate(CompleteTransferCommand cmd) {
        BigDecimal sourceBalance = cmd.getSource().getBalance().getValue();
        if(sourceBalance.compareTo(cmd.getAmountValue()) < 0
                || sourceBalance.subtract(cmd.getAmountValue()).compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("Insufficient amount in source account");
        }
    }
}
