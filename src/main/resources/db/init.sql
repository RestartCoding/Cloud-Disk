create table users(
    id int auto_increment,
    username varchar(32) not null,
    password varchar(64) not null,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp,
    primary key (id),
    unique(username)
);

create table file(
    id int auto_increment,
    user_id int not null,
    filename varchar(128) not null,
    size bigint,
    upload_time timestamp not null,
    url varchar(256),
    type varchar(32) not null,
    pid int,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default  current_timestamp,
    primary key (id),
    unique (url),
    unique (user_id, pid, filename)
);