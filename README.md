# Grocery Splitter

A spring-boot web application that serves the purpose of splitting a receipt between a group of people. Targeted at college students and first-time apartment renters. Our application will eliminate the need to do manual receipt calculations in order to save time and increase splitting accuracy. Unlike Venmo’s bill splitting feature, our application will be able to split the receipt by each individual grocery item.

## Usage

```bash
# Start a docker container running postgres on port 5432
docker run --name grocery-postgres -e POSTGRES_PASSWORD=grocery -d -p 5432:5432 postgres:alpine
docker start grocery-postgres
docker ps
docker exec -it [container-id] bin/bash

# In container
psql -U postgres
CREATE DATABASE grocerydb;

# Outside of container
./mvnw spring-boot:run
```

