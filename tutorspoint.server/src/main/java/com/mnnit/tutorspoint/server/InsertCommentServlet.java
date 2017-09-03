package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.video.Comment;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertCommentServlet", urlPatterns = "/insertComment")
public class InsertCommentServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        Comment comment = new Comment(request.getParameter("username"), request.getParameter("message"));
        comment.setDateTime(request.getParameter("dateTime"));
        comment.setVideoId(Integer.parseInt(request.getParameter("videoId")));
        try {
            SQLUtils.insertComment(comment);
            response.getWriter().print(0);
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }
}
