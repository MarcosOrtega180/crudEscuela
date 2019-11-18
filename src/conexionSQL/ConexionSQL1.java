package conexionSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class ConexionSQL1 {
    Connection conectar = null;
    public Connection conexion() {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Conexión" + e.getMessage());
        }
        return conectar;
    }
}
