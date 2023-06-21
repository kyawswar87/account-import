-- liquibase formatted sql

-- changeset kyawswar:1
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `role` enum('ROLE_ADMIN', 'ROLE_USER') DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `trx_amount` double NOT NULL,
  `trx_date` date DEFAULT NULL,
  `trx_time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO demo_db.app_user
(id, user_name, password_hash, first_name, last_name, email, image_url, role)
VALUES(1, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'administrator', '', 'kyawswaaung87@gmail.com', '', 'ROLE_ADMIN');

INSERT INTO demo_db.app_user
(id, user_name, password_hash, first_name, last_name, email, image_url, role)
VALUES(2, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@gmail.com', '','ROLE_USER');