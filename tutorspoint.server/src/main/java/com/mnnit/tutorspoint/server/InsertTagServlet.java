package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.video.Tag;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertTagServlet", urlPatterns = "/insertTag")
public class InsertTagServlet extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.insertTag(
                    new Tag(request.getParameter("name"), Integer.parseInt(request.getParameter("videoId"))));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
        response.getWriter().print("0");
    }
}
