package controles;
import entidades.Cuenta;

public class ControlConsulta {
   Cuenta cuenta;

   public ControlConsulta(){
     cuenta = new Cuenta(); //Asume que la instancia persiste durante la sesion
   }
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){
      return(cuenta.validar(ncuenta));
   }

   public float consultaSaldo(int ncuenta){
      return cuenta.getSaldo(ncuenta);
   }
}
