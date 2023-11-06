<%-- 
    Document   : editUser
    Created on : 24/09/2023, 10:34:26 p. m.
    Author     : harby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="UsuarioServlet?action=update" method="post">
        <input type="hidden" name="id" value="${usuario.id}">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required><br>
        
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required><br>
        
        <input type="submit" value="Guardar Cambios">
    </form>
    
    <br>
    <a href="UsuarioServlet?action=list">Volver a la lista de usuarios</a>
</body>
</html>
