package com.mnnit.tutorspoint.server.database;

import com.google.gson.Gson;
import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.User;
import com.mnnit.tutorspoint.core.video.Video;

import java.sql.*;

public class SQLUtils {

    private static final Gson GSON = Globals.GSON;
    private static final Connection connection = Database.getConnection();

    private SQLUtils() {
        //no instance
    }

    public static void insertUser(final User user) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserType().toString());
        preparedStatement.executeUpdate();
    }

    public static void insertVideo(final Video video) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_VIDEO);
        preparedStatement.setString(1, video.getName());
        preparedStatement.setString(2, video.getUsername());
        preparedStatement.setString(3, video.getCategory().toString());
        preparedStatement.setString(4, video.getDateTime());
        preparedStatement.setInt(5, video.getComments().size());
        preparedStatement.executeUpdate();
    }

    public static int getVideoidByVideo(final Video video) throws SQLException {
        
    }
}
