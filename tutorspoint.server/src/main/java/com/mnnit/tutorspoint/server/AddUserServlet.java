package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.UserBuilder;
import com.mnnit.tutorspoint.core.UserType;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddUserServlet", urlPatterns = "/adduser")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        try {
            SQLUtils.insertUser(new UserBuilder().setUsername(request.getParameter("username")).setPassword(
                    request.getParameter("password")).setUserType(
                    UserType.valueOf(request.getParameter("usertype"))).createUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
