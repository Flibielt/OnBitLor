CREATE TABLE `players` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) UNIQUE NOT NULL,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `bit` int
);

CREATE TABLE `roles` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE NOT NULL
);

CREATE TABLE `user_roles` (
  `player_id` int,
  `role_id` int,
  PRIMARY KEY (`player_id`, `role_id`)
);

CREATE TABLE `programming` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE NOT NULL,
  `description` text NOT NULL,
  `bit` int
);

CREATE TABLE `programming_statistics` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `programming_id` int NOT NULL,
  `player_id` int NOT NULL,
  `date` datetime NOT NULL,
  `score` int NOT NULL
);

CREATE TABLE `tests` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE NOT NULL,
  `description` text NOT NULL,
  `bit` int
);

CREATE TABLE `test_results` (
  `id` int PRIMARY KEY,
  `player_id` int,
  `test_id` int,
  `date` datetime NOT NULL,
  `result` long
);

ALTER TABLE `programming_statistics` ADD FOREIGN KEY (`programming_id`) REFERENCES `programming` (`id`);

ALTER TABLE `programming_statistics` ADD FOREIGN KEY (`player_id`) REFERENCES `players` (`id`);

ALTER TABLE `user_roles` ADD FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

ALTER TABLE `user_roles` ADD FOREIGN KEY (`player_id`) REFERENCES `players` (`id`);

ALTER TABLE `test_results` ADD FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`);

ALTER TABLE `test_results` ADD FOREIGN KEY (`player_id`) REFERENCES `players` (`id`);

