package com.ivan.ruzhalovich.waletrest.exceptions;


import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<String> noMoneyHandler(NoMoneyException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }

    @ExceptionHandler
    public ResponseEntity<String> noWalletWithCurrentUUIDHandler(IncorrectUUIDException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public  ResponseEntity<String> incorrectOperationTypeHandler(IncorrectOperationType e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> concurrencyFailureExceptionHandler(ConcurrencyFailureException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public  ResponseEntity<String> exceptionHandler(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
