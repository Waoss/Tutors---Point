package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteVideoServlet", urlPatterns = "/deleteVideo")
public class DeleteVideoServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.deleteVideo(Integer.parseInt(request.getParameter("videoId")),
                    getServletContext().getRealPath(""));

        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
        response.getWriter().print("0");
    }
}
