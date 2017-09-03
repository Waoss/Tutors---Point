package com.mnnit.tutorspoint.server.database;

import com.google.gson.Gson;
import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.User;

import java.sql.*;

public class SQLUtils {

    private static final Gson GSON = Globals.GSON;
    private static final Connection connection = Database.getConnection();

    private SQLUtils() {
        //no instance
    }

    public static void insertUser(User user) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserType().toString());
        preparedStatement.executeUpdate();
    }
}
