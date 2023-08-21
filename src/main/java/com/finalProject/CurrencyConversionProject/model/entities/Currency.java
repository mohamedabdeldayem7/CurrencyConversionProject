package com.finalProject.CurrencyConversionProject.model.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Currency {
    private String name;
    private Double value;
}
