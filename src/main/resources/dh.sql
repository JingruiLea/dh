CREATE TABLE dh.doctor
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(300) NOT NULL,
    hospital_name varchar(50) NOT NULL,
    department_name varchar(50) NOT NULL
);
INSERT INTO dh.doctor (id, name, username, password, hospital_name, department_name) VALUES (10, '123', '233', '41241', '315', '14');
INSERT INTO dh.doctor (id, name, username, password, hospital_name, department_name) VALUES (11, 'admin', 'admin', '123456', 'test', 'omg');
CREATE TABLE dh.patient
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    sex varchar(50) NOT NULL,
    age varchar(50) NOT NULL,
    doctor_id int(11) NOT NULL,
    disease_name varchar(50) NOT NULL
);
INSERT INTO dh.patient (id, name, sex, age, doctor_id, disease_name) VALUES (2, 'Sam', '女', '22', 10, '没有123病');
CREATE TABLE dh.scale_score
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    patient_id int(11) NOT NULL,
    score varchar(30) NOT NULL,
    time varchar(50) NOT NULL
);
CREATE TABLE dh.train_score
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    patient_id int(11) NOT NULL,
    score varchar(30) NOT NULL,
    time varchar(50) NOT NULL,
    path varchar(50) NOT NULL
);