#MyRetail REST API

MyRetail RESTful service provides the product API which will aggregate product data from multiple sources and returns it as JSON to the caller.

    1. Get request to retrieve product information by Product Id

    2. Put request to save the product price information in the database

##Get Request :
-----------------------

###Input: 
GET request at the path "/products/{id}" for a product 

###Logic: 
When the API receives the request, it sends a request to "redsky.target.com" , retrieves the 
product information and parses product name from response. The price information retrieved from database  ic combined with product information and returned to the user.

###Output: 
For a product with product id '13860428', the sample JSON output is as shown below

{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

###Errors/Validations: 
Throws Product not found exception

##Put request:
-------------------------------------

###Input: 
Put request can save the product info in the database. The request is done at the same path "/products/{id}"

###Logic: 
When the API receives PUT request, it validates that the id in the URL and the JSON body are matching and if so, the price for the product is updated 
in the data store.

###Output: 
Returns json product response if success

###Errors/Validations: 
Throws Bad request exception

##Technologies Used
-----------------

1. Spring Boot - https://projects.spring.io/spring-boot/
2. MongoDB - https://www.mongodb.com/
3. Maven - https://gradle.org

##Instructions to Setup
---------------------
1. Download & Install MongoDB
2. Download and install maven
3. Start Mongodb
4. Clone the code from git repository - https://github.com/nijithab/RetailAPI.git
5. Run the following command in RetailAPI directory
'mvn clean install'
6. Run RetailproductApplication
