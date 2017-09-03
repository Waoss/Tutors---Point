package com.mnnit.tutorspoint.server.database;

import com.google.gson.Gson;
import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.User;
import com.mnnit.tutorspoint.core.video.*;
import com.mnnit.tutorspoint.server.OverallContent;

import java.sql.*;
import java.util.List;
import java.util.Vector;

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
        video.setId(getVideoidByVideo(video.getName()));
        OverallContent.getInstance().getVideoList().add(video);
    }

    public static int getVideoidByVideo(final String videoName) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(
                SQLConstants.GET_VIDEO_ID_BY_VIDEO_NAME);
        preparedStatement.setString(1, videoName);
        final ResultSet resultSet = preparedStatement.executeQuery();
        int videoid = - 1;
        while (resultSet.next()) {
            videoid = resultSet.getInt("videoid");
        }
        return videoid;
    }

    public static List<Video> getVideoList() throws SQLException {
        Vector<Video> videos = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.GET_VIDEOS_LIST);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Video video;
        while (resultSet.next()) {
            video = getVideoById(resultSet.getInt("videoid"));
            videos.add(video);
        }
        return videos;
    }

    public static Video getVideoById(final int id) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.GET_VIDEO_BY_ID);
        preparedStatement.setInt(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Video result = new Video();
        result.setId(id);
        while (resultSet.next()) {
            result.setName(resultSet.getString("name"));
            result.setUsername(resultSet.getString("uploader"));
            result.setCategory(new VideoCategory(resultSet.getString("category")));
            result.setDateTime(resultSet.getString("uploadTime"));
            //TODO: Implement likes and comments
            /*result.setLikes();
            result.setComments();*/
        }
        return result;
    }

    public static void insertComment(final Comment comment) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_COMMENT);
        preparedStatement.setInt(1, comment.getVideoId());
        preparedStatement.setString(2, comment.getMessage());
        preparedStatement.setString(3, comment.getUsername());
        preparedStatement.setString(4, comment.getDateTime());
        preparedStatement.executeUpdate();
    }
}
