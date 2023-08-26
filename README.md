# CurrencyConversionProject
This is a back-end currency conversion project 

## Architecture of Currency Conversion API

## <mark style="background: #FFB86CA6;">1- pair-conversion Api</mark>

### Api Contract :
```
GET : {base_url}/pair-conversion
	?base={base_currency}
	&target={target_currency}
	&amount={amount}
```

### Request Example :
```
GET : localhost:8080/pair-conversion
	?base=USD
	&target=EGP
	&amount=222
```

### Response Example :
```
{
    "statusCode": 200,
    "data": {
        "conversion_result": 6860.133
    }
}
```

****

## <mark style="background: #FFB86CA6;">2- comparison API</mark>

### Api Contract :
```
GET : {base_url}/comparison
	?base={base_currency}
	&target1={target_currency_1}
	&target={target_currency_2}
	&amount={amount}
```

### Request Example :
```
GET : localhost:8080/comparison
	?base=USD
	&target1=EUR
	&target2=EGP
	&amount=222
```

### Response Example :
```
{
    "statusCode": 200,
    "data": {
        "firstTargetCurrency": {
            "conversion_result": 205.6164
        },
        "secondTargetCurrency": {
            "conversion_result": 6860.133
        }
    }
}
```

****

## <mark style="background: #FFB86CA6;">3- Favorite currencies Api</mark>

### Api Contract :

***POST : to send request body for all currencies .***

```

POST : {base_url}/favorite-currencies
	?base={base_currency}

Body : [
         "EGP",
	 "EUR",
         "SAR"
       ]
```

### Request Example :
```
// For example : we have USD and 3 currencies as favorites : EGP, EUR, SAR

POST : localhost:8080/comparison
	?base=USD

Body : [
	"EGP",
	"EUR",
        "SAR"
       ]	
```

### Response Example :
```
{
    "statusCode": 200,
    "data": {
        "conversion_rates": {
            "EUR": 0.9262,
            "SAR": 3.75,
            "EGP": 30.9015
        }
    }
}
```


****

## <mark style="background: #FFB86CA6;">4- Get All Currencies Api</mark>

### Api Contract :
```
GET : {base_url}/currencies
```

### Request Example :
```
GET : localhost:8080/currencies
```

### Response Example :
```json
{
    "statusCode": 200,
    "data": [
        {
            "code": "EUR",
            "name": "Europe Union",
            "url": "*********"
        },
        {
            "code": "USD",
            "name": "United States",
            "url": "*********"
        }
    ]
}
