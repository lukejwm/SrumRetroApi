drop table if exists `user`;

-- create the `user` table on initialisation of the Spring Boot Service
create table `user`
(
    id        int          not null primary key auto_increment,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    username  varchar(100) not null,
    email     varchar(100) not null
);
