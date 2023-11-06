package com.mycompany.usuarioswebtextoscuanticos.controller;

import com.mycompany.usuarioswebtextoscuanticos.dao.UsuarioDAO;
import com.mycompany.usuarioswebtextoscuanticos.model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Si no se especifica ninguna acción, muestra la lista de usuarios por defecto
        }

        switch (action) {
            case "list":
                listUsuarios(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "insert":
                insertUsuario(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateUsuario(request, response);
                break;
            case "delete":
                deleteUsuario(request, response);
                break;
            default:
                listUsuarios(request, response);
        }
    }

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            // Obtener la lista de usuarios desde la base de datos
            List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
            // Establecer la lista de usuarios como atributo en la solicitud
            request.setAttribute("usuarios", usuarios);
            // Redirigir a la página JSP para mostrar la lista de usuarios
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Redirigir a la página JSP para mostrar el formulario de agregar usuario
        request.getRequestDispatcher("addUser.jsp").forward(request, response);
    }

    private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        try {
            // Crear un nuevo usuario con los datos proporcionados
            Usuario nuevoUsuario = new Usuario(nombre, apellido);
            // Agregar el nuevo usuario a la base de datos
            usuarioDAO.addUser(nuevoUsuario);
        } catch (SQLException e) {
         
        }

        // Redirigir a la acción de listar usuarios después de agregar uno nuevo
        response.sendRedirect("UsuarioServlet?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            // Buscar el usuario por su ID en la base de datos
            Usuario usuarioExistente = usuarioDAO.findUserById(id);
            // Establecer el usuario como atributo en la solicitud
            request.setAttribute("usuario", usuarioExistente);
            // Redirigir a la página JSP para mostrar el formulario de edición
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        try {
            // Crear un usuario actualizado con los datos proporcionados
            Usuario usuarioActualizado = new Usuario(id, nombre, apellido, null, null, null);
            // Actualizar el usuario en la base de datos
            usuarioDAO.updateUser(usuarioActualizado);
        } catch (SQLException e) {
           
        }

        // Redirigir a la acción de listar usuarios después de actualizar
        response.sendRedirect("UsuarioServlet?action=list");
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            // Eliminar el usuario de la base de datos por su ID
            usuarioDAO.deleteUser(id);
        } catch (SQLException e) {
           
        }

        // Redirigir a la acción de listar usuarios después de eliminar
        response.sendRedirect("UsuarioServlet?action=list");
    }
}
