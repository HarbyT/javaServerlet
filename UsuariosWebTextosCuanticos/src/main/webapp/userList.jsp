<%-- 
    Document   : userList
    Created on : 24/09/2023, 10:33:54 p. m.
    Author     : harby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>
                    <a href="UsuarioServlet?action=edit&id=${usuario.id}">Editar</a> |
                    <a href="UsuarioServlet?action=delete&id=${usuario.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="UsuarioServlet?action=add">Agregar Usuario</a>
</body>
</html>
