package controles;

import entidades.Cuenta;
import java.sql.Connection;

public class ControlExtraccion {
   Cuenta cuenta;

   public ControlExtraccion(){
     cuenta = new Cuenta();
   }

   ///Implementa una regla de negocio;
   ///no se puede extaer mas de $3000 en una transaccion
   public boolean extraerEfectivo(int ncuenta, float cantidad, Connection con){
      if(cantidad < 3000.0f) {
           float saldo = cuenta.getSaldo(ncuenta, con);
           if (saldo > cantidad) {
               saldo = saldo - cantidad;
               cuenta.setSaldo(ncuenta,saldo, con);
               return true; ///Transaccion aceptada
           }else {
              return false; ///No hay fondos suficientes en la cuenta
           }
      }
      return false; ///Cantidad demasiado alta
   }

   public float consultaSaldo(int ncuenta, Connection con){
      return cuenta.getSaldo(ncuenta, con);
   }
}
