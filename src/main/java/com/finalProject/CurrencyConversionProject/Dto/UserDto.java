package com.finalProject.CurrencyConversionProject.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String email;
}
