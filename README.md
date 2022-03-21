# quarkus-microservices-java
Creating microservices with quarkus like uber eats and ifood

1. Go to cadastro/src/main/resources and execute docker-compose up to create instances of mongoDB, postgres and artemismq. This docker-compose has important configurations to run project including username and password.
2. The project has 2 main apis:
    a. Cadastro - to create restaurants and dish (complete CRUD created with Panache Quarkus)
    b. Pedido - to create request for restaurantes with location and more, I used mongodB for it. (PanacheMongoEntity).
3. All APIS have swagger configurated, you can make request accessing: http://localhost:8082/q/swagger-ui/ (please, check the port of each API)

