CREATE TABLE people
(
    id           serial not null,
    email        varchar(255),
    firstname    varchar(30),
    patronymic   varchar(255),
    phone_number varchar(255),
    surname      varchar(30),
    primary key (id)
);