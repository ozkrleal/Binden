package controles;
import entidades.Usuario;
import entidades.Solicitud;
import java.sql.Connection;

public class ControlSolicitud {
  Usuario usuario1, usuario2;
  Solicitud solicitud;

   public ControlSolicitud(){
      usuario1 = new Usuario();
      usuario2 = new Usuario();
      solicitud = new Solicitud();
   }

   public ArrayList<Usuario> obtenerUsuarios(String tipo, Connection con){
     usuario1.obtenerUsuarios(tipo, con);
   }

   public String crearSolicitud(String sender, String receiver, Connection con){
     return solicitud.mandarSolicitud(sender, receiver, con);
   }

   public void aceptarSolicitud(String solic, Connection con){
     solicitud.aceptarSolicitud(solic, con);
   }

   public void ignorarSolicitud(String solic, Connection con){
     solicitud.borrarSolicitud(solic, con);
   }

   public String obtenerTipoUsuario(String usuario){
     return usuario1.obtenerTipoUsuario(usuario);
   }

}
