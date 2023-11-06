<%-- 
    Document   : addUser
    Created on : 24/09/2023, 10:31:24 p. m.
    Author     : harby
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Usuario</title>
</head>
<body>
    <h1>Agregar Usuario</h1>
    <form action="UsuarioServlet?action=insert" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        
        <input type="submit" value="Agregar Usuario">
    </form>
    
    <br>
    <a href="UsuarioServlet?action=list">Volver a la lista de usuarios</a>
</body>
</html>
