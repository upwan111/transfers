package com.java.example.transfers.infrastructure;

import com.java.example.transfers.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,String> {
}
