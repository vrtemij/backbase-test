CREATE SCHEMA IF NOT EXISTS moviedb;

CREATE TABLE tab_app_users
(
    id        int          not null primary key unique,
    password  varchar(255) not null,
    role_name varchar(255) null,
    username  varchar(255) not null
);

CREATE TABLE tab_awards_information
(
    id              int          not null primary key unique,
    additional_info text         null,
    category        varchar(255) null,
    nominee         text         null,
    won             varchar(255) null,
    year            varchar(255) null
);

CREATE TABLE tab_user_movie
(
    id          int          not null primary key unique,
    box_office  bigint       null,
    user_rating int          null,
    title       varchar(255) null,
    user_id     int          null
);

