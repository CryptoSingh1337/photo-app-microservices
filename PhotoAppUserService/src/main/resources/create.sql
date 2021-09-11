use users;

create table role (id integer not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table user (id varchar(255) not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table user_role (user_id varchar(255) not null, role_id integer not null) engine=InnoDB;
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);