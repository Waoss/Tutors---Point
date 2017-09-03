package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Video;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1000000000)// 100 mb
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        Part metadataPart = request.getPart("metadata");
        Part binaryFilePart = request.getPart("binaryFile");
        response.getWriter().print("0");
        System.out.println(IOUtils.toString(new InputStreamReader(metadataPart.getInputStream())));
    }

}
