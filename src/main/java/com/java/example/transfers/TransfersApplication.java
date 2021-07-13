package com.java.example.transfers;

import com.java.example.transfers.infrastructure.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TransfersApplication implements ApplicationRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TransfersApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountRepository.loadSampleAccounts(mongoTemplate);
    }
}
