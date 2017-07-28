package com.transaction.statistics.endpoint;

import com.transaction.statistics.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionEndpoint {

    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postTransaction(@RequestBody Transaction transaction) {

        /*

        201 - in case of success
         204 - if transaction is older than 60 seconds

         */
        return ResponseEntity.status(HttpStatus.OK).body(null);


    }
}
