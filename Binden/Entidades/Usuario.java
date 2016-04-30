package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
   PreparedStatement stmt;
   String correo,String contra,String tipo,String nombre,,String ubicacion,String descripcion, int idUsuario ;

   public Usuario(String mail,String password,String type,String name,String ubica,String desc, int id){
     correo = mail;
     tipo = type;
     contra = password;
     nombre = name;
     descripcion = desc;
     idUsuario = id;
     ubicacion = ubica;

   }

   public void agregar(String correo,String contra,String tipo,String nombre,String ubicacion,String descripcion, Connection con){
      try {
         String query = "INSERT INTO Usuario (nombre, tipoUsuario, contra, correo, descripcion ,ubicacion) VALUES (?, ?, ?, ?, ?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, nombre);
         stmt.setString(2, tipo);
         stmt.setString(3, contra);
         stmt.setString(4, correo);
         stmt.setString(5, descripcion);
         stmt.setString(6, ubicacion);
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

   public ArrayList<Usuario> obtenerUsuarios(String tipo, Connection con){
     try {
       ArrayList<Usuario> lista = new ArrayList<>;
        String query = "SELECT nombre,idUsuario,correo,tipoUsuario,descripcion,contra FROM Usuario WHERE tipoUsuario = ? ";
        stmt = con.prepareStatement(query);
        stmt.setString(1, tipo);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

           lista.add(new Usuario(rs.getString("correo"),rs.getInt("contra"),
           rs.getString("tipo"),rs.getString("nombre"),rs.getString("descripcion"),
           rs.getInt("idUsuario") )   );
        }
         rs.close();
         return( lista );

     } catch (SQLException e) {}
     return 0;

   }
   public String obtenerTipoUsuario (String idUsuario, Connection con){
     try {
        String query = "SELECT tipoUsuario FROM Usuario WHERE idUsuario = ?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, idUsuario);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { ///Va al primer registro si lo hay
           String tipo = rs.getInt ("tipoUsuario");
           rs.close();
           return( tipo );
        }
     } catch (SQLException e) { System.out.println ("No se pudo ejecutar validarCorreo() a la tabla Usuario" + e );}
     return 0;

   }

   public Usuario obtenerUsuario (String idUsuario, Connection con){
     try {
        String query = "SELECT tipoUsuario FROM Usuario WHERE idUsuario = ?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, idUsuario);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { ///Va al primer registro si lo hay
           String tipo = rs.getInt ("tipoUsuario");
           rs.close();
           return( tipo );
        }
     } catch (SQLException e) { System.out.println ("No se pudo ejecutar validarCorreo() a la tabla Usuario" + e );}
     return 0;

   }
}
