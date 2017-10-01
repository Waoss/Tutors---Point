package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.video.Like;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "InsertLikeServlet", urlPatterns = "/insertLike")
public class InsertLikeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(InsertLikeServlet.class.getName());

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final Like like = new Like();
        like.setUsername(request.getParameter("username"));
        like.setVideoId(Integer.parseInt(request.getParameter("videoId")));
        like.setDateTime(request.getParameter("dateTime"));
        try {
            SQLUtils.insertLike(like);
            response.getWriter().print(0);
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
