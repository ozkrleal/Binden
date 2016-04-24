  package entidades;
import java.sql.*;
import java.io.*;

public class Cuenta {
   Connection conn;
   Statement stmt;

   public Cuenta(){
      try {
        String userName = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost/cajero?autoReconnect=true&useSSL=false";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }

   public boolean validar(int cuenta){
      try {
         stmt.executeQuery ("SELECT idcuenta FROM cuenta WHERE idcuenta = " + cuenta);
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            int nCuenta = rs.getInt ("idcuenta");
            rs.close();
            return( cuenta == nCuenta );
         }else{ return false;}
      } catch (SQLException e) {}
      return false;
   }

   public void agregar(int cuenta, String nombre, float saldo){
      try {
         String s = "INSERT INTO CUENTA (idcuenta, nombre, saldo)" +
                   " VALUES ("+ cuenta + " , '" + nombre + "', " + saldo + " )";
         System.out.println(s);
         stmt.executeUpdate(s);
      }catch (Exception e) { System.out.println ("Cannot update database" + e ); }
   }

  public void sumaSaldo(int cuenta, float saldo){
    float saldoAct = this.getSaldo(cuenta);
    saldoAct = saldoAct + saldo;
      try {
         String s = "UPDATE cuenta SET saldo = " + saldoAct + " WHERE idcuenta = " + cuenta;
         stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);}
   }

   public void setSaldo(int cuenta, float saldo){
      try {
         String s = "UPDATE cuenta SET saldo = " + saldo + " WHERE idcuenta = " + cuenta;
         stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);}
   }

   public float getSaldo(int ncuenta){
      float saldo = 0.0f;
      try {
         stmt.executeQuery ("SELECT saldo FROM cuenta WHERE idcuenta = " +ncuenta);
         ResultSet rs = stmt.getResultSet();
         rs.next(); //Va al registro ya validado
         saldo = rs.getFloat("saldo");
         rs.close();
         return saldo;
      } catch (SQLException e) {System.out.println ("Cannot getSaldo()" + e);}
      return saldo;
   }

}
