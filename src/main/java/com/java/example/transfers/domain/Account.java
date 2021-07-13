package com.java.example.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("accounts")
public class Account {

    private String id;
    private AccountNumber accountNumber;
    private Balance balance;
}
