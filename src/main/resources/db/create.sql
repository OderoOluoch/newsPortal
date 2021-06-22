SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    deptId int
);

CREATE TABLE IF NOT EXISTS departments (
    id int PRIMARY KEY auto_increment,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    content VARCHAR,
    deptid INTEGER,
    authorid INTEGER
);