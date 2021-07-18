<%-- 
    Document   : index
    Created on : 17-07-2021, 18:25:38
    Author     : Yunnicio
--%>

<%@page import="modelos.Carro"%>
<%@page import="entidades.Cliente"%>
<%@page import="repositorio.ClienteRepositorio"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Mascota"%>
<%@page import="repositorio.MascotaRepositorio"%>
<%@page import="controladores.MascotaJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    List<Mascota> mascotas = MascotaRepositorio.obtenerMascotasDisponibles();
    pageContext.setAttribute("mascotas", mascotas);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascotas</title>
    </head>
    <body>
        <h1>Estas son las mascotas:</h1>     
        
        <nav>
            <ul>
                <li><a href="../index.jsp">Inicio</a></li>
                <li><a href="#">Mascotas</a></li>
                <li><a href="../index.jsp">Recetas</a></li>
                <li><a href="../index.jsp">Administrador</a></li>
                <li><a href="../login.jsp"><%= ClienteRepositorio.getClienteSession(request) != null? "Cerrar sesión" : "Iniciar sesión" %></a></li>
                <li><a href="../carro.jsp">Carro de adopción (<%= Carro.getCarro().getCount() %>)</a></li>
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
                    <%
                    // Existe en carro?
                    boolean exists = false;
                    try{
                        Mascota mascota = (Mascota)pageContext.getAttribute("mascota");
                        exists = Carro.getCarro().existsMascota(mascota);                      
                    } catch(Exception e){}
                    %>
                    <form action="<%= exists? "../RemoveCarro" : "../AddCarro"%>" method="POST">
                        <input type="hidden" name="id" value="${mascota.getId()}"/>
                        <td><input type="submit" value="<%= exists? "Eliminar de carro" : "Añadir al carro"%>"/></td>
                    </form>       
                </tr>
            </c:forEach>
            
        </table>
        
    </body>
</html>
