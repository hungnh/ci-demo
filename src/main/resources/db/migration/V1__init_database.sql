CREATE TABLE APP_USER
(
  ID         BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  EMAIL      VARCHAR(255),
  FIRST_NAME VARCHAR(255),
  LAST_NAME  VARCHAR(255),
  USERNAME   VARCHAR(255)
);

INSERT INTO APP_USER (EMAIL, FIRST_NAME, LAST_NAME, USERNAME) VALUES ('hungnh.uet@gmail.com', 'Jon', 'Snow', 'hungnh');
INSERT INTO APP_USER (EMAIL, FIRST_NAME, LAST_NAME, USERNAME) VALUES ('robb.stark@gmail.com', 'Robb', 'Stark', 'robb');
INSERT INTO APP_USER (EMAIL, FIRST_NAME, LAST_NAME, USERNAME) VALUES ('arya.stark@gmail.com', 'Arya', 'Stark', 'arya');