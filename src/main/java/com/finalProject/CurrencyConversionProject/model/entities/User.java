package com.finalProject.CurrencyConversionProject.model.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String userName;
    private String email;
}
