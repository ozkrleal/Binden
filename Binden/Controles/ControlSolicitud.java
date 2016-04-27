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

   public ArrayList<Usuario> obtenerUsuarios (String tipo, Connection con){
     usuario.obtenerUsuarios(tipo, con);
   }

   public void mandarSolicitud(Usuario sender, Usuario receiver, Connection con){
     solicitud.crearSolicitud(sender, receiver, con);
   }

   public void aceptarSolicitud(Usuario receiver, Solicitud solic, Connection con){
     solicitud.aceptarSolicitud(receiver, solic, con);
   }

   public void ignorarSolicitud(Usuario receiver, Solicitud solic, Connection con){
     solicitud.ignorarSolicitud(receiver, solic, con);
   }

}
