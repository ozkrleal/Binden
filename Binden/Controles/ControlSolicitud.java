package controles;
import entidades.Usuario;
import java.sql.Connection;

public class ControlSolicitud {
  Usuario usuario;

   public ControlSolicitud(){
      usuario = new Usuario();
   }

   public ArrayList<Usuario> obtenerUsuarios (String tipo, String con){
     usuario.obtenerUsuarios(tipo, con);
   }

   public void mandarSolicitud(){

   }

   public void aceptarSolicitud(){

   }

   public void ignorarSolicitud(){

   }

}
