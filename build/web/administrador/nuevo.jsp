<%-- 
    Document   : añadir
    Created on : 19-07-2021, 19:18:55
    Author     : Yunnicio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir mascota</title>
    </head>
    <body>
        <h1>Administrador: Añadir mascota</h1>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio Administrador</a></li>
                <li><a href="#">Añadir mascota</a></li>
            </ul>
        </nav>
        
        <form>
            <label>
                Nombre
                <input type="text" name="nombre" placeholder="Ingrese el nombre"/>
            </label>
            <br>
            <label>
                Raza
                <input type="text" name="raza" placeholder="Ingrese la raza"/>
            </label>
            <br>
            <label>
                Edad
                <input type="number" name="edad" placeholder="Ingrese la edad"/>
            </label>
            <br>
            <label>
                Peso
                <input type="number" name="peso" placeholder="Ingrese el peso"/>
            </label>
            <br>
            <label>
                Foto
                <input type="text" name="foto" placeholder="Ingrese una foto"/>
            </label>
            <br>
            <label>
                Tipo
                <select name="tipo">
                    <option value="perro">Perro</option>
                    <option value="gato">Gato</option>
                </select>
            </label>
            <br>
            <input type="submit" value="Añadir"/>
        </form>
    </body>
</html>
