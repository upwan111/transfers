package com.java.example.transfers.application;

import com.java.example.transfers.command.CompleteTransferCommand;
import com.java.example.transfers.domain.Transaction;

public interface TransferService {
    /**
     * Handle the command to return a successful transaction
     * @param cmd
     * @return
     */
    Transaction handle(CompleteTransferCommand cmd);
}
