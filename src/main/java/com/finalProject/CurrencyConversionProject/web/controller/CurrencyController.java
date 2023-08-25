package com.finalProject.CurrencyConversionProject.web.controller;

import com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.dto.TwoCurrenciesComparisonDto;
import com.finalProject.CurrencyConversionProject.web.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {
    @Autowired
    private CurrencyServiceImpl currencyService;


    @GetMapping("/{base}/{target}/{amount}")
    public ResponseEntity<ResponseModel<?>> convertAmount(@PathVariable("base") String base,
                                                        @PathVariable("target") String target,
                                                        @PathVariable("amount") Double amount)  {

        AmountConversionDto response =  this.currencyService.convertAmount(base, target, amount);
        ResponseModel<AmountConversionDto> model = ResponseModel.<AmountConversionDto>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);
    }
    @PostMapping("/{base}")
    public ResponseEntity<ResponseModel<?>> getConversionRates(@RequestBody List<String>currencies,
                                                                @PathVariable("base") String base){
        FavoriteCurrenciesDto response =  this.currencyService.compareCurrencies(currencies,base);
        ResponseModel<FavoriteCurrenciesDto> model = ResponseModel.<FavoriteCurrenciesDto>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);

    }


    @PostMapping("/{base}/{amount}")
    public ResponseEntity<ResponseModel<?>> compareTwoCurrencies(@PathVariable("base") String base,
                                                          @PathVariable("amount") Double amount,
                                                          @RequestBody List<String> targetCurrecies)  {

        TwoCurrenciesComparisonDto response =  this.currencyService.compareTwoCurrencies(base, amount, targetCurrecies);
        ResponseModel<TwoCurrenciesComparisonDto> model = ResponseModel.<TwoCurrenciesComparisonDto>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();
        return new ResponseEntity(model, HttpStatus.OK);
    }
    @GetMapping("/currencies")
    public ResponseEntity<ResponseModel<?>> getCurrencies(){
        List<Map<String, String>> currencies =  this.currencyService.getCurrencies();
        ResponseModel<List<Map<String, String>>> model = ResponseModel.<List<Map<String, String>>>builder()
                .data(currencies).statusCode(HttpStatus.OK.value()).build();
        return new ResponseEntity(model, HttpStatus.OK);
    }
}
