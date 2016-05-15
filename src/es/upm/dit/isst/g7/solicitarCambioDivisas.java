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
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAO;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.SolicitudCambioDivisas;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;
import es.upm.dit.isst.yahoo.YahooCurrencyConverter;

@SuppressWarnings("serial")
public class solicitarCambioDivisas extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String divisaSolicitada = req.getParameter("divisas");
		System.out.println("Divisa dolicitada: "+divisaSolicitada);
		
		String numeroTarjeta = req.getParameter("tarjetas");
		System.out.println("tarnumeroTarjetajeta: "+numeroTarjeta);
		
		String divisaPredeterminada = req.getParameter("divisaPredeterminada");
		System.out.println("divisaPredeterminada: "+divisaPredeterminada);
		
		String cantidadSolicitada = req.getParameter("cantidadSolicitada");
		System.out.println("cantidadSolicitada: "+cantidadSolicitada);
		
		String localTime = req.getParameter("localTime");
		System.out.println("localTime: "+localTime);
		
		String numeroCuenta = req.getParameter("numeroCuenta");
		System.out.println("numeroCuenta: "+numeroCuenta);
		
		String user = req.getUserPrincipal().getName();
		
		 YahooCurrencyConverter ycc = new YahooCurrencyConverter();
		 double current  = 0;
	        try {
	            current = ycc.convert(divisaPredeterminada, divisaSolicitada);
	            System.out.println(current);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    double importeDivisaACambiar = Double.parseDouble(cantidadSolicitada)*current;
	    System.out.println("Cambio: "+importeDivisaACambiar);
		
		if(user!= null){
			//Escribe en tabla SolicitudCambioDivisas
			 SolicitudCambioDivisasDAO daoCambio = SolicitudCambioDivisasDAOImpl.getInstance();
			 SolicitudCambioDivisas sol = daoCambio.Create(1, Double.parseDouble(cantidadSolicitada), divisaSolicitada, divisaPredeterminada, Long.parseLong(numeroCuenta), 1, Long.parseLong(numeroTarjeta),importeDivisaACambiar);
		}
		resp.sendRedirect("/isst_grupo07_socialex");
	}
}
