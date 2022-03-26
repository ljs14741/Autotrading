package com.bitcoin.autotrading.trading.client.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestController {

    @ExceptionHandler
    public String errorHandeler(Exception e){
        return "ControllerAdvice"+ e.getMessage();
    }
}
