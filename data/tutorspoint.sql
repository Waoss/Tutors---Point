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