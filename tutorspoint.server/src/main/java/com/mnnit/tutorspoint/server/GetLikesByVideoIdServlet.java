package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import static com.mnnit.tutorspoint.core.Globals.GSON;

@WebServlet(name = "GetLikesByVideoIdServlet", urlPatterns = "/getLikesByVideoId")
public class GetLikesByVideoIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            response.getWriter().print(
                    GSON.toJson(SQLUtils.getLikesByVideoId(Integer.parseInt(request.getParameter("videoId")))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }
}
