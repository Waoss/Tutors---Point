package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.todo.Todo;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertTodoServlet", urlPatterns = "/insertTodo")
public class InsertTodoServlet extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLUtils.insertTodo(new Todo(request.getParameter("student"), request.getParameter("message")));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
        response.getWriter().print(0);
    }
}
