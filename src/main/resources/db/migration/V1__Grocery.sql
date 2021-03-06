CREATE TABLE IF NOT EXISTS Receipt (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Item (
    receiptid VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    cost INTEGER,
    FOREIGN KEY(receiptid) REFERENCES Receipt(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Member (
    id VARCHAR(100) NOT NULL,
    userEmail VARCHAR(100) NOT NULL, 
    userPassword VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL
);