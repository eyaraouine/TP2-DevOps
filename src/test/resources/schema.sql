create table users_table (
    usersId integer not null auto_increment,
    username varchar(255),
    firstName varchar(255),
    lastName varchar(255),
    userRole varchar(255),
    createdAt datetime,
    updatedAt datetime,
    primary key (usersId)
) ENGINE=MyISAM;
