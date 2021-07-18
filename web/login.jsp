<%-- 
    Document   : login
    Created on : 17-07-2021, 20:32:16
    Author     : Yunnicio
--%>

<%@page import="entidades.Cliente"%>
<%@page import="repositorio.ClienteRepositorio"%>
<%
Cliente cliente = ClienteRepositorio.getClienteSession(request);
if(cliente != null){
    ClienteRepositorio.logout(request);
    response.sendRedirect("index.jsp");
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Iniciar sesión</h1>
        
        <nav>
            <ul>
                <li><a href="#">Inicio</a></li>
                <li><a href="mascotas/">Mascotas</a></li>
                <li><a href="index.jsp">Recetas</a></li>
                <li><a href="index.jsp">Administrador</a></li>
                <li><a href="login.jsp">Iniciar sesión</a></li>
            </ul>
        </nav>
        
        <form action="TryLogin" method="POST">
            <label>
                Email
                <input type="email" name="email"/>
            </label>
            <label>
                Contraseña
                <input type="password" name="pass"/>
            </label>
            <input type="submit"/>
        </form>
    </body>
</html>
