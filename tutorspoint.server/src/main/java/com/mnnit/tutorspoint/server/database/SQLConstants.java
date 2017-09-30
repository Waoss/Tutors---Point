package com.mnnit.tutorspoint.server.database;

public class SQLConstants {

    public static final String INSERT_USER = "INSERT INTO Users (username, password, userype) VALUES (?, ?, ?)";
    public static final String INSERT_VIDEO = "INSERT INTO Videos (name, uploader, category, uploadTime, likes, format) VALUES (?,?,?,?,?,?)";
    public static final String GET_VIDEO_BY_ID = "SELECT * FROM Videos WHERE videoId = ?";
    public static final String GET_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    public static final String GET_VIDEO_ID_BY_VIDEO_NAME = "SELECT videoId\n" + "FROM Videos\n" + "WHERE name = ?";
    public static final String GET_VIDEOS_LIST = "SELECT *\n" + "FROM Videos";
    public static final String INSERT_COMMENT = "INSERT INTO Comments (videoId, message, commenter, dateTime) VALUES (?, ?, ?, ?)";
    public static final String INSERT_LIKE = "INSERT INTO Likes (videoId, dateTime) VALUES (?, ?)";
    public static final String GET_LIKES_BY_VIDEO_ID = "SELECT *\n" + "FROM Likes\n" + "WHERE videoId = ?";
    public static final String GET_COMMENTS_BY_VIDEO_ID = "SELECT *\n" + "FROM Comments\n" + "WHERE videoId = ?";
    public static final String GET_VIDEOS_BY_CATEGORY = "SELECT * FROM Videos WHERE category=?";
    public static final String GET_CATEGORIES_BY_PARENT = "SELECT *\nFROM Categories\nWHERE parent = ?";
    public static final String GET_TAGS_BY_VIDEO_ID = "SELECT * FROM Tags WHERE videoId=?";
    public static final String INSERT_TAG = "INSERT INTO Tags (name, videoId) VALUES (?, ?)";
    public static final String INSERT_SUBSCRIPTION = "INSERT INTO Subscriptions (subscriber, subscribedTo) VALUES (?, ?)";
}
