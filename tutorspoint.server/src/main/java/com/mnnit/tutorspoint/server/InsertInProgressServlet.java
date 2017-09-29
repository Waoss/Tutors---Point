package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.InProgress;
import com.mnnit.tutorspoint.core.video.VideoCategory;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertInProgressServlet", urlPatterns = "/insertInProgress")
public class InsertInProgressServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        InProgress inProgress = new InProgress();
        inProgress.setCategory(new VideoCategory(request.getParameter("category")));
        inProgress.setStudent(request.getParameter("student"));
        try {
            SQLUtils.insertInProgress(inProgress);
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
