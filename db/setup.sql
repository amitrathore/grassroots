create database grassroots_dev;
CREATE USER 'grassroots_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON grassroots_dev.* TO 'grassroots_user'@'%';

CREATE DATABASE ejabberd;
GRANT ALL ON ejabberd.* TO 'ejabberd'@'%' IDENTIFIED BY 'password';
