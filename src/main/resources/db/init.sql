create table users(
    id int auto_increment,
    username varchar(32) not null,
    password varchar(64) not null,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,
    primary key (id),
    unique(username)
);