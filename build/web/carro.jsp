<%-- 
    Document   : carro
    Created on : 17-07-2021, 22:11:49
    Author     : Yunnicio
--%>

<%@page import="entidades.Cliente"%>
<%@page import="entidades.Mascota"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Carro"%>
<%@page import="repositorio.ClienteRepositorio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    List<Mascota> mascotas = Carro.getCarro().getMascotas();
    System.out.println("MASCOTAS: " + mascotas);
    pageContext.setAttribute("mascotas", mascotas);
    
    Cliente cliente = ClienteRepositorio.getClienteSession(request);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carro</title>
    </head>
    <body>
        <h1>Revisar carro</h1>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio</a></li>
                <li><a href="mascotas/">Mascotas</a></li>
                <li><a href="index.jsp">Recetas</a></li>
                <li><a href="index.jsp">Administrador</a></li>
                <li><a href="login.jsp"><%= cliente != null? "Cerrar sesión" : "Iniciar sesión" %></a></li>
                <li><a href="#">Carro de adopción (<%= Carro.getCarro().getCount() %>)</a></li>
            </ul>
        </nav>
            
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Raza</td>
                <td>Edad</td>
                <td>Peso</td>
                <td>Foto</td>
                <td>Tipo</td>
                <td>Añadir al carro</td>
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
                    <form action="RemoveCarro" method="POST">
                        <input type="hidden" name="id" value="${mascota.getId()}"/>
                        <td><input type="submit" value="Eliminar de carro"/></td>
                    </form>       
                </tr>
            </c:forEach>
            
        </table>
            
        <hr>
           
        <% if(cliente != null){ %>
            <a href="Checkout">Adoptar</a>
        <% } else { %>
            <p>Debe iniciar sesión para finalizar la adopción</p>
        <% } %>
    </body>
</html>
