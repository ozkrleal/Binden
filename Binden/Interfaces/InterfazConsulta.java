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

@WebServlet(urlPatterns = "/Consulta",
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazConsulta")
  }
)
public class InterfazConsulta extends HttpServlet {
    ///Redirige cualquier GET recibido a POST
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { doPost(request,response);
   }
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    ///Si la sesion existe, invalidarla
    HttpSession session = request.getSession(false);
    if(session != null){
    	session.invalidate();
    }
    out.println("<!DOCTYPE html> \n" +
       "<html> \n" +
       "<head> \n" +
       "<meta charset=\"utf-8\"> \n" +
       "</head> \n" +
       "<body> \n" +
       "<title>Banco AMSS</title> \n" +
       "<h2>Cajero Electronico</h2> \n" +
       "<h3>Consultar Saldo</h3> \n" +
       "Esta opción no esta disponible por el momento.</p> \n" +
       "</p> \n" +
       "<a href=\"index.html\"> Regresar al inicio de la sesion</a> \n" +
       "</body> \n" +
       "</html>"
    );
  }

}
