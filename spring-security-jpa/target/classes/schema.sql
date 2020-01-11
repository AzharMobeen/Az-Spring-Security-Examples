drop TABLE  IF EXISTS `AUTHORITIES`,`USERS`;
CREATE TABLE USERS(
	Id int	not null AUTO_INCREMENT primary key ,
    user_name varchar(50) not null,
    PASSWORD varchar(50) not null,
    ENABLED boolean not null
);

CREATE TABLE AUTHORITIES (
	Id int	not null AUTO_INCREMENT primary key ,
    user_name varchar(50) not null,
    AUTHORITY varchar(50) not null
    --constraint fk_authorities_users foreign key(user_name) references USERS(user_name)
);
CREATE UNIQUE INDEX ix_auth_username on AUTHORITIES (user_name,AUTHORITY);