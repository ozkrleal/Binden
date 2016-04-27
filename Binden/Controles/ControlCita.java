package controles;
import java.sql.Connection;
import java.util.Calendar;
import entidades.Usuario;
import entidades.Cita;

public class ControlCita {
   Cita cita;
   Usuario usuario1;
   Usuario usuario2;

   public ControlCita(){
     cita = new Cita();
     usuario1 = new Usuario();
     usuario2 = new Usuario();
   }

   public ArrayList<Usuario> obtenerUsuarios (String tipo, Connection con){
     usuario.obtenerUsuarios(tipo, con);
   }

   public void agendarCita(Usuario receiver, Usuario sender, Connection con){
     cita.crearCita(sender, receiver, con);
   }

   public void aceptarCita(Usuario receiver, Cita cit, Connection con){
     cita.aceptarCita(receiver, cit, con);
   }

   public void cancelarCita(Usuario receiver, Cita cit, Connection con){
     cita.ignorarCita(receiver, cit, con)
   }

}
