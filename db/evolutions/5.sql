# --- !Ups
CREATE TABLE Address (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    street varchar(255),
    houseNumber varchar(255),
    city varchar(255),
    postalCode varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE Organisation (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255),
    addressId bigint(20),
    PRIMARY KEY (id)
);

alter table Organisation add foreign key (addressId) references Address (id);

alter table Task add column organisationId bigint(20);

alter table Task add foreign key (organisationId) references Organisation (id);

insert into Address (street,houseNumber,city,postalCode) values ('Plotterweg','26-28','Amersfoort','3821 BB');

insert into Organisation (name, organisationId) values ('Fa-med',1);

update Task set organisationId = 1;

# --- !Downs