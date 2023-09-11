CREATE TABLE human (Id SERIAL PRIMARY KEY ,
Name VARCHAR,
Age INTEGER,
License BOOLEAN,
Car_id text REFERENCES car(Id));


CREATE TABLE car (Id SERIAL,
Brand VARCHAR,
model varchar,
Price serial);
