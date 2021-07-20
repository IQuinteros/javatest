<%-- 
    Document   : nuevo
    Created on : 20-07-2021, 14:29:37
    Author     : Yunnicio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Administrador: Nueva receta</h1>
        
        <nav>
            <ul>
                <li><a href="../index.jsp">Inicio Administrador</a></li>
                <li><a href="./">Recetas</a></li>
                <li><a href="../nuevo.jsp">Añadir mascota</a></li>
            </ul>
        </nav>
        
        <form action="../../AddReceta" method="POST">
            <label>
                Nombre
                <input type="text" name="nombre" placeholder="Ingrese el nombre" disabled/>
            </label>
            <br>
            <label>
                Descripción
                <input type="text" name="descripcion" placeholder="Ingrese la raza" required/>
            </label>
            <br>
            <label>
                Objetivo
                <select name="objetivo" required>
                    <option value="perro">Perro</option>
                    <option value="gato">Gato</option>
                </select>
            </label>
            <br>
            <label>
                Peso
                <input type="number" name="peso" placeholder="Ingrese el peso" step="0.1" required/>
            </label>
            <br>
            <input type="submit" value="Añadir"/>
        </form>
    </body>
</html>
