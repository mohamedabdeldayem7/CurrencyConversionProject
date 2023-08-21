package com.finalProject.CurrencyConversionProject.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
    private  Integer statusCode;
    private String status;
    private  String message;
    private T data;
}
