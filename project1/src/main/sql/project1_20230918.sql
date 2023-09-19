CREATE DATABASE `project_01`;

use `project_01`;

CREATE TABLE `project_01`.`users` (
  `email` varchar(320) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `last_login_at` datetime DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE `project_01`.`posts` (
  `post_id` integer NOT NULL auto_increment,
  `email` varchar(320) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `post_UNIQUE` (`post_id`)
);

CREATE TABLE `project_01`.`comments` (
  `comment_id` integer NOT NULL auto_increment,
  `post_id` integer NOT NULL,
  `email` varchar(320) NOT NULL,
  `content` text NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_UNIQUE` (`comment_id`)
);

ALTER TABLE `project_01`.`posts` ADD FOREIGN KEY (`email`) REFERENCES `project_01`.`users` (`email`);
ALTER TABLE `project_01`.`comments` ADD FOREIGN KEY (`email`) REFERENCES `project_01`.`users` (`email`);
ALTER TABLE `project_01`.`comments` ADD FOREIGN KEY (`post_id`) REFERENCES `project_01`.`posts` (`post_id`);
