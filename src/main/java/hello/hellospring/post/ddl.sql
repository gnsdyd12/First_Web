drop table post;
create table post(
    id bigint auto_increment ,
    password varchar(60),
    username varchar(20),
    title varchar(255),
    contents varchar(12000),
    link varchar(1023),
    songtitle varchar (255),
    artist varchar(255),
    primary key (id)
);