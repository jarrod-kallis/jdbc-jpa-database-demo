-- This gets called because of auto configuration, but it must be called data.sql
create table person (
	id integer not null,
	name varchar(255) not null,
	location varchar(255),
	dob timestamp not null,
	primary key (id)
);

insert into person values (10000, 'Michael', 'Southfield', sysdate() - 10000);
insert into person values (10001, 'Jarrod', 'Home', sysdate() - 3000);
insert into person values (10002, 'Tess', 'Work', sysdate() - 2000);
insert into person values (10003, 'Ben', 'School', sysdate() - 300);
