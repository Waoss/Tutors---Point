CREATE TABLE Categories (
  name VARCHAR NOT NULL
);
CREATE TABLE Comments (
  videoId   INTEGER NOT NULL,
  message   VARCHAR NOT NULL,
  commenter VARCHAR NOT NULL,
  dateTime  VARCHAR NOT NULL
);
CREATE TABLE Likes (
  videoId  INTEGER NOT NULL,
  dateTime VARCHAR NOT NULL
);
CREATE TABLE Users (
  username VARCHAR UNIQUE NOT NULL,
  password VARCHAR        NOT NULL,
  userype  VARCHAR        NOT NULL
);
CREATE TABLE Videos (
  videoId    INTEGER NOT NULL PRIMARY KEY,
  name       VARCHAR NOT NULL,
  uploader   VARCHAR NOT NULL,
  category   VARCHAR NOT NULL,
  uploadTime VARCHAR NOT NULL,
  likes      INTEGER NOT NULL
);
INSERT INTO Videos VALUES (1, 'Fade', 'AlanWalker', 'Music', '2017-09-04T17:55:20.564+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (2, 'Fade', 'AlanWalker', 'Music', '2017-09-04T18:00:38.412+05:30[Asia/Calcutta]', 1);
INSERT INTO Videos VALUES (3, 'ameer_pyaar', 'AlanWalker', 'Music', '2017-09-04T18:10:49.445+05:30[Asia/Calcutta]', 1);