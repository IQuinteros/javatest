<%-- 
    Document   : index
    Created on : 20-07-2021, 13:59:47
    Author     : Yunnicio
--%>

<%@page import="modelos.RecetaResult"%>
<%@page import="repositorio.RecetaRepositorio"%>
<%@page import="repositorio.ClienteRepositorio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    String search = request.getParameter("search");
    List<RecetaResult> recetas = RecetaRepositorio.busquedaRecetas(search, ClienteRepositorio.getClienteSession(request));
    
    pageContext.setAttribute("recetas", recetas);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recetas</title>
    </head>
    <body>
        <h1>Administrador - Recetas</h1>
        
        <nav>
            <ul>
                <li><a href="../">Inicio Administrador</a></li>
                <li><a href="#">Recetas</a></li>
                <li><a href="../nuevo.jsp">Añadir mascota</a></li>
            </ul>
        </nav>
            
        <form action="." method="GET">
            <label>
                Buscar por objetivo o peso
                <input type="text" name="search" placeholder="Inserte el texto de búsqueda" value="<%= search != null? search : "" %>"/>
            </label>
            <input type="submit" value="Buscar"/>
        </form>
            
        <a href="nuevo.jsp">Añadir receta</a>
        
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Descripción</td>
                <td>Objetivo</td>
                <td>Peso</td>
                <td>Favoritos</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            
            <c:forEach items="${pageScope.recetas}" var="receta">
                <tr>
                    <td>${receta.getReceta().getId()}</td>
                    <td>${receta.getReceta().getNombre()}</td>
                    <td>${receta.getReceta().getDescription()}</td>
                    <td>${receta.getReceta().getObjetivo()}</td>
                    <td>${receta.getReceta().getPeso()}</td> 
                    <td>${receta.getNumFavoritos()}</td>
                    <td>
                        <form action="editar.jsp" method="GET">
                            <input type="hidden" name="id" value="${receta.getReceta().getId()}"/>
                            <input type="submit" value="Editar"/>
                        </form>
                    </td>
                    <td>
                        <form action="../../RemoveReceta" method="POST">
                            <input type="hidden" name="id" value="${receta.getReceta().getId()}"/>
                            <input type="submit" value="Eliminar"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>
