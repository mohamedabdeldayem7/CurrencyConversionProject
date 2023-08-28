package com.finalProject.CurrencyConversionProject.validation;

import com.finalProject.CurrencyConversionProject.exception.InvalidInputException;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;

import com.finalProject.CurrencyConversionProject.model.constants.ErrorMessages;
import org.springframework.stereotype.Component;
import java.util.List;

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
            throw new InvalidInputException(ErrorMessages.UNSUPPORTED_CURRENCY);
        }
    }
    public void checkAmount(Double amount){
        if(amount < 0){
            throw new InvalidInputException(ErrorMessages.INVALID_AMOUNT);
        }
    }
    public void checkList(List<String> currencies, Integer size){
        if(currencies.size() != size){
            throw new InvalidInputException("Invalid size");
        }
        currencies.stream().forEach(currrecy -> this.checkCurrency(currrecy));
    }

}
