PRAGMA foreign_keys = OFF;
BEGIN TRANSACTION;
CREATE TABLE Users (
  username VARCHAR NOT NULL UNIQUE,
  password VARCHAR NOT NULL,
  usertype VARCHAR NOT NULL
);
INSERT INTO Users VALUES ('asdasd', 'asdasd', 'TEACHER');
INSERT INTO Users VALUES ('foo', 'bar', 'STUDENT');
CREATE TABLE Videos (

  videoId    INTEGER PRIMARY KEY,

  name       VARCHAR NOT NULL,

  uploader   VARCHAR NOT NULL,

  category   VARCHAR NOT NULL,

  uploadTime VARCHAR NOT NULL,

  likes      INTEGER NOT NULL

);
INSERT INTO Videos VALUES (1, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:29:50.173+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (2, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:33:11.927+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (3, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:38:11.335+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (4, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:40:02.445+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (5, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:42:21.903+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (6, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:44:26.290+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (7, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:47:38.968+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (8, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:52:22.827+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (9, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:53:31.498+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (10, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:54:51.447+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (11, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:57:49.292+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (12, 'fade', 'waqar', 'Data-Structures', '2017-09-03T13:59:07.163+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (13, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:00:32.517+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (14, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:02:28.298+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (15, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:03:08.245+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (16, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:04:57.381+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (17, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:06:08.546+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (18, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:07:31.622+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (19, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:07:47.335+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (20, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:08:51.033+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (21, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:09:58.826+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (22, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:11:56.389+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (23, 'fade', 'waqar', 'Data-Structures', '2017-09-03T14:13:28.956+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos
VALUES (24, 'ameer_pyaar', 'UnoRespublicanas', 'Music', '2017-09-03T14:18:52.770+05:30[Asia/Calcutta]', 1);
CREATE TABLE Comments (

  videoId   INTEGER PRIMARY KEY,

  message   VARCHAR NOT NULL,

  commenter VARCHAR NOT NULL,

  dateTime  VARCHAR NOT NULL

);
INSERT INTO Comments VALUES (24, 'Great Song One republic', 'rc29', '2017-09-03T16:28:48.428+05:30[Asia/Calcutta]');
CREATE TABLE Likes (

  videoId  INTEGER PRIMARY KEY,

  dateTime VARCHAR NOT NULL

);
COMMIT;
