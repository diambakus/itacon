drop table if exists basic_entity;

create table basic_entity(
  id LONG IDENTITY PRIMARY KEY
);

drop table if exists contribuinte;

create table contribuinte (
  id LONG IDENTITY PRIMARY KEY,
  balance DECIMAL,
  phone varchar(300),
);


drop table if exists individuo;

create table individuo (
id LONG IDENTITY PRIMARY KEY,
first_name varchar(15),
last_name varchar(15),
BI varchar(15),
balance DECIMAL,
phone varchar(300),
NIF LONG
);

drop table if exists colectiva;

create table colectiva(
 if LONG IDENTITY PRIMARY KEY,
 name varchar(30),
 balance DECIMAL,
 phone varchar(300),
 NIPC LONG
);

drop table if exists institution;
create table institution (
 id LONG IDENTITY PRIMARY KEY,
 name varchar(50),
 code varchar(10)
);

alter table if exists institution add constraint UK_name unique (name);

drop table if exists service;
create table service (
  id LONG IDENTITY PRIMARY KEY,
  name varchar(50),
  code varchar(10),
  price DECIMAL,
  institution_id LONG references institution(id)
);

drop table if exists user;
create table user (
id long identity primary key,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL,
username varchar(30) NOT NULL,
email varchar(80) NOT NULL,
password varchar(150) NOT NULL,
institution_id long references institution(id),
role character varying(10) not null,
enable boolean not null
);
