package es.upm.dit.isst.g7;

import java.io.IOException;

import javax.mail.Address; 
import javax.mail.Message; 
import javax.mail.MessagingException;
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties; 

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

@SuppressWarnings("serial")
public class recargaCuenta extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String divisa = req.getParameter("divisas"); //divisas
		String importe = req.getParameter("cantidadRecarga");
		String fechaCliente = req.getParameter("localTime");
		String numeroCuenta = req.getParameter("numeroCuenta");
		System.out.println("nCuenta: "+numeroCuenta);
	    System.out.println("Local time: "+fechaCliente);
	    String num = req.getParameter("tarjetas");
	    System.out.println("num: "+num);
	    Long numeroTarjeta = Long.parseLong(num, 10);
	    System.out.println("parselong: "+numeroTarjeta);
		
		if (importe.isEmpty()) {
			System.out.println("Importe vacío. Vuelve a la página principal");
			RequestDispatcher view = req.getRequestDispatcher("recargarCuenta.jsp");
			view.forward(req, resp);
		} else {
			// Añadimos importe a la base de datos
			
			Double value = Double.parseDouble(importe);
			String user = req.getUserPrincipal().getName();
			System.out.println(user);
			System.out.println(value);
			ClienteDAO dao = ClienteDAOImpl.getInstance();
			CuentaDAO daoCuenta = CuentaDAOImpl.getInstance();
			Long numeroCuentaLong = Long.parseLong(numeroCuenta);
			Cuenta cuenta = daoCuenta.GetCuenta(numeroCuentaLong);
			cuenta.addSaldo(divisa, value);
			daoCuenta.update(cuenta);
			
			Tipo tipo = Tipo.INGRESAR;
			TransaccionDAO dao2 = TransaccionDAOImpl.getInstance();
			Transaccion t = dao2.createTransaccion(numeroCuentaLong, fechaCliente, divisa, value, "Recarga de dinero", tipo, num);
			
			try{
				Message msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
	            msg.setFrom(new InternetAddress("ISSTGrupo07SocialEX@gmail.com", "Sistema de cambio de divisas"));
	            msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(user, "Propietario de la cuenta"));
	            msg.setSubject("Ingreso de dinero en MassMoney");
	            msg.setText("Usted ha realizado un ingreso en su cuenta por el importe de " + importe + " " + divisa);
	            Transport.send(msg);
			}catch(MessagingException e){ 
				resp.setContentType("text/plain");
	            resp.getWriter().println("Algo ha ido mal. Por favor, inténtelo otra vez.");
	        } 
			
			
			//Llama al servlet principal para cargar los datos de nuevo
			resp.sendRedirect("/isst_grupo07_socialex");
		}

	}
}
