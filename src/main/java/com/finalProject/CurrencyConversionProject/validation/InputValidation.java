package com.finalProject.CurrencyConversionProject.validation;

import com.finalProject.CurrencyConversionProject.exception.InvalidInputException;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;

import org.springframework.stereotype.Component;

@Component
public class InputValidation {
    public void checkCurrency(String currncy){
        Boolean flag = false;
        for(Currencies code:Currencies.values()){
            if(code.toString().equalsIgnoreCase(currncy)){
                flag = true;
                break;
            }
        }
        if(!flag){
            throw new InvalidInputException("Invalid Currency");
        }
    }
    public void checkAmount(Double amount){
        if(amount < 0){
            throw new InvalidInputException("Invalid amount");
        }
    }
}
