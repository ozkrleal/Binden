package interfaces;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Utilizando urlPatterns en lugar de urlPattern permite agregar mas parametros a la anotacion
@WebServlet(urlPatterns = "/Conocer",
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazSolicitud")
  }
)
public class InterfazSolicitud extends HttpServlet {

   //Redirige cualquier GET recibido a POST
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doPost(request,response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      PrintWriter out = response.getWriter();

        HttpSession sesion = request.getSession(false);

        String idCuenta = sesion.getAttribute("cuenta");

        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        ControlSolicitud cSolicitud = new ControlSolicitud();

        ArrayList<Usuario> listUsuarios = cSolicitud.obtenerUsuarios();

      if (sesion == null) { ///El usuario no esta logeado
		     out.println("<font color=red>Favor de proporcionar primero usuario y clave.</font>");
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			   rd.include(request, response); ///include() permite que el mensaje anterior se incluya en la pagina Web
      } else {
         //Mostrar el menu de opciones
         response.setContentType("text/html");
         out.println(
            "<!DOCTYPE html> \n" +
            "<html> \n" +
            "<head> \n" +
            "<meta charset=utf-8> \n" +
            "</head> \n" +
            "<body> \n" +
            "<title>Binden</title> \n" +
            "<h2>Citas para proyectos de servicio social y empresas</h2> \n" +
            "<h3>Indica la operacion que deseas realizar</h3> </p>" +
            "<a href=Conocer>Conocer gente</a> </p>" +
            "<a href=Solicitudes>Ver solicitudes</a> </p>" +
            "<a href=Agenda>Consultar agenda</a> </p>" +
            "</body>" +
            "</html>"
         );
      }
   }
}
