package com.finalProject.CurrencyConversionProject.web.controller;

<<<<<<< HEAD
import com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl.CurrencyServiceImpl;
=======
import com.finalProject.CurrencyConversionProject.currencyService.serviceImpl.CurrencyServiceImpl;
>>>>>>> mohamed
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


    @GetMapping("/pair-conversion")
    public ResponseEntity<ResponseModel<?>> convertAmount(@RequestParam(value = "base",required = false, defaultValue = "USD") String base,
                                                          @RequestParam(value = "target",required = false, defaultValue = "USD") String target,
                                                          @RequestParam(value = "amount",required = false, defaultValue = "0.0") Double amount)  {

        AmountConversionDto response =  this.currencyService.convertAmount(base, target, amount);
        ResponseModel<AmountConversionDto> model = ResponseModel.<AmountConversionDto>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);
    }
<<<<<<< HEAD
    @PostMapping("/{base}")
    public ResponseEntity<ResponseModel<?>> getConversionRates(@RequestBody List<String>currencies,
                                                                @PathVariable("base") String base){
        FavoriteCurrenciesDto response =  this.currencyService.compareCurrencies(currencies,base);
        ResponseModel<Object> model = ResponseModel.<Object>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();
=======
    @PostMapping("/favorite-currencies")
    public ResponseEntity<ResponseModel<?>> getConversionRates(@RequestBody List<String>currencies,
                                                               @RequestParam(value = "base",required = false, defaultValue = "USD") String base){
        FavoriteCurrenciesDto response =  this.currencyService.compareCurrencies(currencies,base);
        ResponseModel<FavoriteCurrenciesDto> model = ResponseModel.<FavoriteCurrenciesDto>builder()
                .data(response)
                .statusCode(HttpStatus.OK.value())
                .status("success").build();
>>>>>>> mohamed

        return new ResponseEntity(model, HttpStatus.OK);

    }
    @GetMapping("/comparison")
    public ResponseEntity<ResponseModel<?>> compareTwoCurrencies(@RequestParam(value = "base",required = false, defaultValue = "USD") String base,
                                                                 @RequestParam(value = "amount",required = false, defaultValue = "0.0") Double amount,
                                                                 @RequestParam(value = "target1",required = false, defaultValue = "USD") String target1,
                                                                 @RequestParam(value = "target2",required = false, defaultValue = "USD") String target2)  {

<<<<<<< HEAD

    @PostMapping("/{base}/{amount}")
    public ResponseEntity<ResponseModel<?>> compareTwoCurrencies(@PathVariable("base") String base,
                                                          @PathVariable("amount") Double amount,
                                                          @RequestBody List<String> targetCurrecies)  {

        TwoCurrenciesComparisonDto response =  this.currencyService.compareTwoCurrencies(base, amount, targetCurrecies);
        ResponseModel<Object> model = ResponseModel.<Object>builder()
=======
        TwoCurrenciesComparisonDto response =  this.currencyService.compareTwoCurrencies(base, amount, target1,target2);
        ResponseModel<TwoCurrenciesComparisonDto> model = ResponseModel.<TwoCurrenciesComparisonDto>builder()
>>>>>>> mohamed
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
