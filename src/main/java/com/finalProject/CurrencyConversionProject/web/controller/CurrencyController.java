package com.finalProject.CurrencyConversionProject.web.controller;

import com.finalProject.CurrencyConversionProject.services.currencyService.serviceImpl.CurrencyServiceImpl;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CurrencyController {
    @Autowired
    private CurrencyServiceImpl currencyService;
    @GetMapping("/pair-conversion")
    public ResponseEntity<ResponseModel<?>> convertAmount(@RequestParam(value = "base",required = false, defaultValue = "USD") String base,
                                                          @RequestParam(value = "target",required = false, defaultValue = "USD") String target,
                                                          @RequestParam(value = "amount",required = false, defaultValue = "0.0") Double amount)  {
        AmountConversionDto response =  this.currencyService.convertAmount(base, target, amount);
        ResponseModel<AmountConversionDto> model = ResponseModel.<AmountConversionDto>builder()
                .data(response)
                .status("success")
                .statusCode(HttpStatus.OK.value()).build();
        return new ResponseEntity(model, HttpStatus.OK);
    }

    @PostMapping("/favorite-currencies")
    public ResponseEntity<ResponseModel<?>> getConversionRates(@RequestBody List<String>currencies,
                                                               @RequestParam(value = "base",required = false, defaultValue = "USD") String base){
//        FavoriteCurrenciesDto response =  this.currencyService.compareCurrencies(currencies,base);
        List<Double> response = this.currencyService.compareCurrencies(currencies,base);

        ResponseModel<List<Double>> model = ResponseModel.<List<Double>>builder()
                .data(response)
                .statusCode(HttpStatus.OK.value())
                .status("success")
                .build();

        return new ResponseEntity(model, HttpStatus.OK);
    }

    @GetMapping("/comparison")
    public ResponseEntity<ResponseModel<?>> compareTwoCurrencies(@RequestParam(value = "base",required = false, defaultValue = "USD") String base,
                                                                 @RequestParam(value = "amount",required = false, defaultValue = "0.0") Double amount,
                                                                 @RequestParam(value = "target1",required = false, defaultValue = "USD") String target1,
                                                                 @RequestParam(value = "target2",required = false, defaultValue = "USD") String target2)  {
        TwoCurrenciesComparisonDto response =  this.currencyService.compareTwoCurrencies(base, amount, target1,target2);
        ResponseModel<TwoCurrenciesComparisonDto> model = ResponseModel.<TwoCurrenciesComparisonDto>builder()
                .data(response)
                .statusCode(HttpStatus.OK.value())
                .status("success")
                .build();
        return new ResponseEntity(model, HttpStatus.OK);
    }

    @GetMapping("/currencies")
    public ResponseEntity<ResponseModel<?>> getCurrencies(){
        List<Map<String, String>> currencies =  this.currencyService.getCurrencies();
        ResponseModel<List<Map<String, String>>> model = ResponseModel.<List<Map<String, String>>>builder()
                .data(currencies)
                .statusCode(HttpStatus.OK.value())
                .status("success")
                .build();
        return new ResponseEntity(model, HttpStatus.OK);
    }
}
