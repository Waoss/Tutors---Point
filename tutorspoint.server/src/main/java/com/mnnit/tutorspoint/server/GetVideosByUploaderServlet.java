package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetVideosByUploaderServlet", urlPatterns = "/getVideosByUploader")
public class GetVideosByUploaderServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter()
                    .print(Globals.GSON.toJson(SQLUtils.getVideosByUploader(request.getParameter("uploader"))));
        } catch (Throwable throwable) {
            throwable.printStackTrace(response.getWriter());
        }
    }
}
