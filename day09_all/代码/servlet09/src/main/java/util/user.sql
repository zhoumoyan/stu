-- 建库
create database jsd1812db character set utf8;

-- 使用库
use jsd1812db;

-- 建表
create table t_user(
id int primary key auto_increment,
username varchar(50) unique,
password varchar(50),
email varchar(50)
);






