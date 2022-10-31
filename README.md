# Commerce API
## Estado del código


Construir una aplicación/servicio en SpringBoot que provea una end point rest de
consulta tal que:
* Parámetros de entrada: fecha de aplicación, identificador de
producto, identificador de cadena.
* Datos de salida: identificador de producto, identificador de
cadena, fechas de aplicación y precio final a aplicar.

## H2 Database
### Tabla Prices
| brandId | startDate           |       endDate       | priceList | productId | priority | priceValue | currency |
|:-------:|:--------------------|:-------------------:|:---------:|:---------:|:--------:|:----------:|:--------:|
|    1    | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 |     1     |   35455   |    0     |   35.50    |   EUR    |
|    1    | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 |     2     |   35455   |    1     |   25.45    |   EUR    |
|    1    | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 |     3     |   35455   |    1     |   30.50    |   EUR    |
|    1    | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 |     4     |   35455   |    1     |   38.95    |   EUR    |


## Enlaces
1. API URL: **http://localhost:9090/api/v0**
2. Documentación de la API: **http://localhost:9090/api/v0/swagger-ui.html**
3. Ejemplo de petición para la obtención del precio asociado a un producto en una fecha determinada: 
**http://localhost:9090/api/v0/prices/search?brandId=1&productId=35455&applicationDate=2020-06-14T21:00:00.000Z**

