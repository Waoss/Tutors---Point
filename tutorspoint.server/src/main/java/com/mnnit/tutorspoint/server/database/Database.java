package com.mnnit.tutorspoint.server.database;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static Connection connection;

    static {
        try {
            final DataSource dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/derbyEmbedded");
            connection = dataSource.getConnection();
            connection.createStatement().execute("SET SCHEMA MAIN");
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
