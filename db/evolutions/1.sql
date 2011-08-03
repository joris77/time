# --- !Ups
 
CREATE TABLE TimeSlot (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    beginTime datetime,
    endTime datetime,
    description varchar(255),
    PRIMARY KEY (id)
);
 
# --- !Downs