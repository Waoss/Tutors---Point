<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.mnnit.tutorspoint.core.*" %>
<%
    response.setContentType("application/json");
    final User user = new UserBuilder()
            .setUsername("foo")
            .setPassword("bar")
            .setUserType(UserType.TEACHER)
            .createUser();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(User.class, new UserTypeAdapter())
            .create();
    out.println(gson.toJson(user));
%>
