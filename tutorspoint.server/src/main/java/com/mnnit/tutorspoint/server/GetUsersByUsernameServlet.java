package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetUsersByUsernameServlet", urlPatterns = "/getLoggedUsers")
public class GetUsersByUsernameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter()
                    .print(Globals.GSON.toJson(SQLUtils.getUsersByUsername(request.getParameter("username"))));
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
