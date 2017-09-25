package com.mnnit.tutorspoint.server.database;

import com.google.gson.Gson;
import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.User;
import com.mnnit.tutorspoint.core.video.*;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import static com.mnnit.tutorspoint.server.database.SQLConstants.*;

public class SQLUtils {

    private static final Gson GSON = Globals.GSON;
    private static final Connection connection = Database.getConnection();

    private SQLUtils() {
        //no instance
    }

    public static void insertUser(final User user) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserType().toString());
        preparedStatement.executeUpdate();
    }

    public static void insertVideo(final Video video) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VIDEO);
        preparedStatement.setString(1, video.getName());
        preparedStatement.setString(2, video.getUsername());
        preparedStatement.setString(3, video.getCategory());
        preparedStatement.setString(4, video.getDateTime());
        preparedStatement.setInt(5, video.getComments().size());
        preparedStatement.executeUpdate();
        video.setVideoId(getVideoIdByName(video.getName()));
    }

    public static int getVideoIdByName(final String videoName) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_VIDEO_ID_BY_VIDEO_NAME);
        preparedStatement.setString(1, videoName);
        final ResultSet resultSet = preparedStatement.executeQuery();
        int videoid = -1;
        while (resultSet.next()) {
            videoid = resultSet.getInt("videoid");
        }
        return videoid;
    }

    public static List<Video> getVideoList() throws SQLException {
        Vector<Video> videos = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_VIDEOS_LIST);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Video video;
        while (resultSet.next()) {
            video = getVideoById(resultSet.getInt("videoid"));
            videos.add(video);
        }
        return videos;
    }

    public static Video getVideoById(final int id) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_VIDEO_BY_ID);
        preparedStatement.setInt(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Video result = new Video();
        result.setVideoId(id);
        while (resultSet.next()) {
            result.setName(resultSet.getString("name"));
            result.setUsername(resultSet.getString("uploader"));
            result.setCategory(resultSet.getString("category"));
            result.setDateTime(resultSet.getString("uploadTime"));
            result.setLikes(getLikesByVideoId(id));
            result.setComments(getCommentsByVideoId(id));
        }
        return result;
    }

    public static List<Like> getLikesByVideoId(final int videoId) throws SQLException {
        Vector<Like> likes = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_LIKES_BY_VIDEO_ID);
        preparedStatement.setInt(1, videoId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Like like = new Like();
        while (resultSet.next()) {
            like.setVideoId(videoId);
            like.setDateTime(resultSet.getString("dateTime"));
            likes.add(like);
            like = new Like();
        }
        return likes;
    }

    public static List<Comment> getCommentsByVideoId(final int id) throws SQLException {
        Vector<Comment> comments = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS_BY_VIDEO_ID);
        preparedStatement.setInt(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Comment comment = new Comment(null, null);
        while (resultSet.next()) {
            comment.setVideoId(id);
            comment.setUsername(resultSet.getString("commenter"));
            comment.setDateTime(resultSet.getString("dateTime"));
            comment.setMessage(resultSet.getString("message"));
            comments.add(comment);
            comment = new Comment(null, null);
        }
        return comments;
    }

    public static void insertComment(final Comment comment) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT);
        preparedStatement.setInt(1, comment.getVideoId());
        preparedStatement.setString(2, comment.getMessage());
        preparedStatement.setString(3, comment.getUsername());
        preparedStatement.setString(4, comment.getDateTime());
        preparedStatement.executeUpdate();
    }

    public static void insertLike(final Like like) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIKE);
        preparedStatement.setInt(1, like.getVideoId());
        preparedStatement.setString(2, like.getDateTime());
        preparedStatement.executeUpdate();
    }

    public static List<Video> getVideosByCategory(final String category) throws SQLException {
        Vector<Video> result = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_VIDEOS_BY_CATEGORY);
        preparedStatement.setString(1, category);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Video video = new Video();
            video.setVideoId(resultSet.getInt("videoId"));
            video.setUsername(resultSet.getString("uploader"));
            video.setName(resultSet.getString("name"));
            video.setDateTime(resultSet.getString("uploadTime"));
            video.setLikes(getLikesByVideoId(video.getVideoId()));
            video.setCategory(category);
            video.setComments(getCommentsByVideoId(video.getVideoId()));
            result.add(video);
        }
        return result;
    }

    public static List<String> getCategoriesByParent(final String parent) throws SQLException {
        Vector<String> result = new Vector<>();
        final PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORIES_BY_PARENT);
        preparedStatement.setString(1, parent);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(resultSet.getString("name"));
        }
        return result;
    }
}
