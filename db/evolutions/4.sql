# --- !Ups

alter table TimeSlot add column taskId bigint(20);

alter table TimeSlot add foreign key (taskId) references Task (id);

# --- !Downs