package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetSubscriptionsServlet", urlPatterns = "/getSubscriptions")
public class GetSubscriptionsServlet extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getSubscriptionList()));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
