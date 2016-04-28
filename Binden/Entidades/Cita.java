package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cita {
   PreparedStatement stmt;
   String idCita,String usuario1,String usuario2, Date fecha, String descripcion ;

   public Cita(String user1 ,String user2,String id,Date date,String desc){
     usuario1 = user1;
     usuario2 = user2;
     idCita = id;
     fecha = date;
     descripcion = desc;
   }
   public void agregarCita(String usuario1,String usuario2,Date fecha,String descripcion ,Connection con){
      try {
         String query = "INSERT INTO cita (idUsuario1, idUsuario2,fecha,descripcion) VALUES (?, ?, ?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setString(1, usuario1);
         stmt.setString(2, usuario2);
         stmt.setDate(3, fecha);
         stmt.setString(4, descripcion);

         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cliente" + e ); }
   }

   public ArrayList<Usuario> obtenerCitas(String usuario, Connection con){
     try {
       ArrayList<Cita> lista = new ArrayList<>;
        String query = "SELECT (SELECT u.nombre FROM usuario u WHERE u.idUsuario =c.idUsuario2 ) as nombre,fecha,descripcion FROM cita c WHERE idUsuario1 = ? ";
        stmt = con.prepareStatement(query);
        stmt.setString(1, usuario);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

           lista.add(new Cita(rs.getString("idUsuario1"),rs.getString("nombre"),
           rs.getDate("fecha"),rs.getString("descripcion") )   );
        }
         rs.close();
         return( lista );

     } catch (SQLException e) {}
     return 0;

   }
}
