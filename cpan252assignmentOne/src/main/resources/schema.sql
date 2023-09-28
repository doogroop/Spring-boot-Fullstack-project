create table if not exists item (
  id identity,
  name varchar(50) not null,
  brand varchar(100) not null,
  yearOfCreation INT
);