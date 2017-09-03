package com.mnnit.tutorspoint.database;

import org.junit.Test;

public class DatabaseTest {
    @Test
    public void testConnection() throws Exception {
        System.out.println(Database.getConnection());
    }
}