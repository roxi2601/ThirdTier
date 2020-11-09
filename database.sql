Create Schema sep3db;

SET Schema 'sep3db';

Create Table UserAccount
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    securityLevel int NOT NULL
);

