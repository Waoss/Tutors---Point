package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Tag;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetVideosByTagServlet", urlPatterns = "/getVideosByTag")
public class GetVideosByTagServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().print(Globals.GSON.toJson(SQLUtils.getVideosByTag(new Tag(request.getParameter("name"),
                    -1))));
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
