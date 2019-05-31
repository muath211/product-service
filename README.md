# Product Service

Prodcut is a Java Application provides crud operations for a Single Item.

## Built With

Spring Boot, Spring Data, Spring Web, Tomcat, MongoDb

### Prerequisites

1. JDK 1.8+
2. Maven 3.3+
3. Docker
4. Mongodb

## Installation

In order to run this project on your local machine you have to
set the below environment variables with the appropriate values, 
```
    db_host
    db_port
    db_name
    db-username
    db-password
```
then run command 
```
mvn clean install
```

## Usage
Navigate to traget directory then

```
java -jar product-api.jar
```

## Swagger
```
http://localhost:8080/product-api/swagger-ui.html
```

## Apis
Create Product
```
curl -X POST \
  http://localhost:8080/product-api/products/ \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
    "name": "item",
    "vendorId": 124,
    "title": "item one",
    "description": "description",
    "price": 34.323,
    "imageUrl": "url",
    "views": 343,
    "dietaryFlags": {
        "flag1": 1,
        "flag2":0
    }
}'
```

Find Product By id
```
curl -X GET \
  http://localhost:8080/product-api/products/5cf1119a1443d224319d155a \
  -H 'Postman-Token: 994d6b82-3492-4efc-9516-e5283816d46b' \
  -H 'cache-control: no-cache'

```
Update Product
```
curl -X PUT \
  http://localhost:8080/product-api/products/{id} \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "test",
    "vendorId": 124,
    "title": "test",
    "description": "description",
    "price": 34.323,
    "image": "url",
    "views": 3434,
    "dietaryFlags": {
        "test": 1,
        "testw":0
    }
}'
```

Delete Product
```
curl -X DELETE \
  http://localhost:8080/product-api/products/{id} \
  -H 'cache-control: no-cache'
```

Search By Title
```
curl -X POST \
  http://localhost:8080/product-api/products/search \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"title":"itemtitle"
}'
```

Search By Description
```
curl -X POST \
  http://localhost:8080/product-api/products/search \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"description":"itemdescription"
}'
```

