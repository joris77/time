# --- !Ups

alter table Timeslot add column taskId bigint(20);

alter table Timeslot add foreign key (taskId) references Task (id);

# --- !Downs