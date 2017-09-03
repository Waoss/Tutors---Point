package com.mnnit.tutorspoint.server.database;

public class SQLConstants {
    public static final String INSERT_USER = "INSERT INTO users (username, password, usertype) VALUES (?, ?, ?);";
    public static final String INSERT_VIDEO = "INSERT INTO videos (name, uploader, category, uploadTime, likes) VALUES (?,?,?,?,?);";
    public static final String GET_VIDEO_BY_ID = "SELECT * FROM videos WHERE videoid = ?;";
    public static final String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?;";
    public static final String GET_VIDEO_ID_BY_VIDEO_NAME =
            "SELECT videoid\n" +
                    "FROM videos\n" +
                    "WHERE name = ?;";
}
