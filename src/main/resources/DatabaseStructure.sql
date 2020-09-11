-- adaptative_learning.departments definition

CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.cities definition

CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `id_department` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cities_FK` (`id_department`),
  CONSTRAINT `cities_FK` FOREIGN KEY (`id_department`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.difficulties definition

CREATE TABLE `difficulties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO difficulties (name) VALUES
('Bajo')
,('Basico')
,('Alto')
;

-- adaptative_learning.grade definition

CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.areas definition

CREATE TABLE `areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `id_grade` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `areas_FK` (`id_grade`),
  CONSTRAINT `areas_FK` FOREIGN KEY (`id_grade`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.categories definition

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `id_area` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categories_FK` (`id_area`),
  CONSTRAINT `categories_FK` FOREIGN KEY (`id_area`) REFERENCES `areas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.dbas definition

CREATE TABLE `dbas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dbas_FK` (`id_category`),
  CONSTRAINT `dbas_FK` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.themes definition

CREATE TABLE `themes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `id_dba` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `themes_FK` (`id_dba`),
  CONSTRAINT `themes_FK` FOREIGN KEY (`id_dba`) REFERENCES `dbas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.schools definition

CREATE TABLE `schools` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `id_city` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `schools_FK_1` (`id_city`),
  CONSTRAINT `schools_FK_1` FOREIGN KEY (`id_city`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.roles definition

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO roles (name,description) VALUES
('ADMIN',NULL)
,('PROFESOR',NULL)
,('ESTUDIANTE',NULL)
;

-- adaptative_learning.users definition

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_number` int(11) NOT NULL,
  `names` varchar(100) NOT NULL,
  `last_names` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(61) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_school` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_id_number_IDX` (`id_number`) USING BTREE,
  KEY `users_FK` (`id_role`),
  KEY `users_FK_1` (`id_school`),
  CONSTRAINT `users_FK` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`),
  CONSTRAINT `users_FK_1` FOREIGN KEY (`id_school`) REFERENCES `schools` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.parameters definition

CREATE TABLE `parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parameter_key` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO parameters (parameter_key,value,description) VALUES
('NUMBER_QUESTIONS','15','Numero de preguntas asignadas a los examenes, debe ser un numero divisible por 3 con el fin de tener el mismo numero de preguntas por cada dificultad (bajo, basico y alto)')
;

-- adaptative_learning.media_content definition

CREATE TABLE `media_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_content` varchar(100) NOT NULL,
  `mime` varchar(100) NOT NULL,
  `ref_content` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.questions definition

CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `id_theme` int(11) NOT NULL,
  `id_difficulty` int(11) NOT NULL,
  `id_content` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `questions_FK` (`id_theme`),
  KEY `questions_FK_1` (`id_difficulty`),
  KEY `questions_FK_2` (`id_content`),
  CONSTRAINT `questions_FK` FOREIGN KEY (`id_theme`) REFERENCES `themes` (`id`),
  CONSTRAINT `questions_FK_1` FOREIGN KEY (`id_difficulty`) REFERENCES `difficulties` (`id`),
  CONSTRAINT `questions_FK_2` FOREIGN KEY (`id_content`) REFERENCES `media_content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.answers definition

CREATE TABLE `answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(300) DEFAULT NULL,
  `is_correct` tinyint(1) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_content` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `answers_FK` (`id_question`),
  KEY `answers_FK_1` (`id_content`),
  CONSTRAINT `answers_FK` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`),
  CONSTRAINT `answers_FK_1` FOREIGN KEY (`id_content`) REFERENCES `media_content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.reinforcement definition

CREATE TABLE `reinforcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `link` varchar(300) NOT NULL,
  `id_theme` int(11) NOT NULL,
  `id_difficulty` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reinforcement_FK` (`id_theme`),
  KEY `reinforcement_FK_1` (`id_difficulty`),
  CONSTRAINT `reinforcement_FK` FOREIGN KEY (`id_theme`) REFERENCES `themes` (`id`),
  CONSTRAINT `reinforcement_FK_1` FOREIGN KEY (`id_difficulty`) REFERENCES `difficulties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.student_assignment definition

CREATE TABLE `student_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_teacher` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_grade` int(11) NOT NULL,
  `id_area` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_assignment_UN` (`id_student`,`id_grade`,`id_area`),
  KEY `student_assignment_FK` (`id_teacher`),
  KEY `student_assignment_FK_2` (`id_grade`),
  KEY `student_assignment_FK_3` (`id_area`),
  CONSTRAINT `student_assignment_FK` FOREIGN KEY (`id_teacher`) REFERENCES `users` (`id`),
  CONSTRAINT `student_assignment_FK_1` FOREIGN KEY (`id_student`) REFERENCES `users` (`id`),
  CONSTRAINT `student_assignment_FK_2` FOREIGN KEY (`id_grade`) REFERENCES `grade` (`id`),
  CONSTRAINT `student_assignment_FK_3` FOREIGN KEY (`id_area`) REFERENCES `areas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.exams definition

CREATE TABLE `exams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `is_private` tinyint(1) NOT NULL DEFAULT '1',
  `id_teacher` int(11) NOT NULL,
  `id_grade` int(11) NOT NULL,
  `id_area` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  `id_dba` int(11) NOT NULL,
  `id_theme` int(11) NOT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exams_FK` (`id_grade`),
  KEY `exams_FK_1` (`id_area`),
  KEY `exams_FK_2` (`id_category`),
  KEY `exams_FK_3` (`id_dba`),
  KEY `exams_FK_4` (`id_theme`),
  KEY `exams_FK_5` (`id_teacher`),
  CONSTRAINT `exams_FK` FOREIGN KEY (`id_grade`) REFERENCES `grade` (`id`),
  CONSTRAINT `exams_FK_1` FOREIGN KEY (`id_area`) REFERENCES `areas` (`id`),
  CONSTRAINT `exams_FK_2` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`),
  CONSTRAINT `exams_FK_3` FOREIGN KEY (`id_dba`) REFERENCES `dbas` (`id`),
  CONSTRAINT `exams_FK_4` FOREIGN KEY (`id_theme`) REFERENCES `themes` (`id`),
  CONSTRAINT `exams_FK_5` FOREIGN KEY (`id_teacher`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.exam_student definition

CREATE TABLE `exam_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_exam` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `assignment_date` date NOT NULL,
  `realization_date` date DEFAULT NULL,
  `questions` varchar(100) DEFAULT NULL,
  `answers` varchar(100) DEFAULT NULL,
  `reinforcements` varchar(100) DEFAULT NULL,
  `try_number` int(11) NOT NULL,
  `result` int(11) DEFAULT NULL,
  `state` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_student_FK` (`id_exam`),
  KEY `exam_student_FK_1` (`id_student`),
  CONSTRAINT `exam_student_FK` FOREIGN KEY (`id_exam`) REFERENCES `exams` (`id`),
  CONSTRAINT `exam_student_FK_1` FOREIGN KEY (`id_student`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- adaptative_learning.exam_student_reinforcement definition

CREATE TABLE `exam_student_reinforcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_exam_student` int(11) NOT NULL,
  `id_reinforcement` int(11) NOT NULL,
  `is_read` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `exam_student_reinforcement_FK` (`id_exam_student`),
  KEY `exam_student_reinforcement_FK_1` (`id_reinforcement`),
  CONSTRAINT `exam_student_reinforcement_FK` FOREIGN KEY (`id_exam_student`) REFERENCES `exam_student` (`id`),
  CONSTRAINT `exam_student_reinforcement_FK_1` FOREIGN KEY (`id_reinforcement`) REFERENCES `reinforcement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

