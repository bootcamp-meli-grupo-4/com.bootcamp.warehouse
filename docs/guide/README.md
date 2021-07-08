# Project: Projeto final Warehouse

Repositório responsável por conter o desenvolvimento de um sistema web proposto por um desafio da digital house e mercado livre.
Para isso foi construído uma API com o Spring Framework, MySQL e o Fury.

## Recursos Utilizados: :computer:
Para o desenvolvimento do projeto foi utilizado as seguintes aplicações/Frameworks :

* [Docker](https://www.docker.com/)
* [MySQL](https://www.mysql.com/)
* [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
* [Spring Framework](https://spring.io/)
* [Swagger](https://swagger.io/)
* [Fury]()

## Executando o Projeto Localmente :fire:

Após ter baixado o projeto deste repositório, é necessário definir uma variável de nome SCOPE e 
valor local, também é necessário inicializar o banco de dados local, que pode ser feito com o comando abaixo:

```
> docker-compose up -d
```
por último é possivel iniciar a aplicação com o comando abaixo:
```
> mvn spring-boot:run
```

Esse comando irá baixar todas as dependências e executar o programa ao fim.

## End-point: Login
### Description: 
Method: POST
>```
>http://localhost:8082/api/v1/sign-in?username=user_three&password=contra123
>```
### Query Params

|Param|value|
|---|---|
|username|user_three|
|password|contra123|


### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Indbound Order - REQ 1
### Description: 
Method: POST
>```
>localhost:8082/api/v1/fresh-products/inboundorder
>```
### Body (**raw**)

```json
{
  "orderDate": "2021-07-07",
  "section": {
    "sectionCode": 1,
    "warehouseCode": 1
  },
  "batchStock": [
    {
      "currentTemperature": 10,
      "minimumTemperature": 0,
      "initialQuantity": 13,
      "manufacturingDate": "2021-07-07",
      "manufacturingTime": "2021-07-07T14:36:09.946Z",
      "dueDate": "2021-07-07",
      "productId": 1
    }
  ]
}
```

### Query Params

|Param|value|
|---|---|
|X-Auth|null|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiJdLCJpYXQiOjE2MjU1OTM4OTMsImV4cCI6MTYyNjU5Mzg5M30.lRSyk8T_gLIVRvtlfv4A2mOXPwbIKd6wT7mBWyBwu9uqHsy28dM1pcgHFr3An3sGueJ8vaIOxozuhHXXd49wIA|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Indbound Order - REQ 1
### Description: 
Method: PUT
>```
>localhost:8082/api/v1/fresh-products/inboundorder/1
>```
### Body (**raw**)

```json
{
  "orderNumber": 1,
  "orderDate": "2021-07-07",
  "section": {
    "sectionCode": 1,
    "warehouseCode": 1
  },
  "batchStock": [
    {
      "batchNumber": 1,
      "currentTemperature": 0,
      "minimumTemperature": 0,
      "initialQuantity": 3,
      "currentQuantity": 4,
      "manufacturingDate": "2021-07-07",
      "manufacturingTime": "2021-07-07T14:43:07.906Z",
      "dueDate": "2021-07-07",
      "productId": 1
    }
  ]
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX0JZX1dBUkVIT1VTRV9QRVJNSVNTSU9OIiwiTU9ESUZZX1NUT0NLX1BFUk1JU1NJT04iLCJGSU5EX0FMTF9QUk9EVUNUX0JZX0NBVEVHT1JZX0FORF9XQVJFSE9VU0VfUEVSTUlTU0lPTiIsIkZJTkRfQUxMX1BST0RVQ1RfU1RPQ0tfRFVFX0RBVEVfQllfU0VDVE9SIl0sImlhdCI6MTYyNTc1ODAxMywiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.wN-07CWjdGIQrrri7u2aNldqV_hAxVppoGW-z7QWduw5xbYVcVjgG__S9Qby7HeylbaC7NUvNzuTKlyW6iS5VA|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Purchase Order - REQ 2
### Description: 
Method: GET
>```
>localhost:8082/api/v1/fresh-products/orders/2
>```
### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiNSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIkJVWV9QUk9EVUNUX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkVESVRfUFVSQ0hBU0VfT1JERVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX1BVUkNIQVNFX09SREVSX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUl9DQVRFR09SWV9QRVJNSVNTSU9OIl0sImlhdCI6MTYyNTc1ODI1NCwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.3lBOxOX4HBzf6DvDav6nEChLRtS9OYw9PaG935oHQJGgfxpEQy_9Y92hbUqdg81rGQ5sYLGADB_0kD-fldc20g|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Puchase Order - REQ 2
### Description: 
Method: POST
>```
>localhost:8082/api/v1/fresh-products/orders
>```
### Body (**raw**)

```json
{
  "date": "2021-07-08",
  "products": [
    {
      "quantity": 5,
      "productId": 1
    }
  ]
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiNSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIkJVWV9QUk9EVUNUX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkVESVRfUFVSQ0hBU0VfT1JERVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX1BVUkNIQVNFX09SREVSX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUl9DQVRFR09SWV9QRVJNSVNTSU9OIl0sImlhdCI6MTYyNTc1ODI1NCwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.3lBOxOX4HBzf6DvDav6nEChLRtS9OYw9PaG935oHQJGgfxpEQy_9Y92hbUqdg81rGQ5sYLGADB_0kD-fldc20g|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Puchase Order - REQ 2
### Description: 
Method: PUT
>```
>localhost:8082/api/v1/fresh-products/orders/2
>```
### Body (**raw**)

```json
{
  "date": "2021-07-08",
  "products": [
    {
      "quantity": 1,
      "productId": 1
    }
  ]
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiNSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIkJVWV9QUk9EVUNUX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkVESVRfUFVSQ0hBU0VfT1JERVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX1BVUkNIQVNFX09SREVSX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUl9DQVRFR09SWV9QRVJNSVNTSU9OIl0sImlhdCI6MTYyNTc1ODI1NCwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.3lBOxOX4HBzf6DvDav6nEChLRtS9OYw9PaG935oHQJGgfxpEQy_9Y92hbUqdg81rGQ5sYLGADB_0kD-fldc20g|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Unexpired Products - REQ 2
### Description: 
Method: GET
>```
>localhost:8082/api/v1/fresh-products/
>```
### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiNSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIkJVWV9QUk9EVUNUX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkVESVRfUFVSQ0hBU0VfT1JERVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX1BVUkNIQVNFX09SREVSX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUl9DQVRFR09SWV9QRVJNSVNTSU9OIl0sImlhdCI6MTYyNTc1OTA0MSwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.J1MMR8xJ5n8bFSKpCMhdUvj3coRgDzyzNPOq7TkbMIUX0d2jBgSHbAkhIDRuVh8tJK3e2HNsxAInXFWhwL1KbA|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Unexpired Products Filters - REQ 2
### Description: 
Method: GET
>```
>localhost:8082/api/v1/fresh-products/list?category=FR
>```
### Query Params

|Param|value|
|---|---|
|category|FR|
|null|null|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiNSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIkJVWV9QUk9EVUNUX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkVESVRfUFVSQ0hBU0VfT1JERVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX1BVUkNIQVNFX09SREVSX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUk1JU1NJT04iLCJMSVNUX0FMTF9QUk9EVUNUX1BFUl9DQVRFR09SWV9QRVJNSVNTSU9OIl0sImlhdCI6MTYyNTc1OTA0MSwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.J1MMR8xJ5n8bFSKpCMhdUvj3coRgDzyzNPOq7TkbMIUX0d2jBgSHbAkhIDRuVh8tJK3e2HNsxAInXFWhwL1KbA|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Product in Sector with filters - REQ 3
### Description: 
Method: GET
>```
>localhost:8082/api/v1/fresh-products/warehouse/list?idProduct=1&order=F
>```
### Query Params

|Param|value|
|---|---|
|idProduct|1|
|order|F|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX0JZX1dBUkVIT1VTRV9QRVJNSVNTSU9OIiwiTU9ESUZZX1NUT0NLX1BFUk1JU1NJT04iLCJGSU5EX0FMTF9QUk9EVUNUX0JZX0NBVEVHT1JZX0FORF9XQVJFSE9VU0VfUEVSTUlTU0lPTiIsIkZJTkRfQUxMX1BST0RVQ1RfU1RPQ0tfRFVFX0RBVEVfQllfU0VDVE9SIl0sImlhdCI6MTYyNTc1ODc0MCwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.kvMu1ZZ3p02plIY4I1X6yFZwXY_wNGie_crrXQha5Va4ZMdcOu51iScKcMsIuHL8BI8xfFS0BD0x5GM4n7qdnQ|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Product in Warehouse - REQ 4
### Description: 
Method: GET
>```
>localhost:8082/api/v1/fresh-products/warehouse/1
>```
### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX0JZX1dBUkVIT1VTRV9QRVJNSVNTSU9OIiwiTU9ESUZZX1NUT0NLX1BFUk1JU1NJT04iLCJGSU5EX0FMTF9QUk9EVUNUX0JZX0NBVEVHT1JZX0FORF9XQVJFSE9VU0VfUEVSTUlTU0lPTiIsIkZJTkRfQUxMX1BST0RVQ1RfU1RPQ0tfRFVFX0RBVEVfQllfU0VDVE9SIl0sImlhdCI6MTYyNTc1ODc0MCwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.kvMu1ZZ3p02plIY4I1X6yFZwXY_wNGie_crrXQha5Va4ZMdcOu51iScKcMsIuHL8BI8xfFS0BD0x5GM4n7qdnQ|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Expired Products - REQ 5
### Description: 
Method: GET
>```
>http://localhost:8082/api/v1/fresh-products/due-date/?quantityDay=10&idSector=1
>```
### Query Params

|Param|value|
|---|---|
|quantityDay|10|
|idSector|1|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX0JZX1dBUkVIT1VTRV9QRVJNSVNTSU9OIiwiTU9ESUZZX1NUT0NLX1BFUk1JU1NJT04iLCJGSU5EX0FMTF9QUk9EVUNUX0JZX0NBVEVHT1JZX0FORF9XQVJFSE9VU0VfUEVSTUlTU0lPTiIsIkZJTkRfQUxMX1BST0RVQ1RfU1RPQ0tfRFVFX0RBVEVfQllfU0VDVE9SIl0sImlhdCI6MTYyNTc1OTc3MywiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.bWDVemiLUGiia_H46wOeJ1lEujMACgICPUWcDLqIXN7ITS5s2QIWwDqRcUi_0JU48teE8TAXY4CU2y40bTmw-g|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Expired Products Filters - REQ 5
### Description: 
Method: GET
>```
>http://localhost:8082/api/v1/fresh-products/due-date/list?quantityDay=10&category=FR&sorted=asc
>```
### Query Params

|Param|value|
|---|---|
|quantityDay|10|
|category|FR|
|sorted|asc|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiIsIlJFR0lTVEVSX1NUT0NLX1BFUk1JU1NJT04iLCJBVVRIRU5USUNBVEVEX1VTRVJfUEVSTUlTU0lPTiIsIkdFVF9QUk9EVUNUX0JZX1dBUkVIT1VTRV9QRVJNSVNTSU9OIiwiTU9ESUZZX1NUT0NLX1BFUk1JU1NJT04iLCJGSU5EX0FMTF9QUk9EVUNUX0JZX0NBVEVHT1JZX0FORF9XQVJFSE9VU0VfUEVSTUlTU0lPTiIsIkZJTkRfQUxMX1BST0RVQ1RfU1RPQ0tfRFVFX0RBVEVfQllfU0VDVE9SIl0sImlhdCI6MTYyNTc1OTc3MywiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1fQ.bWDVemiLUGiia_H46wOeJ1lEujMACgICPUWcDLqIXN7ITS5s2QIWwDqRcUi_0JU48teE8TAXY4CU2y40bTmw-g|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

_________________________________________________
