<%-- 
    Document   : realindex
    Created on : 17-07-2021, 18:22:40
    Author     : Yunnicio
--%>

<%@page import="entidades.Mascota"%>
<%@page import="java.util.List"%>
<%@page import="repositorio.AdopcionRepositorio"%>
<%@page import="entidades.Cliente"%>
<%@page import="modelos.Carro"%>
<%@page import="repositorio.ClienteRepositorio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Página para adoptar mascotas</h1>
        
        <c:if test="${sessionScope.result != null && !sessionScope.result.isSuccess()}">
            <hr>
            <div>
                <p>${sessionScope.result.getMessage()}</p>
            </div>
            <hr>
            <c:remove var="result" scope="session"/>
        </c:if>
        
        <nav>
            <ul>
                <li><a href="#">Inicio</a></li>
                <li><a href="mascotas/">Mascotas</a></li>
                <li><a href="recetas/">Recetas</a></li>
                <li><a href="administrador/">Administrador</a></li>
                <li><a href="login.jsp"><%= ClienteRepositorio.getClienteSession(request) != null? "Cerrar sesión" : "Iniciar sesión" %></a></li>
                <li><a href="carro.jsp">Carro de adopción (<%= Carro.getCarro().getCount(request) %>)</a></li>
            </ul>
        </nav>
            
        <% 
            Cliente cliente = ClienteRepositorio.getClienteSession(request);
            if(cliente != null){ 
        %>
            <h1>Bienvenido <%= cliente.getNombre() %></h1>
            <hr>
            
            <h2>Tus mascotas</h2>
            <%
            List<Mascota> mascotas = AdopcionRepositorio.getMascotasOfCliente(cliente).getResult();
            pageContext.setAttribute("mascotas", mascotas);
            %>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Nombre</td>
                    <td>Raza</td>
                    <td>Edad</td>
                    <td>Peso</td>
                    <td>Foto</td>
                    <td>Tipo</td>
                    <td>Eliminar adopción</td>
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
                            <form action="RemoveAdopcion" method="POST">
                                <input type="hidden" name="id" value="${mascota.getId()}"/>
                                <input type="submit" value="Eliminar adopción"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        <% } %>
       
    </body>
</html>
