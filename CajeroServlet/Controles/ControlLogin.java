package controles;
import java.sql.Connection;
import entidades.Cliente;

public class ControlLogin {
   Cliente cliente;

   public ControlLogin(){
     cliente = new Cliente();
   }
   
   public void agregarCliente (String usuario, String paswd, int cuenta, String nombre, Connection con){
      cliente.agregar(usuario, paswd, cuenta, nombre, con);
   }
    
   ///Valida al cliente en la base de datos
   public int validarCliente(String usuario, String password, Connection con){
      int ncuenta = cliente.validar(usuario, password, con);
      return( ncuenta );
   }

}
