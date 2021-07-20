<%-- 
    Document   : editar
    Created on : 19-07-2021, 19:10:29
    Author     : Yunnicio
--%>

<%@page import="entidades.Mascota"%>
<%@page import="repositorio.MascotaRepositorio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    String idString = request.getParameter("id");
    int id = Integer.parseInt(idString);
    
    Mascota mascota = MascotaRepositorio.encontrarMascota(id);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar mascota</title>
    </head>
    <body>
        <h1>Administrador: Editar mascota</h1>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio Administrador</a></li>
                <li><a href="recetas/">Recetas</a></li>
                <li><a href="nuevo.jsp">Añadir mascota</a></li>
            </ul>
        </nav>
        
        <form action="../EditMascota" method="POST">
            <input type="hidden" name="id" value="<%= mascota.getId() %>"/>
            <label>
                Nombre
                <input type="text" name="nombre" placeholder="Ingrese el nombre" value="<%= mascota.getNombre() %>" disabled/>
            </label>
            <br>
            <label>
                Raza
                <input type="text" name="raza" placeholder="Ingrese la raza" value="<%= mascota.getRaza() %>" required/>
            </label>
            <br>
            <label>
                Edad
                <input type="number" name="edad" placeholder="Ingrese la edad" value="<%= mascota.getEdad() %>" required/>
            </label>
            <br>
            <label>
                Peso
                <input type="number" name="peso" placeholder="Ingrese el peso" value="<%= mascota.getPeso() %>" step="0.1" required/>
            </label>
            <br>
            <label>
                Foto
                <input type="text" name="foto" placeholder="Ingrese una foto" value="<%= mascota.getFoto() %>" required/>
            </label>
            <br>
            <img src="<%= mascota.getFoto() %>" style="width: 200px; height: 100px; object-fit: cover"/>
            <br>
            <label>
                Tipo
                <select name="tipo" required>
                    <option value="perro" <%= mascota.getTipo().toLowerCase().equals("perro")? "selected" : "" %>>Perro</option>
                    <option value="gato" <%= mascota.getTipo().toLowerCase().equals("gato")? "selected" : "" %>>Gato</option>
                </select>
            </label>
            <br>
            <input type="submit" value="Editar"/>
        </form>
    </body>
</html>
