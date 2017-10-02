package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.todo.Todo;
import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.server.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "GetVideosByUserTodosServlet", urlPatterns = "/getVideosByUserTodos")
public class GetVideosByUserTodosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            final List<Todo> todos = SQLUtils.getTodosByUser(request.getParameter("user"));
            final Vector<Video> result = new Vector<>();
            todos.forEach(todo -> {
                try {
                    result.add(getVideoByTodo(todo));
                } catch (SQLException e) {
                    try {
                        e.printStackTrace(response.getWriter());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            response.getWriter().print(Globals.GSON.toJson(result));
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }

    private Video getVideoByTodo(final Todo todo) throws SQLException {
        final StringTokenizer stringTokenizer = new StringTokenizer(todo.getMessage(), "-");
        String videoId = null;
        while (stringTokenizer.hasMoreTokens()) {
            videoId = stringTokenizer.nextToken();
        }

        return SQLUtils.getVideoById(Integer.parseInt(videoId));
    }
}
