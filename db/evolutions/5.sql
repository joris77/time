# --- !Ups
CREATE TABLE address (
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

alter table organisation add foreign key (addressId) references address (id);

alter table task add column organisationId bigint(20);

alter table task add foreign key (organisationId) references organisation (id);


insert into address (street,houseNumber,city,postalCode) ('Plotterweg','26-28','Amersfoort','3821 BB');


insert into organisation (name, organisationId) values ('Fa-med',1);

update task set organisationId = 1;

# --- !Downs