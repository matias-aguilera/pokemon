import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/AgregarUsuarioServlet")
public class AgregarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar la respuesta en HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");

        // Conectar a la base de datos y agregar el usuario
        try {
            // Configurar el recurso JNDI
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/pokemonDB");

            try (Connection conn = ds.getConnection()) {
                // Sentencia SQL para insertar un nuevo usuario
                String sql = "INSERT INTO Usuario (Nombre, Contraseña) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, contrasena);  // Puedes encriptarla antes de guardarla

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        out.println("<h3>Usuario agregado con éxito!</h3>");
                    } else {
                        out.println("<h3>Error al agregar el usuario.</h3>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error al conectar con la base de datos: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
}
