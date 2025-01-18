create table topics(

    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(800) not null,
    creation_date datetime not null ,
    status varchar(100) not null,
    author varchar(100) not null,
    course varchar(100) not null,

    primary key(id)

);
