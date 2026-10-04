-- Run manually in Sqlectron against your Railway MySQL service.
-- Tables and seed accounts are based on scripts.sql. Run once on a fresh database.
-- customer.id uses BIGINT to match the Customer entity's Java long field.
CREATE DATABASE IF NOT EXISTS vixitbank CHARACTER SET utf8mb4;
USE vixitbank;

create table users(username varchar(100) not null primary key,password varchar(1000) not null,enabled boolean not null);
create table authorities (username varchar(100) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


INSERT IGNORE INTO `users` VALUES ('user', '{noop}vixit@4321','1');
INSERT IGNORE INTO `authorities` VALUES ('user','read');

INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$UmXYHmvknpgQD0/.7Gpto.Q5FUSSK4dZxZZGRJe/e81LEX3oFfuJG','1');
INSERT IGNORE INTO `authorities` VALUES ('admin','admin');



CREATE TABLE `customer` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(500) NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`,`password`,`role`) VALUES ('user@test.com','{noop}vixit@4321','user');
INSERT INTO `customer` (`email`,`password`,`role`) VALUES ('admin@vixitbank.com','{bcrypt}$2a$12$UmXYHmvknpgQD0/.7Gpto.Q5FUSSK4dZxZZGRJe/e81LEX3oFfuJG','admin');
