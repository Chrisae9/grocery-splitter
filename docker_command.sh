docker run --name grocery-postgres -e POSTGRES_PASSWORD=grocery -d -p 5432:5432 postgres:alpine


# Commands

# docker start grocery-postgres
# docker ps
# docker exec -it [container id] bin/bash

# once in container, enter postgres
# psql -U postgres
# CREATE DATABASE grocerydb;
# \l
# \c grocerydb
# \d
# \dt
# CREATE EXTENSION "uuid-ossp";

# SELECT uuid_generate_V4();

# INSERT INTO Receipt (id, name) VALUES (uuid_generate_V4(), 'example');
# SELECT * FROM Receipt;
# INSERT INTO Item (receiptid, id, name, cost) VALUES ('e025a961-dcc5-48ac-95a4-d36d43577ee2',uuid_generate_V4(), 'Apple', 4.00);
# INSERT INTO Item (receiptid, id, name, cost) VALUES ('e025a961-dcc5-48ac-95a4-d36d43577ee2',uuid_generate_V4(), 'Pear', 3.00);
# INSERT INTO Item (receiptid, id, name, cost) VALUES ('e025a961-dcc5-48ac-95a4-d36d43577ee2',uuid_generate_V4(), 'Hot Tea', 1.00);
# SELECT * FROM Item;
# SELECT r.id, r.name, i.name, i.cost FROM Item i INNER JOIN Receipt r ON i.id = r.id;