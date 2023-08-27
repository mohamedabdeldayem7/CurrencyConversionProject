package com.finalProject.CurrencyConversionProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountConversionDto {
//    private String base_code;
//    private String target_code;
    private Double conversion_result;
}
