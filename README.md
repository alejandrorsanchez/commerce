# Commerce API
## Status code
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=alejandrorsanchez_commerce&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=alejandrorsanchez_commerce)

Develop a SpringBoot application/service provinding an end-point with the following features:
* Input parameters: application date, product identificator, brand identificator.
* Output parameters: product identificator, brand identificator, application dates and final price.

## H2 Database
### Prices table
| brandId | startDate           |       endDate       | priceList | productId | priority | priceValue | currency |
|:-------:|:--------------------|:-------------------:|:---------:|:---------:|:--------:|:----------:|:--------:|
|    1    | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 |     1     |   35455   |    0     |   35.50    |   EUR    |
|    1    | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 |     2     |   35455   |    1     |   25.45    |   EUR    |
|    1    | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 |     3     |   35455   |    1     |   30.50    |   EUR    |
|    1    | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 |     4     |   35455   |    1     |   38.95    |   EUR    |


## Links
1. API URL: **http://localhost:9090/api/v0**
2. API documentation: **http://localhost:9090/api/v0/swagger-ui.html**
3. Java version 17. Get it here: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
4. Request example to get the price regarding an application date:
   **http://localhost:9090/api/v0/prices/search?brandId=1&productId=35455&applicationDate=2020-06-14T21:00:00.000Z**

