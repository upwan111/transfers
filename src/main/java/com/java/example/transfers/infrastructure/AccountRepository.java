package com.java.example.transfers.infrastructure;

import com.java.example.transfers.domain.Account;
import com.java.example.transfers.domain.AccountNumber;
import com.java.example.transfers.domain.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends CrudRepository<Account,String> {

    Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    default void loadSampleAccounts(MongoTemplate mongoTemplate) {

        Account account = new Account(null,new AccountNumber("11111"), new Balance(BigDecimal.valueOf(10000)));
        mongoTemplate.save(account);
        Account account1 = new Account(null,new AccountNumber("22222"), new Balance(BigDecimal.valueOf(10000)));
        mongoTemplate.save(account1);
        logger.info("Sample accounts saved");
    }

    Account findByAccountNumber_value(String accountNumber);
}
