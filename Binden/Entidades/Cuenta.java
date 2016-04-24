package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuenta {
   PreparedStatement stmt;

   public void agregar(int cuenta, float saldo, Connection con){
      try {
         String query = "INSERT INTO cuenta (idcuenta, saldo) VALUES (?, ?)";
         stmt = con.prepareStatement(query);
         stmt.setInt(1, cuenta);
         stmt.setFloat(2, saldo);
         stmt.execute();
      }catch (Exception e) { System.out.println ("No se pudo ejecutar agregar() a la tabla Cuenta" + e ); }
   }

   public void setSaldo(int cuenta, float saldo, Connection con){
      try {
         String query = "UPDATE cuenta SET saldo = ? WHERE idcuenta = ?";
         stmt = con.prepareStatement(query);
         stmt.setFloat(1, saldo);
         stmt.setInt(2, cuenta);
         stmt.execute();
      } catch (SQLException e) {System.out.println ("No se pudo ejecutar setSaldo() a la tabla Cuenta" + e);}
   }

   public float getSaldo(int cuenta, Connection con){
      float saldo = 0.0f;
      try {
         String query = "SELECT saldo FROM cuenta WHERE idcuenta = ?";
         stmt = con.prepareStatement(query);
         stmt.setInt(1, cuenta);

         ResultSet rs = stmt.executeQuery();
         rs.next(); //Va al registro ya validado
         saldo = rs.getFloat("saldo");
         rs.close();
      } catch (SQLException e) {System.out.println ("No se pudo ejecutar getSaldo() a la tabla Cuenta" + e);}
      return saldo;
   }
   
}
