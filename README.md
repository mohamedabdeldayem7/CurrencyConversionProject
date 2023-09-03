# CurrencyConversionProject
This is a sample Java / Maven / Spring Boot (version 2.7.14) application that aims to  provide users with real-time and accurate currency
conversion rates using Google's open APIs.
## About the Service

The service is just a simple currencies conversion REST service.You can use this sample service to understand the conversion and comparison between currencies.

Her is the services that application contains:

1- Enable user to convert between amount of money from one base currency to another target currency he needs.

2- Enable User to compare between amount of money of one base Currency to another pair Currencies at the same time.

3- Enable user to get the conversion rates of his favorite currencies.

4- Enable user to get All suppoted currencies.

## Adding the dependency
 
If you are using Maven, add the following dependency.  


```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.ben-manes.caffeine</groupId>
            <artifactId>caffeine</artifactId>
            <version>3.0.0</version>
        </dependency>

    </dependencies>
```
## Steps to run
1. Build the project using
  `mvn clean install`
2. Make sure you are using JDK 18 and Maven 2.x
3. Run using `mvn spring-boot:run`
4. The web application is accessible via localhost:8080## Steps to run
Once the application runs you should see something like this

```
2023-08-29 04:54:49.833  INFO 14096 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-08-29 04:54:50.325  INFO 14096 --- [           main] f.C.CurrencyConversionProjectApplication : Started CurrencyConversionProjectApplication in 6.646 seconds (JVM running for 7.43)

```

## Deploying the application on Railway.
If you want to access the app you can use the following base url
### base_url
```
tiresome-part-production.up.railway.app
```
## Swagger.
I have been using Swagger to document API in Spring project and it worked pretty well.
### How to run
```
https://tiresome-part-production.up.railway.app/swagger-ui/#
```
![User Document](src/images/swagger.PNG?raw=true "Swagger")




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

POST : localhost:8080/favorite-currencies
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
    "status": "success",
    "data": [
        30.9,
        0.9248,
        3.75
    ]
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
            "flagUrl": "*********"
        },
        {
            "code": "USD",
            "name": "United States",
            "flagUrl": "*********"
        }
    ]
}
