package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetSubscriptionsForTeacherServlet", urlPatterns = "/getSubscriptionsForTeacher")
public class GetSubscriptionsForTeacherServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter()
                    .print(Globals.GSON.toJson(SQLUtils.getSubscriptionsForTeacher(request.getParameter("teacher"))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
