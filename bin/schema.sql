drop table if exists customer;

create table customer (
customer_id LONG IDENTITY PRIMARY KEY,
firstName varchar(15),
lastName varchar(15),
BI varchar(15),
balance DECIMAL,
phones varchar(300)
);