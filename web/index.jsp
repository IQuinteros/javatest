<%-- 
    Document   : indexx
    Created on : 17-07-2021, 16:04:39
    Author     : Yunnicio
--%>

<%@page import="javax.persistence.Persistence"%>
<%@page import="controladores.MascotaJpaController"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Mascota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
        
        MascotaJpaController controller = new MascotaJpaController(Persistence.createEntityManagerFactory("TestingPU"));
        List<Mascota> mascotas = controller.findMascotaEntities();
        
        for(int i = 0; i < mascotas.size(); i++){
            out.print(mascotas.get(i).getNombre());
        }
        %>
    </body>
</html>
