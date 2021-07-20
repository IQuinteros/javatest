<%-- 
    Document   : index
    Created on : 20-07-2021, 12:44:27
    Author     : Yunnicio
--%>

<%@page import="modelos.RecetaResult"%>
<%@page import="repositorio.RecetaRepositorio"%>
<%@page import="entidades.Receta"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Carro"%>
<%@page import="repositorio.ClienteRepositorio"%>
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
        <h1>Recetas</h1>     
        
        <nav>
            <ul>
                <li><a href="../index.jsp">Inicio</a></li>
                <li><a href="../mascotas/">Mascotas</a></li>
                <li><a href="#">Recetas</a></li>
                <li><a href="../administrador/">Administrador</a></li>
                <li><a href="../login.jsp"><%= ClienteRepositorio.getClienteSession(request) != null? "Cerrar sesión" : "Iniciar sesión" %></a></li>
                <li><a href="../carro.jsp">Carro de adopción (<%= Carro.getCarro().getCount() %>)</a></li>
            </ul>
        </nav>
            
        <form action="." method="GET">
            <label>
                Buscar por objetivo o por peso
                <input type="text" name="search" placeholder="Inserte el texto de búsqueda" value="<%= search %>"/>
            </label>
            <input type="submit" value="Buscar"/>
        </form>
            
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Descripción</td>
                <td>Objetivo</td>
                <td>Peso</td>
                <td>Favoritos</td>
                <td>Añadir favorito</td>
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
                        <% if(ClienteRepositorio.getClienteSession(request) != null){ %>
                            <form action="<c:choose><c:when test="${receta.isFavoritoByCliente()}">../RemoveFavorito</c:when><c:otherwise>../AddFavorito</c:otherwise></c:choose>" method="POST">
                                <input type="hidden" name="id" value="${receta.getReceta().getId()}"/>
                                <input type="submit" value="<c:choose><c:when test="${receta.isFavoritoByCliente()}">Eliminar favorito</c:when><c:otherwise>Añadir favorito</c:otherwise></c:choose>"/>
                            </form>
                        <% } else { %>
                            Necesita iniciar sesión para marcar como favorito
                        <% } %>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>
