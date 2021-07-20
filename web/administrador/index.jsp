<%-- 
    Document   : index
    Created on : 19-07-2021, 18:55:13
    Author     : Yunnicio
--%>

<%@page import="repositorio.MascotaRepositorio"%>
<%@page import="entidades.Mascota"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    String search = request.getParameter("search");
    List<Mascota> mascotas = MascotaRepositorio.busquedaMascotasDisponibles(search);
    
    pageContext.setAttribute("mascotas", mascotas);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
    </head>
    <body>
        <h1>Administrador</h1>
        
        <nav>
            <ul>
                <li><a href="#">Inicio Administrador</a></li>
                <li><a href="nuevo.jsp">Añadir mascota</a></li>
            </ul>
        </nav>
            
        <form action="." method="GET">
            <label>
                Buscar por raza o tipo
                <input type="text" name="search" placeholder="Inserte el texto de búsqueda" value="<%= search != null? search : "" %>"/>
            </label>
            <input type="submit" value="Buscar"/>
        </form>
        
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Raza</td>
                <td>Edad</td>
                <td>Peso</td>
                <td>Foto</td>
                <td>Tipo</td>
                <td>Editar</td>
                <td>Eliminar</td>
            </tr>
            
            <c:forEach items="${pageScope.mascotas}" var="mascota">
                <tr>
                    <td>${mascota.getId()}</td>
                    <td>${mascota.getNombre()}</td>
                    <td>${mascota.getRaza()}</td>
                    <td>${mascota.getEdad()}</td>
                    <td>${mascota.getPeso()}</td>
                    <td><img style="width: 200px" src="${mascota.getFoto()}" alt="mascota image"/></td>
                    <td>${mascota.getTipo()}</td>  
                    <td>
                        <form action="editar.jsp" method="GET">
                            <input type="hidden" name="id" value="${mascota.getId()}"/>
                            <input type="submit" value="Editar"/>
                        </form>
                    </td>
                    <td>
                        <form action="../RemoveMascota" method="POST">
                            <input type="hidden" name="id" value="${mascota.getId()}"/>
                            <input type="submit" value="Eliminar"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>
