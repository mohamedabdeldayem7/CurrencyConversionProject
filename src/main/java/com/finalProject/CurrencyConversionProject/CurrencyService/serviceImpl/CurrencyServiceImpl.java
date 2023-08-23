package com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.CurrencyService.CurrencyServiceInterface;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;
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
    @Value("${api1.accessKey}")
    private String accessKeyToConvertAmount;
    @Value("${api2.accessKey}")
    private String accessKeyToCompareCurrencies;
    private final WebClient webClient;

    public CurrencyServiceImpl() {
        this.webClient = WebClient.create("http://localhost:8080");
    }
    @Override
    public Object convertAmount(String base, String target, Double amount){
        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkCurrency(target);
        this.inputValidation.checkAmount(amount);
        String url = "https://v6.exchangerate-api.com/v6/" + accessKeyToConvertAmount
                +"/pair/"+ base + "/" + target + "/" + amount.toString();
        String  response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();

        Object responseObject = this.convertToJsonObject(response);
        return responseObject;
    }

    @Override
    public Object compareCurrencies(List<String> currencies, String base) {
        for (String currency:currencies){
            this.inputValidation.checkCurrency(currency);
        }
        this.inputValidation.checkCurrency(base);
        String baseurl = "http://apilayer.net/api/live?access_key=" +accessKeyToCompareCurrencies+
                "&currencies=";
        String url= Urlbuilder.buildURLWithList(baseurl,currencies)+"&source="+base+"&format=1";
        String  response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();
        Object responseObject = this.convertToJsonObject(response);
        return responseObject;

    }

    @Override
    public List<Map<String, String>> getCurrencies() {
        return Currencies.getCurrencies();
    }

    private Object convertToJsonObject(String response){
        String json = response;

        Gson gson = new Gson();
        Object convertedObject = gson.fromJson(json, Object.class);

        return convertedObject;
    }

}
