package com.finalProject.CurrencyConversionProject.currencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.currencyService.CurrencyServiceInterface;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.dto.TwoCurrenciesComparisonDto;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;
import com.finalProject.CurrencyConversionProject.apiService.CurrenncyApiServiceInterface;
import com.finalProject.CurrencyConversionProject.validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyServiceInterface {
    @Autowired
    private InputValidation inputValidation;
    @Autowired
    private CurrenncyApiServiceInterface currenncyApiService;


    @Override
    public AmountConversionDto convertAmount(String base, String target, Double amount){
        this.convertToUpperCase(base, target);

        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkCurrency(target);
        this.inputValidation.checkAmount(amount);

        AmountConversionDto responseObject = (AmountConversionDto) this.currenncyApiService.convertAmount(base, target, amount);;
        return responseObject;
    }

    @Override
    public FavoriteCurrenciesDto compareCurrencies(List<String> favoriteCurrencies, String base) {
        List<String> currencies = this.convertToUpperCase(favoriteCurrencies, base);

        this.inputValidation.checkList(currencies, currencies.size());
        this.inputValidation.checkCurrency(base);

        FavoriteCurrenciesDto responseObject = this.currenncyApiService.compareCurrencies(base);

        Map<String ,Double> finalResponseMap = new HashMap<>();
        Map<String, Double> responseMap = responseObject.getConversion_rates();

        currencies.stream().forEach(currency -> finalResponseMap.put(currency, responseMap.get(currency)));

        FavoriteCurrenciesDto finalResponseObject = FavoriteCurrenciesDto.builder()
                .conversion_rates(finalResponseMap)
                .build();

        return finalResponseObject;
    }
    @Override
    public List<Map<String, String>> getCurrencies() {
        List<Map<String, String>> currencies = Currencies.getCurrencies();
        return currencies;
    }

    @Override
    public TwoCurrenciesComparisonDto compareTwoCurrencies(String base, Double amount,String target1,String target2) {
        this.convertToUpperCase(base, target1, target2);

        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkAmount(amount);
        this.inputValidation.checkCurrency(target1);
        this.inputValidation.checkCurrency(target2);

        AmountConversionDto response1 = (AmountConversionDto) this.currenncyApiService.convertAmount(base, target1, amount);
        AmountConversionDto response2 = (AmountConversionDto) this.currenncyApiService.convertAmount(base, target2, amount);

        TwoCurrenciesComparisonDto finalResponse = TwoCurrenciesComparisonDto.builder()
                .firstTargetCurrency(response1)
                .secondTargetCurrency(response2)
                .build();
        return finalResponse;
    }

    private void convertToUpperCase(String... currency){
        Arrays.stream(currency).collect(Collectors.toList()).stream()
                .forEach(cur -> cur.toUpperCase());
    }
    private List<String> convertToUpperCase(List<String> list, String... currency){
        this.convertToUpperCase(currency);
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
}
