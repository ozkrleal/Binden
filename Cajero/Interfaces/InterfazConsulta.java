package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlConsulta;

public class InterfazConsulta extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlConsulta ce;

   public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<title>Banco AMSS</title>");
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Consultar Saldo</h3>");


    String operacion = request.getParameter("operacion");

    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarConsulta();
    }else if(operacion.equals("validar")){
       validarCuenta();
    }
    // out.println("<a href=\"menu.html\"> Regresar al menu principal </a>");

  }

  public void iniciarConsulta(){
    out.println("<p>Indique el numero de cuenta</p>");
    out.println("<form method=\"GET\" action=\"Consulta\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
    out.println("<p> Cuenta  <input type=\"text\" name=\"cuenta\" size=\"15\"></p>");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }

  public void validarCuenta(){
    ce = new ControlConsulta();
    //La funcion trim() elimina espacios antes y despues del valor
    int cuenta = Integer.parseInt(thisRequest.getParameter("cuenta").trim());
    float cantidad = Float.valueOf(thisRequest.getParameter("cantidad").trim()).floatValue();
    boolean existente = ce.validarCuenta(cuenta);

    if (existente){
      cantidad = ce.consultaSaldo(cuenta);

       out.println("<p>Su saldo es de:</p>");
       out.println("<form method=\"GET\" action=\"Consulta\">");
       out.println("<input type=\"hidden\" name=\"cuenta\" value=\"" + cuenta + "\"/>");
       out.println("<input type=\"hidden\" name=\"cantidad\" value=\"" + cantidad + "\"/>");
       out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
       out.println("</form>");


       out.println("</body>");
       out.println("</html>");
    } else {
       iniciarConsulta();
    }
  }

}
