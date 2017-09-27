package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertSubscriptionServlet", urlPatterns = "/insertSubscription")
public class InsertSubscriptionServlet extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.insertSubscription(
                    new Subscription(request.getParameter("subscriber"), request.getParameter("subscribedTo")));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
        response.getWriter().print(0);
    }
}
