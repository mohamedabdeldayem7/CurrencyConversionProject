package com.finalProject.currencyConversion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PairCurrenciesConversionDto {
    String base_code;
    String target_code;
    Double conversion_rate;
}
