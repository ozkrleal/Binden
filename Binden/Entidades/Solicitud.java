package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Solicitud {
   PreparedStatement stmt;
   String idSolicitud,String usuario1,String usuario2;

   public Solicitud(String sender, String receiver, String id){
      idSolicitud = id;
      usuario1 = sender;
      usuario2 = receiver;
   }

   public String mandarSolicitud(String usuario1,String usuario2, Connection con){
      try {
         String query = "INSERT INTO Solicitud (idUsuarioS1, idUsuarioS2) VALUES (?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, usuario1);
         stmt.setString(2, usuario2);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }

      try {
         String query = "SELECT idSolicitud FROM Solicitud WHERE idUsuario1 = ? AND idUsuario2 = ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, usuario1);
         stmt.setString(2, usuario2);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) { ///Va al primer registro si lo hay
            String idSol = rs.getInt ("idSolicitud");
            rs.close();
            return( idSol );
         }
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }

   public void borrarSolicitud(String idSolicitud Connection con){
      try {
         String query = "DELETE FROM solicitud WHERE idSolicitud = ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, idSolicitud);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }

   public void aceptarSolicitud(String idUsuario1,String idusuario2 , Connection con){
      try {
         String query = "INSERT INTO usuario_conexion (idUsuarioS1, idUsuarioS2) VALUES (?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, idUsuario1);
         stmt.setString(2, idusuario2);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }

   public ArrayList<Solicitud> obtenerSolicitudes(String idUsuario2, Connection con){
     try {
       ArrayList<Solicitud> lista = new ArrayList<>;
        String query = "SELECT idSolicitud FROM solicitud WHERE idUsuario2 = ? ";
        stmt = con.prepareStatement(query);
        stmt.setString(1, idUsuario2);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

           lista.add(new Solicitud(rs.getString("idSolicitud")  ));
        }
         rs.close();
         return( lista );

     } catch (SQLException e) {}
     return 0;

   }

}
