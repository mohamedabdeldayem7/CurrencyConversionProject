package com.finalProject.currencyConversion.exception;

import com.finalProject.currencyConversion.model.constants.Messages;
import com.finalProject.currencyConversion.web.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleRequestException(InvalidInputException e){

        ResponseModel model = ResponseModel.builder()
                .status(Messages.ERROR_STATUS)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRequestException(RuntimeException e){

        ResponseModel model = ResponseModel.builder()
                .status(Messages.ERROR_STATUS)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }
}
