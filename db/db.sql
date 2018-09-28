CREATE DATABASE  IF NOT EXISTS `abc1`;
USE `abc1`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `userFirstName` varchar(64) NOT NULL,
  `userLastName` varchar(64) NOT NULL,
  `userPassword` char(60) NOT NULL,
  `userAdmin` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  KEY `idx_user_common` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES (1,'admin','admin','admin','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.',''),(2,'user','Danilo','Madrigalejosc','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(3,'user1','Rom','Vill','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(4,'user2','Test','Lang','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(12,'user3','Maddy','Lim','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(13,'user4','Maddy','Lao','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(14,'user5','Fe','Pog','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(15,'user6','Dan','Dan','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(17,'user7','Hahaha','Ida','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(21,'user10','Huhuhu','Wha','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0'),(22,'user11','Bom','Bom','$2a$10$lqm5rIvcBVqW6JOa/CEBqupJ3INFV5BOYdZ29p49ArtREngYZlEV.','\0');

UNLOCK TABLES;