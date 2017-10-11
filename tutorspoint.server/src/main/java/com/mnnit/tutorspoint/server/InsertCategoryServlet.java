package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "InsertCategoryServlet", urlPatterns = "/insertCategory")
public class InsertCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.insertCategory(request.getParameter("name"), request.getParameter("parent"));
            response.getWriter().print(0);
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
