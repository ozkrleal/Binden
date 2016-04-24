package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
   PreparedStatement stmt;
   public int maxId(){

     String query = " SELECT MAX(column_name) FROM table_name";
     stmt = con.prepareStatement(query);
     ResultSet rs = stmt.executeQuery();
     return rs+1;


   }

   public void agregar(String correo,String contraseana,String tipo,String nombre,String descripcion, Connection con){
      try {
         String query = "INSERT INTO Usuario (nombre, tipoUsuario, contra, correo, descripcion) VALUES (?, ?, ?, ?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, nombre);
         stmt.setString(2, tipo);
         stmt.setInt(3, contraseana);
         stmt.setString(4, correo);
         stmt.setString(5, descripcion);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }

   public int validarCorreo(String correo, Connection con) {
      try {
         String query = "SELECT idUsuario FROM Usuario WHERE correo = ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, correo);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) { ///Va al primer registro si lo hay
            int nUsuario = rs.getInt ("correo");
            rs.close();
            return( nUsuario );
         }
      } catch (SQLException e) { System.out.println ("No se pudo ejecutar validarCorreo() a la tabla Usuario" + e );}
      return 0;
   }
   public int validar(String correo, String contrasena,Connection con) {
      try {
         String query = "SELECT idUsuario FROM Usuario WHERE correo = ? AND contra = ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, correo);
         stmt.setString(2, contrasena);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) { ///Va al primer registro si lo hay
            int nUsuario = rs.getInt ("correo");
            rs.close();
            return( nUsuario );
         }
      } catch (SQLException e) { System.out.println ("No se pudo ejecutar validar() a la tabla Usuario" + e );}
      return 0;
   }

}
