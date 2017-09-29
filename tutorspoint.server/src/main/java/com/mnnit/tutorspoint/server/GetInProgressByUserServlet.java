package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Rohan on 29-09-2017.
 */
@WebServlet(name = "GetInProgressByUserServlet", urlPatterns = "/getInProgressByUser")
public class GetInProgressByUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getInProgressByUser(request.getParameter("user"))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
