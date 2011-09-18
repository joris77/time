# --- !Ups

CREATE TABLE User (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    username varchar(255),
    password varchar(255),
    PRIMARY KEY (id)
) ENGINE=INNODB;

insert into User (username,password) values ('joris77','apenootje');

# --- !Downs