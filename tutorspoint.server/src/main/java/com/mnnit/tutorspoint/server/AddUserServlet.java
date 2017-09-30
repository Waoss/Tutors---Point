package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AddUserServlet", urlPatterns = "/adduser")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        try {
            final User user = new UserBuilder().setUsername(request.getParameter("username")).setPassword(
                    request.getParameter("password")).setUserType(
                    UserType.valueOf(request.getParameter("usertype"))).createUser();
            final String insecurePassword = user.getPassword();
            user.setPassword(
                    arrayToString(MessageDigest.getInstance("SHA-512")
                            .digest((insecurePassword + user.getUsername() + user.getUserType().toString())
                                    .getBytes("utf-16"))));
            SQLUtils.insertUser(user);
            response.getWriter().print(0);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    private static String arrayToString(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
