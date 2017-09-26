package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetUsersServlet", urlPatterns = "/getUsers")
public class GetUsersServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getUsersList()));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
