package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetTagsByVideoIdServlet", urlPatterns = "/getTagsByVideoId")
public class GetTagsByVideoIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getTagsByVideoId(
                    Integer.parseInt(request.getParameter("videoId")))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

    }
}
