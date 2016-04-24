  package controles;
import entidades.Cuenta;

public class ControlTransferencia {
   Cuenta cuenta;

   public ControlTransferencia(){
     cuenta = new Cuenta(); //Asume que la instancia persiste durante la sesion
   }

   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){
      return(cuenta.validar(ncuenta));
   }

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

   public void setSaldo(int ncuenta, float saldo){
      cuenta.setSaldo(ncuenta, saldo);
   }

   public void sumaSaldo(int ncuenta, float saldo){
      cuenta.setSaldo(ncuenta, saldo);
   }

   public float consultaSaldo(int ncuenta){
      return cuenta.getSaldo(ncuenta);
   }
}
