package controles;
import java.sql.Connection;
import entidades.Usuario;

public class ControlRegistro {
   Usuario usuario;

   public ControlRegistro(){
     usuario = new Usuario();
   }

   public void agregarUsuario (String correo, String paswd, String tipoUsuario, String nombre, String descripcion, Connection con){
      usuario.agregar(correo, paswd, tipoUsuario, nombre, descripcion, con);
   }

   ///Valida al cliente en la base de datos
   public int validarUsuario(String correo, Connection con){
      int ncuenta = usuario.validar(correo, con);
      return( ncuenta );
   }

}
