# --- !Ups

alter table timeslot add column taskId bigint(20);

alter table timeslot add foreign key (taskId) references task (id);

# --- !Downs