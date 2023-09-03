package com.finalProject.currencyConversion.validation;

import com.finalProject.currencyConversion.exception.InvalidInputException;
import com.finalProject.currencyConversion.model.constants.Currencies;

import com.finalProject.currencyConversion.model.constants.Messages;
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
            throw new InvalidInputException(Messages.UNSUPPORTED_CURRENCY);
        }
    }
    public void checkAmount(Double amount){
        if(amount < 0){
            throw new InvalidInputException(Messages.INVALID_AMOUNT);
        }
    }
    public void checkList(List<String> currencies, Integer size){
        if(currencies.size() != size){
            throw new InvalidInputException(Messages.INVALID_SIZE);
        }
        currencies.stream().forEach(cur -> this.checkCurrency(cur));
    }
}
