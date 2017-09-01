drop table if exists basic_entity;

create table basic_entity(
  id LONG IDENTITY PRIMARY KEY
);

drop table if exists customer;

create table customer (
id LONG IDENTITY PRIMARY KEY,
first_name varchar(15),
last_name varchar(15),
BI varchar(15),
balance DECIMAL,
phone varchar(300),
NIF LONG
);

drop table if exists public_institution;
create table public_institution (
 id LONG IDENTITY PRIMARY KEY,
 name varchar(50),
 code varchar(10)
);

drop table if exists service;
create table service (
  id LONG IDENTITY PRIMARY KEY,
  name varchar(50),
  code varchar(10),
  price DECIMAL,
  public_institution_id LONG references public_institution(id)
);

drop table if exists user;
create table user (
id long identity primary key,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL,
username varchar(30) NOT NULL,
email varchar(80) NOT NULL,
password varchar(150) NOT NULL,
public_institution_id long references public_institution(id),
role character varying(10) not null,
enable boolean not null
);
