package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetCategoriesByParentServlet", urlPatterns = "/getCategoriesByParent")
public class GetCategoriesByParentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<String> parent = SQLUtils.getCategoriesByParent(request.getParameter("parent"));
            response.getWriter()
                    .print(Globals.GSON.toJson(parent));

        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
