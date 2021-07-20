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
        
        <form action="../AddMascota" method="POST">
            <label>
                Nombre
                <input type="text" name="nombre" placeholder="Ingrese el nombre" required/>
            </label>
            <br>
            <label>
                Raza
                <input type="text" name="raza" placeholder="Ingrese la raza" required/>
            </label>
            <br>
            <label>
                Edad
                <input type="number" name="edad" placeholder="Ingrese la edad" required/>
            </label>
            <br>
            <label>
                Peso
                <input type="number" name="peso" placeholder="Ingrese el peso" required/>
            </label>
            <br>
            <label>
                Foto
                <input type="text" name="foto" placeholder="Ingrese una foto" required/>
            </label>
            <br>
            <label>
                Tipo
                <select name="tipo" required>
                    <option value="perro">Perro</option>
                    <option value="gato">Gato</option>
                </select>
            </label>
            <br>
            <input type="submit" value="Añadir"/>
        </form>
    </body>
</html>
