CREATE DATABASE IF NOT EXISTS `SevenTravel`;
USE `SevenTravel`;

CREATE TABLE IF NOT EXISTS `Driver`(
    `id` INT(11) NOT NULL UNIQUE,
    `name` VARCHAR(255) NOT NULL,
    `passportNumber` VARCHAR(100) NOT NULL,
    `experience` INT(3),
    `age` INT(3),
    `rating` INT(2),
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `Car`(
    `id` VARCHAR(40) NOT NULL UNIQUE,
    `name` VARCHAR(255) NOT NULL,
    `price` int(100) NOT NULL,
    `info` VARCHAR(500),
    `numberOfSeats` INT(3),
    `carNumber` VARCHAR(10),
    `drive` VARCHAR(15),
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `Helicopter`(
    `id` VARCHAR(40) NOT NULL UNIQUE,
    `name` VARCHAR(255) NOT NULL,
    `helicopterNumber` VARCHAR(100) NOT NULL,
    `price` INT(7),
    `info` VARCHAR(500),
    `numberOfSeats` INT(3),
    `length` DOUBLE(3,3),
    `width` DOUBLE(3,3),
    `maxSpeed` DOUBLE(3,3),
    `numberOfScrews` INT(2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `User`(
    `id` VARCHAR(40) NOT NULL UNIQUE,
    `fullname` VARCHAR(50),
    `phonenumber` VARCHAR(12),
    `password` VARCHAR(30),
    `numberOfTravels` INT(11),
    `status` VARCHAR(20),
    PRIMARY KEY(`id`)
);
