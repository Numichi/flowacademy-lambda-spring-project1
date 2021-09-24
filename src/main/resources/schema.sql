create table many2
(
    id int auto_increment
        primary key
);

create table users
(
    id       int auto_increment
        primary key,
    passwd   tinytext     not null,
    username varchar(255) not null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table comment
(
    id      int auto_increment
        primary key,
    message text        null,
    time    datetime(6) null,
    user_id int         null,
    constraint FKqm52p1v3o13hy268he0wcngr5
        foreign key (user_id) references users (id)
);

create table track_provider
(
    a_id int not null,
    b_id int not null,
    constraint FKfw98vnkn51ptr47timivm0ycr
        foreign key (b_id) references many2 (id),
    constraint FKs29n432evmhp4gyfu06539tr1
        foreign key (a_id) references comment (id)
);



