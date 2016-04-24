package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
   PreparedStatement stmt;

   public void agregar(String usuario, String paswd, int cuenta, String nombre, Connection con){
      try {
         String query = "INSERT INTO cliente (usuario, password, cuenta, nombre) VALUES (?, ?, ?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, usuario);
         stmt.setString(2, paswd);
         stmt.setInt(3, cuenta);
         stmt.setString(4, nombre);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }
   
   public int validar(String user, String password, Connection con) {
      try {
         String query = "SELECT cuenta FROM cliente WHERE usuario = ? and password = ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, user);
         stmt.setString(2, password);

         ResultSet rs = stmt.executeQuery();
         if (rs.next()) { ///Va al primer registro si lo hay
            int ncuenta = rs.getInt ("cuenta");
            rs.close();
            return( ncuenta );
         }
      } catch (SQLException e) {}
      return 0;
   }
   
}
