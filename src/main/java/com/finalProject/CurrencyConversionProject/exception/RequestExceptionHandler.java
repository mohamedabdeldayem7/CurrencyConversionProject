package com.finalProject.CurrencyConversionProject.exception;

import com.finalProject.CurrencyConversionProject.web.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleRequestException(InvalidInputException e){

        ResponseModel model = ResponseModel.builder().message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRequestException(RuntimeException e){

        ResponseModel model = ResponseModel.builder().message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }
}
