<%-- 
    Document   : editar
    Created on : 20-07-2021, 14:29:30
    Author     : Yunnicio
--%>

<%@page import="entidades.Receta"%>
<%@page import="repositorio.RecetaRepositorio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String idString = request.getParameter("id");
    int id = Integer.parseInt(idString);
    
    Receta receta = RecetaRepositorio.encontrarReceta(id);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Administrador: Editar receta</h1>
        
        <nav>
            <ul>
                <li><a href="../index.jsp">Inicio Administrador</a></li>
                <li><a href="./">Recetas</a></li>
                <li><a href="../nuevo.jsp">Añadir mascota</a></li>
            </ul>
        </nav>
        
        <form action="../../EditReceta" method="POST">
            <input type="hidden" name="id" value="<%= receta.getId() %>"/>
            <label>
                Nombre
                <input type="text" name="nombre" placeholder="Ingrese el nombre" value="<%= receta.getNombre() %>" disabled/>
            </label>
            <br>
            <label>
                Descripción
                <input type="text" name="descripcion" placeholder="Ingrese la raza" value="<%= receta.getDescription()%>" required/>
            </label>
            <br>
            <label>
                Objetivo
                <select name="objetivo" required>
                    <option value="perro" <%= receta.getObjetivo().toLowerCase().equals("perro")? "selected" : "" %>>Perro</option>
                    <option value="gato" <%= receta.getObjetivo().toLowerCase().equals("gato")? "selected" : "" %>>Gato</option>
                </select>
            </label>
            <br>
            <label>
                Peso
                <input type="number" name="peso" placeholder="Ingrese el peso" value="<%= receta.getPeso() %>" step="0.1" required/>
            </label>
            <br>
            <input type="submit" value="Editar"/>
        </form>
    </body>
</html>
