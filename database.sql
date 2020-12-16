DROP SCHEMA IF EXISTS sep3db CASCADE;

Create Schema sep3db;

SET Schema 'sep3db';

Create Table "UserAccount"
(
    userId SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    firstName VARCHAR(256) NOT NULL,
    lastName VARCHAR(256) NOT NULL,
    description VARCHAR(350) NOT NULL,
    img bytea,
    securityLevel int NOT NULL
);
Create Table "User"
(
    userId SERIAL NOT NULL,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    securityLevel int NOT NULL,

	Foreign key(userId) references "UserAccount"(userId)
);

Create Table "Chat"
(
    userId SERIAL NOT NULL,
    username VARCHAR(256) NOT NULL,
    message VARCHAR(1000) NOT NULL,
    Foreign key(userId) references "UserAccount"(userId)
);