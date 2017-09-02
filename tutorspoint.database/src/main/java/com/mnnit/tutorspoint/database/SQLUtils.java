package com.mnnit.tutorspoint.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.core.video.*;

import java.sql.*;
import java.time.ZonedDateTime;

import static com.mnnit.tutorspoint.database.Database.getConnection;

public class SQLUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private SQLUtils() {
        //no instance
    }

    public static final void insertUser(User user) throws SQLException {
        final PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserType().toString());
        preparedStatement.executeUpdate();
    }

    public static final void insertVideo(Video video) throws SQLException {
        final PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.INSERT_VIDEO);
        preparedStatement.setString(1, video.getName());
        preparedStatement.setString(2, video.getUser().getUsername());
        preparedStatement.setString(3, video.getVideoCategory().getName());
        preparedStatement.setString(4, GSON.toJson(ZonedDateTime.now()));
        preparedStatement.setInt(5, video.getLikes().size());
        preparedStatement.executeUpdate();
    }

    public static final Video getVideoById(int id) throws SQLException {
        final PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.GET_VIDEO_BY_ID);
        preparedStatement.setInt(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        VideoBuilder videoBuilder = new VideoBuilder();
        while (resultSet.next()) {
            videoBuilder.setName(resultSet.getString("name"));
            videoBuilder.setVideoCategory(new VideoCategory(resultSet.getString("category"), null));
        }
        return videoBuilder.createVideo();
    }

    public static final int getVideoIdByVideoName(String name) throws SQLException {
        final PreparedStatement preparedStatement = getConnection()
                .prepareStatement(SQLConstants.GET_VIDEO_ID_BY_VIDEO_NAME);
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = resultSet.getInt("videoid");
        }
        return result;
    }

    public static final User getUserByUsername(String username) throws SQLException {
        final PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.GET_USER_BY_USERNAME);
        preparedStatement.setString(1, username);
        final ResultSet resultSet = preparedStatement.executeQuery();
        UserBuilder userBuilder = new UserBuilder();
        while (resultSet.next()) {
            userBuilder
                    .setUsername(resultSet.getString("username"))
                    .setPassword(resultSet.getString("password"))
                    .setUserType(UserType.valueOf(resultSet.getString("usertype")));
        }
        return userBuilder.createUser();
    }
}
