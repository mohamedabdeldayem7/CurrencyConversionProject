package com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.CurrencyService.CurrencyServiceInterface;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.dto.TwoCurrenciesComparisonDto;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;
import com.finalProject.CurrencyConversionProject.repository.CurrencyRepository;
import com.finalProject.CurrencyConversionProject.validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyServiceInterface {
    @Autowired
    InputValidation inputValidation;
    @Autowired
    private CurrencyRepository currencyRepository;


    @Override
    public AmountConversionDto convertAmount(String base, String target, Double amount){
        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkCurrency(target);
        this.inputValidation.checkAmount(amount);

        AmountConversionDto responseObject = (AmountConversionDto) this.currencyRepository.convertAmount(base, target, amount);;
        return responseObject;
    }

    @Override
    public FavoriteCurrenciesDto compareCurrencies(List<String> currencies, String base) {

        this.inputValidation.checkList(currencies, currencies.size());
        this.inputValidation.checkCurrency(base);

        FavoriteCurrenciesDto responseObject = (FavoriteCurrenciesDto) this.currencyRepository.compareCurrencies(base);

        Map<String ,Double> finalMap = new HashMap<>();
        Map<String, Double> responseMap = responseObject.getConversion_rates();

        currencies.stream().forEach(currency -> finalMap.put(currency, responseMap.get(currency)));

        FavoriteCurrenciesDto finalResponseObject = FavoriteCurrenciesDto.builder()
                .conversion_rates(finalMap)
                .build();

        return finalResponseObject;
    }
    @Override
    public List<Map<String, String>> getCurrencies() {
        List<Map<String, String>> currencies = Currencies.getCurrencies();
        return currencies;
    }

    @Override
    public TwoCurrenciesComparisonDto compareTwoCurrencies(String base, Double amount, List<String> targetCurrencies) {
        this.inputValidation.checkList(targetCurrencies, 2);
        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkAmount(amount);

        List<AmountConversionDto> reponseList = new ArrayList<>();

        targetCurrencies.stream().forEach(target -> {
            AmountConversionDto response = (AmountConversionDto) this.currencyRepository.convertAmount(base, target, amount);
            reponseList.add(response);
        });

        TwoCurrenciesComparisonDto finalResponse = TwoCurrenciesComparisonDto.builder()
                .firstTargetCurrency(reponseList.get(0))
                .secondTargetCurrency(reponseList.get(1))
                .build();
        return finalResponse;
    }


}
