CREATE TABLE USERS(
    USERNAME varchar_ignorecase(50) not null primary key,
    PASSWORD varchar_ignorecase(50) not null,
    ENABLED boolean not null
);

CREATE TABLE AUTHORITIES (
    USERNAME varchar_ignorecase(50) not null,
    AUTHORITY varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(USERNAME) references USERS(USERNAME)
);
CREATE UNIQUE INDEX ix_auth_username on AUTHORITIES (USERNAME,AUTHORITY);