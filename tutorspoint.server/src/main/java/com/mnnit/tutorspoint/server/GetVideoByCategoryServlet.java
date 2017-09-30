package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "findVideosByCategory", urlPatterns = "/getVideosByCategory")
public class GetVideoByCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            response.getWriter()
                    .println(Globals.GSON.toJson(SQLUtils.getVideosByCategory(request.getParameter("category"))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
