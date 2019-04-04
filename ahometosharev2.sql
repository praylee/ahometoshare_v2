DROP SCHEMA IF EXISTS `ahometosharev2` ;
CREATE DATABASE `ahometosharev2`;
USE `ahometosharev2`;
CREATE TABLE IF NOT EXISTS `ahometosharev2`.`host`(
`host_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR(42) NOT NULL unique,
`password` varchar(64) NOT NULL,
`first_name` VARCHAR(45) NOT NULL,
`last_name` VARCHAR(45) NOT NULL,
`phone` varchar(12) NOT NULL,
`gender` int(1) NOT NULL,
`date_of_birth`varchar(4) NOT NULL,
`retired` boolean ,
`pets` boolean ,
`smoker` boolean ,
`referral_source` VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS `ahometosharev2`.`renter`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR(42) NOT NULL unique,
`password` varchar(64) NOT NULL,
`first_name` VARCHAR(45) NOT NULL,
`last_name` VARCHAR(45) NOT NULL,
`phone` varchar(12) NOT NULL,
`gender` INTEGER(1) NOT NULL,
`date_of_birth`varchar(4) NOT NULL,
`student` boolean ,
`employed` boolean ,
`smoker` boolean ,
`rent_start_date`Date,
`rent_end_date`DATE ,
`availability`int(1) ,
`low_price` DOUBLE ,
`high_price` DOUBLE ,
`referral_source` VARCHAR(45) ,
`criminality_check` boolean 
);

CREATE TABLE IF NOT EXISTS `ahometosharev2`.`property`(
`property_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`host_id` int NOT NULL,
CONSTRAINT `host_id_p`
    FOREIGN KEY (`host_id`)
    REFERENCES `ahometosharev2`.`host` (`host_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
`address` VARCHAR(45) NOT NULL,
`city` VARCHAR(45) NOT NULL,
`postal_code` VARCHAR(45) NOT NULL,
`province` VARCHAR(45) NOT NULL,
`country` VARCHAR(45) NOT NULL,
`family_members` INT(1) NOT NULL,
`smoker` boolean ,
`pets` boolean ,
`hydro` boolean,
`water` boolean,
`gas` boolean,
`cable` boolean,
`internet` boolean,
`parking` boolean,
`laundry` boolean,
`family_room` boolean,
`private_bedroom` boolean,
`shared_bedroom` boolean,
`private_kitchen` boolean,
`shared_kitchen` boolean,
`private_washroom` boolean,
`shared_washroom` boolean,
`price` DOUBLE,
`host_start_date`DATE NOT NULL,
`host_end_date`DATE NOT NULL,
`shared_chore` VARCHAR(45) NOT NULL,
`availability`int(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS`ahometosharev2`.`property_picture`(
`picture_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`property_id` int NOT NULL,
CONSTRAINT `property_id_t`
    FOREIGN KEY (`property_id`)
    REFERENCES `ahometosharev2`.`property` (`property_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
`picture` LONGBLOB NOT NULL
);

CREATE TABLE IF NOT EXISTS`ahometosharev2`.`admin`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`username` VARCHAR(42) NOT NULL unique,
`password` VARCHAR(64) NOT NULL,
`valid` boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS `ahometosharev2`.`login_record`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(42),
    `usertype` int,
    `time` date
);

CREATE TABLE IF NOT EXISTS `ahometosharev2`.`article`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(42) NOT NULL,
    `content` MEDIUMTEXT,
    `update_date` DATE
    );


CREATE TABLE IF NOT EXISTS `ahometosharev2`.`home_request`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `renter` VARCHAR(45) NOT NULL,
	`renter_name` VARCHAR(45) NOT NULL,
    `host` VARCHAR(45) NOT NULL,
    `host_name` VARCHAR(45) NOT NULL,
    `property_address` VARCHAR(45),
    `request_time` timestamp NOT NULL
);


insert into `ahometosharev2`.`admin` (`username`,`password`,`valid`) values ('super','e95407ecadce63bf95fd6cac612ec972',true);  -- super ahometoshare

INSERT INTO `ahometosharev2`.`article` (`title`,`update_date`) values ('Terms of Use',CURDATE());
INSERT INTO `ahometosharev2`.`article` (`title`,`update_date`) values ('Privacy Policy',CURDATE());
