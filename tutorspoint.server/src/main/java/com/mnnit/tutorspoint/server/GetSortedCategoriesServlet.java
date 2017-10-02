package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetSortedCategoriesServlet", urlPatterns = "/getSortedCategories")
public class GetSortedCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getSortedCategories()));
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
