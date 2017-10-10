package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteCommentServlet", urlPatterns = "/deleteComment")
public class DeleteCommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.deleteComment(Integer.parseInt(request.getParameter("videoId")), request.getParameter("username"));
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
