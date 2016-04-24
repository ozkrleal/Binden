package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlExtraccion;


public class InterfazExtraccion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlExtraccion ce;

  //Es importante observar que todos los metodos definen la operacion GET para
  //que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar".
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisRequest = request;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Preparar el encabezado de la pagina Web de respuesta
    out.println("<!DOCTYPE html >");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<title>Banco AMSS</title>");
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Extraer efectivo</h3>");

    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarExtraccion();
    }else if(operacion.equals("validar")){
       validarCuenta();
    } else if (operacion.equals("extraer")){
      extraerEfectivo();
    }
  }

  public void iniciarExtraccion(){
    out.println("<p>Indique el numero de cuenta</p>");
    out.println("<form method=\"GET\" action=\"Extraer\">");
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
    ce = new ControlExtraccion();
    //La funcion trim() elimina espacios antes y despues del valor
    int cuenta = Integer.parseInt(thisRequest.getParameter("cuenta").trim());
    boolean existente = ce.validarCuenta(cuenta);
    if (existente){
       out.println("<p>Indique la cantidad a extraer</p>");
       out.println("<form method=\"GET\" action=\"Extraer\">");
       out.println("<input type=\"hidden\" name=\"operacion\" value=\"extraer\"/>");
       out.println("<input type=\"hidden\" name=\"cuenta\" value=\"" + cuenta + "\"/>");
       out.println("<p> Cantidad  <input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
       out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
       out.println("</form>");

       out.println("<form method=\"GET\" action=\"menu.html\">");
       out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</body>");
       out.println("</html>");
    } else {
       iniciarExtraccion();
    }
  }

  public void extraerEfectivo(){
    int cuenta = Integer.parseInt(thisRequest.getParameter("cuenta").trim());
    float cantidad = Float.valueOf(thisRequest.getParameter("cantidad").trim()).floatValue();
    boolean resultado = ce.extraerEfectivo(cuenta,cantidad);
    if (resultado == true) {
      out.println("<p>Tome su efectivo.</p>");
      out.println("<p>Fue un placer servirlo. Gracias por operar con nuestro banco.</p>");
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    }else {
      out.println("<h3>Esa es una cantidad excesiva, indique una cantidad menor.</h3>");
      out.println("<p>Indique de nuevo la cantidad a extraer</p>");
      out.println("<form method=\"GET\" action=\"Extraer\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"extraer\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta\" value=\"" + cuenta + "\"/>");
      out.println("<p> Cantidad  <input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
      out.println("</form>");

       out.println("<form method=\"GET\" action=\"menu.html\">");
       out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
       out.println("</form>");

      out.println("</body>");
      out.println("</html>");
    }
  }
}
