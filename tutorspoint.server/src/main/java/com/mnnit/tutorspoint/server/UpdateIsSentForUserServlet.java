package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateIsSentForUserServlet", urlPatterns = "/updateIsSentForUser")
public class UpdateIsSentForUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.updateIsSentForUser(request.getParameter("user"));
            response.getWriter().print(0);
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
