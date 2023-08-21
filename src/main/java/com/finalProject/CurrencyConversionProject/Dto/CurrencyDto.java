package com.finalProject.CurrencyConversionProject.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {
    private String name;
    private Double value;
}
