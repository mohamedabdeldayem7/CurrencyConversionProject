package com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.CurrencyService.CurrencyServiceInterface;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;
import com.finalProject.CurrencyConversionProject.repository.CurrencyRepository;
import com.finalProject.CurrencyConversionProject.urlBuilder.Urlbuilder;
import com.finalProject.CurrencyConversionProject.validation.InputValidation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyServiceInterface {
    @Autowired
    InputValidation inputValidation;
    @Autowired
    private CurrencyRepository currencyRepository;


    @Override
    public Object convertAmount(String base, String target, Double amount){
        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkCurrency(target);
        this.inputValidation.checkAmount(amount);

        String response = (String) this.currencyRepository.convertAmount(base, target, amount);

        Object responseObject = this.convertToJsonObject(response);
        return responseObject;
    }

//    @Override
//    public Object compareCurrencies(List<String> currencies, String base) {
//        for (String currency:currencies){
//            this.inputValidation.checkCurrency(currency);
//        }
//        this.inputValidation.checkCurrency(base);
//        String baseurl = "http://apilayer.net/api/live?access_key=" +accessKeyToCompareCurrencies+
//                "&currencies=";
//        String url= Urlbuilder.buildURLWithList(baseurl,currencies)+"&source="+base+"&format=1";
//        String  response = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class).block();
//
//        Object responseObject = this.convertToJsonObject(response);
//        return responseObject;
//
//    }

    @Override
    public Object compareCurrencies(List<String> currencies, String base) {
        for (String currency : currencies) {
            this.inputValidation.checkCurrency(currency);
        }
        this.inputValidation.checkCurrency(base);

        String response = this.currencyRepository.compareCurrencies(base);

        Object responseObject = this.convertToJsonObject(response);
        return responseObject;
    }
    @Override
    public List<Map<String, String>> getCurrencies() {
        return Currencies.getCurrencies();
    }

    @Override
    public Object compareTwoCurrencies(String base,Double amount, String target1, String target2) {
        return null;
    }

    private Object convertToJsonObject(String response){
        String json = response;

        Gson gson = new Gson();
        Object convertedObject = gson.fromJson(json, Object.class);

        return convertedObject;
    }

}
