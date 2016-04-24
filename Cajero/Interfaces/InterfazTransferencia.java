package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlTransferencia;

public class InterfazTransferencia extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlTransferencia ce;

   public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Pagina Web de respuesta
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<title>Banco AMSS</title>");
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Transferencia de Saldo</h3>");

    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarTransferencia();
    } else if(operacion.equals("validar")){
      validarCuentas();
    } else if (operacion.equals("extraer")){
      efectivoEnviado();
    }
   }

  public void iniciarTransferencia(){
    out.println("<p>Indique el numero de cuenta</p>");
    out.println("<form method=\"GET\" action=\"Transferencia\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
    out.println("<p> Cuenta Remitente  <input type=\"text\" name=\"remitente\" size=\"15\"></p>");
    out.println("<p> Cuenta Destinatario  <input type=\"text\" name=\"destintario\" size=\"15\"></p>");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }

  public void validarCuentas(){
    ce = new ControlTransferencia();
    //La funcion trim() elimina espacios antes y despues del valor
    int remitente = Integer.parseInt(thisRequest.getParameter("remitente").trim());
    int destinatario = Integer.parseInt(thisRequest.getParameter("destinatario").trim());

    boolean existenteR = ce.validarCuenta(remitente);
    boolean existenteD = ce.validarCuenta(destinatario);
    if (existenteR && existenteD){
       out.println("<p>Indique la cantidad a enviar</p>");
       out.println("<form method=\"GET\" action=\"Transferencia\">");
       out.println("<input type=\"hidden\" name=\"operacion\" value=\"extraer\"/>");
       out.println("<input type=\"hidden\" name=\"remitente\" value=\"" + remitente + "\"/>");
       out.println("<input type=\"hidden\" name=\"destinatario\" value=\"" + destinatario + "\"/>");
       out.println("<p> Cantidad  <input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
       out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
       out.println("</form>");

       out.println("<form method=\"GET\" action=\"menu.html\">");
       out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
       out.println("</form>");

       out.println("</body>");
       out.println("</html>");
    } else {
       iniciarTransferencia();
    }
  }

  public void efectivoEnviado(){
    int remitente = Integer.parseInt(thisRequest.getParameter("remitente").trim());
    int destinatario = Integer.parseInt(thisRequest.getParameter("destinatario").trim());
    float cantidad = Float.valueOf(thisRequest.getParameter("cantidad").trim()).floatValue();
    boolean resultado = ce.extraerEfectivo(remitente,cantidad);

    if (resultado == true) {
      ce.sumaSaldo(destinatario, cantidad);
      out.println("<p>Efectivo enviado.</p>");
      out.println("<p>Fue un placer servirlo. Gracias por operar con nuestro banco.</p>");
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    }else {
      out.println("<h3>Esa es una cantidad excesiva, no se cuenta con tanto saldo en la cuenta remitente, indique una cantidad menor.</h3>");
      out.println("<p>Indique de nuevo la cantidad a extraer</p>");
      out.println("<form method=\"GET\" action=\"Transferencia\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"extraer\"/>");
      out.println("<input type=\"hidden\" name=\"remitente\" value=\"" + remitente + "\"/>");
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

    // out.println("</body>");
    // out.println("</html>");

}
