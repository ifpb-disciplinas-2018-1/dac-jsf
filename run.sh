mvn clean package
docker build -t ricardojob/banco ./database
docker build -t ricardojob/app .
docker run -p 5433:5432 --name banco -d ricardojob/banco 
docker run -p 8082:8080 --name app --link banco:host-banco -d ricardojob/app 


