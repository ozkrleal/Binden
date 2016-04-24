package interfaces;
import java.sql.Connection;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controles.ControlExtraccion;

//Utilizando urlPatterns permite agregar parametros a la anotacion
@WebServlet(urlPatterns = "/Extraer", 
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazExtraccion")
  }
)
public class InterfazExtraccion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  HttpSession sesion;
  Connection con;

   ///Redirige cualquier GET recibido a POST
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException { 
      doPost(request,response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      thisRequest = request;
      thisResponse = response;
      String operacion;
      ///La conexion se establece en ContextListener
      con = (Connection) request.getServletContext().getAttribute("DBConnection");

      ///Solo permitir acceso si existe una sesion
      sesion = thisRequest.getSession(false);
      if (sesion == null) ///El usuario no está logeado
         thisResponse.sendRedirect("index.html");

      operacion = thisRequest.getParameter("operacion");
      if (operacion == null ) ///Es la primera llamada a ExtraerEfectivo (viene de menu.html, con usuario exitosamente logeado)
         solicitarCantidad();
      else if(operacion.equals("extraer"))
         extraerEfectivo();
      else if (operacion.equals("terminar")){
         RequestDispatcher rd=request.getRequestDispatcher("Logout");
         rd.forward(request, response);
      }
   }

   private void encabezadoHTML() throws ServletException, IOException {
      thisResponse.setContentType("text/html");
      out = thisResponse.getWriter();
      ///Preparar el encabezado de la pagina Web de respuesta
      out.println(
         "<!DOCTYPE html> \n" +
         "<html> \n" +
         "<head> \n" +
         "<meta charset=utf-8> \n" +
         "</head> \n" +
         "<body> \n" +
         "<title>Banco AMSS</title> \n" +
         "<h2>Cajero Electronico</h2> \n" +
         "<h3>Extraer efectivo</h3>\n"
      );
   }
  ///Es importante observar que todas las fromas (form) definen la accion POST para
  ///que el metodo doPost sea el que se ejecuta en todos los casos.
   private void solicitarCantidad() throws ServletException, IOException {
      encabezadoHTML(); ///Preparar encabezado de la pagina Web
      out.println("Cantidad a extraer</p> \n" +
         "<form method=POST action=Extraer> \n" +
            "<input type=hidden name=operacion value=extraer> \n" +
            "Cantidad: <input type=text name=cantidad size=15 autofocus></p> \n" +
            "<input type=submit value=Enviar name=B1></p> \n" +
         "</form> \n"
      );

      out.println(
         "<form method=POST action=Extraer> \n" +
            "<input type=hidden name=operacion value=terminar> \n" +
            "<input type=submit value=Cancelar name=B2></p> \n" +
            "</form> \n" +
        "</body>" +
        "</html>"
      );
   }

   private void extraerEfectivo() throws ServletException, IOException {
      int ncuenta;
      boolean resultado;
      String sCuenta;
      String errorMsg = null;
      String sCantidad = thisRequest.getParameter("cantidad");
      if (sCantidad.length() > 0) {
         float cantidad = Float.valueOf(sCantidad.trim()).floatValue();
         if (cantidad > 0) {
            HttpSession sesion = thisRequest.getSession(false);
            sCuenta =  (String) sesion.getAttribute("cuenta");
            ncuenta = Integer.parseInt(sCuenta);
            ControlExtraccion ce = new ControlExtraccion();
            resultado = ce.extraerEfectivo(ncuenta, cantidad, con);
            if (resultado == true) { ///Extracción exitosa
               encabezadoHTML();///Preparar encabezado de la pagina Web
               float saldo = ce.consultaSaldo(ncuenta, con);
               out.println(
                  "Operacion exitosa.</p> \n" +
                  "Su saldo actual es: " + saldo +".</p> \n" +
                  "<form method=POST action='Extraer'> \n" +
                     "<input type='hidden' name='operacion' value='terminar'> \n" +
                     "<input type='submit' value='Terminar'></p> \n" +
                   "</form> \n" +
                  "</body> \n" +
                  "</html>"
               );
            }else {
               errorMsg = "Esa cantidad excede a su saldo, o es mayor a lo que puede extraer en una sesion. Por favor indique una cantidad menor.";
            }
         } else {
            errorMsg = "Por favor indique una cantidad mayor que cero.";
         }
      } else {
         errorMsg = "Por favor indique una cantidad.";
      }
      if(errorMsg != null) {
        out.println("<h3><font color=red>" + errorMsg + "</font></h3>");
        solicitarCantidad();
      }
   }
}
