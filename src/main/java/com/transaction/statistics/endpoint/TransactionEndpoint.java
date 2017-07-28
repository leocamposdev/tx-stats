package com.transaction.statistics.endpoint;

import com.transaction.statistics.exception.TooOldTransactionException;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
public class TransactionEndpoint {

    private TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postTransaction(@RequestBody Transaction transaction) {
        try {
            transactionService.addTransaction(transaction);
        } catch (TooOldTransactionException e) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(null);


    }
}
