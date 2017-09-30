PRAGMA foreign_keys = OFF;
BEGIN TRANSACTION;
CREATE TABLE Categories
(
  name   VARCHAR NOT NULL,
  parent VARCHAR
);
CREATE TABLE Comments
(
  videoId   INTEGER NOT NULL,
  message   VARCHAR NOT NULL,
  commenter VARCHAR NOT NULL,
  dateTime  VARCHAR NOT NULL
);
CREATE TABLE InProgress
(
  student  VARCHAR NOT NULL,
  category VARCHAR NOT NULL
);
CREATE TABLE Likes
(
  username VARCHAR,
  videoId  INTEGER NOT NULL,
  dateTime VARCHAR NOT NULL
);
CREATE TABLE Notifications
(
  subscriptionId INTEGER,
  message        VARCHAR,
  isSent         BOOLEAN DEFAULT FALSE,
  CONSTRAINT Notifications_Subscriptions_subscriptionId_fk FOREIGN KEY (subscriptionId) REFERENCES Subscriptions (subscriptionId)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE TABLE Subscriptions
(
  subscriber     VARCHAR                           NOT NULL,
  subscribedTo   VARCHAR                           NOT NULL,
  subscriptionId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
);
CREATE TABLE Tags
(
  name    VARCHAR NOT NULL,
  videoId INTEGER NOT NULL
);
CREATE TABLE Todos
(
  student VARCHAR NOT NULL,
  message VARCHAR NOT NULL
);
CREATE TABLE Users
(
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  userype  VARCHAR NOT NULL
);
INSERT INTO Users VALUES ('foo',
                          '0BFBCA122159CDA9F4EAF2BF70ADB397D0BC496B48EFDA2872A651AECC43D1172872F021122B8B4F1CC1EBABD204770C2CD9811C1E4993F9FF2D6B897DC323BF',
                          'STUDENT');
INSERT INTO Users VALUES ('foo',
                          '0BFBCA122159CDA9F4EAF2BF70ADB397D0BC496B48EFDA2872A651AECC43D1172872F021122B8B4F1CC1EBABD204770C2CD9811C1E4993F9FF2D6B897DC323BF',
                          'STUDENT');
CREATE TABLE Videos
(
  videoId    INTEGER PRIMARY KEY NOT NULL,
  name       VARCHAR             NOT NULL,
  uploader   VARCHAR             NOT NULL,
  category   VARCHAR             NOT NULL,
  uploadTime VARCHAR             NOT NULL,
  likes      INTEGER             NOT NULL,
  format     VARCHAR DEFAULT "mp4"
);
DELETE FROM sqlite_sequence;
CREATE INDEX Notifications_subscriptionId_index
  ON Notifications (subscriptionId);
COMMIT;
