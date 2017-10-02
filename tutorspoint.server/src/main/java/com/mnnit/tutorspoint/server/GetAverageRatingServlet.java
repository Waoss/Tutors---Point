package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetAverageRatingServlet", urlPatterns = "/getAverageRating")
public class GetAverageRatingServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().print(SQLUtils.getAverageRating());
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
