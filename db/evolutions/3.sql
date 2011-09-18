# --- !Ups

CREATE TABLE Task (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255),
    PRIMARY KEY (id)
) ENGINE=INNODB;

insert into Task (name) values ('Defacto project');

CREATE TABLE Person (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    initials varchar(255),
    firstName varchar(255),
    lastName varchar(255),
    PRIMARY KEY (id)
) ENGINE=INNODB;

insert into Person (initials,firstName,lastName) values ('j','joris','wijlens');
insert into Person (initials,firstName,lastName) values ('j','jan-kees','hartog');

CREATE TABLE Executor (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    task_id bigint(20),
    person_id bigint(20),
    PRIMARY KEY (id)
) ENGINE=INNODB;

insert into Executor (task_id,person_id) values (1,1);

CREATE TABLE Authenticator (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    task_id bigint(20),
    person_id bigint(20),
    PRIMARY KEY (id)
) ENGINE=INNODB;

insert into Authenticator (task_id,person_id) values (1,2);

# --- !Downs