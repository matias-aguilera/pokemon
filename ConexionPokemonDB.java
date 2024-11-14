import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexionPrueba {

    public static void main(String[] args) {
        try {
            // Buscar el contexto inicial
            Context initContext = new InitialContext();
            // Buscar el recurso JNDI
            DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/pokemonDB");

            // Obtener una conexión del pool
            try (Connection conn = ds.getConnection()) {
                System.out.println("Conexión establecida con éxito a pokemonDB!");

                // Realizar una consulta de prueba
                String sql = "SELECT TOP 1 * FROM Usuario";  // Cambia `Usuario` por una tabla existente
                try (PreparedStatement ps = conn.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        System.out.println("Prueba de conexión exitosa: Datos de la primera fila - ID: " 
                            + rs.getInt("UsuarioID") + ", Nombre: " + rs.getString("Nombre"));
                    } else {
                        System.out.println("Prueba de conexión exitosa, pero no hay datos en la tabla.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
