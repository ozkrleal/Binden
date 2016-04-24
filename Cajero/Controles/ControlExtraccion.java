package controles;
import entidades.Cuenta;

public class ControlExtraccion {
   Cuenta cuenta;

   public ControlExtraccion(){
     cuenta = new Cuenta(); //Asume que la instancia persiste durante la sesion
   }

   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){
      return(cuenta.validar(ncuenta));
   }

   //Implementa una regla de negocio;
   //no se puede extaer mas de $3000 en una transaccion
   public boolean extraerEfectivo(int ncuenta, float cantidad){
      if(cantidad < 3000.0f) {
           float saldo = cuenta.getSaldo(ncuenta);
           if (saldo > cantidad) {
               saldo = saldo - cantidad;
               cuenta.setSaldo(ncuenta,saldo);
               return true; //Transaccion aceptada
           }else {
              return false; //No hay fondos suficientes en la cuenta
           }
      } else {
           return false; //Cantidad demasiado alta
      }
   }

   public float consultaSaldo(int ncuenta){
      return cuenta.getSaldo(ncuenta);
   }
}
