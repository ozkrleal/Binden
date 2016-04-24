package interfaces;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controles.ControlRegistro;

@WebServlet(urlPatterns = "/Registrar",
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazRegistro")
  }
)
public class InterfazRegistro extends HttpServlet {

     ///Redirige cualquier GET recibido a POST
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
       doPost(request,response);
     }

  	protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

  		String tipoUsuario = request.getParameter("tipoUsuario");
  		String nombre = request.getParameter("nombre");
      String correo = request.getParameter("correo");
  		String contra = request.getParameter("contra");
      String ubicacion = request.getParameter("ubicacion");
  		String descripcion = request.getParameter("descripcion");

      ///La conexion se establecio en ContextListener
      Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

  		ControlRegistro cRegistro = new ControlRegistro();
  		cRegistro.agregarUsuario(correo, contra, tipoUsuario, nombre, descripcion, conn);

			PrintWriter out = response.getWriter();
		  out.println("<h3><font color=green>La cuenta ha sido creada!</font></h3>");
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			rd.include(request, response); ///include() permite que el mensaje anterior se incluya en la pagina Web


  	}

  }
