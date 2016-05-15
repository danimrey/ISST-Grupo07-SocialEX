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
public class gestionCambioDivisas extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		Long cuentaAcepta = Long.parseLong(req.getParameter("cuentaAcepta"));
		System.out.println("cuentaSolicitante: "+cuentaAcepta);
		
		Long cuentaSolicitante = Long.parseLong(req.getParameter("cuentaSolicitante"));
		System.out.println("cuentaSolicitante: "+cuentaSolicitante);
		
		
		Long idSolicitud = Long.parseLong(req.getParameter("idSolicitud"));
		System.out.println("idSolicitud: "+idSolicitud);
		
		String user = req.getUserPrincipal().getName();
		String action = req.getParameter("action");
		
		if(user!= null){
			
			SolicitudCambioDivisasDAO daoSolicitud = SolicitudCambioDivisasDAOImpl.getInstance();
			
			if(action.equals("Cambiar")){
				System.out.println("Cambiar");
				//Acepta solicitud
				SolicitudCambioDivisas solicitud = daoSolicitud.read(idSolicitud);
				solicitud.setEstado(2);
				daoSolicitud.Update(solicitud);
				//Datos de solicitud
				double importeMonedaOriginal = solicitud.getimporteDivisaOriginal();
				double importeMonedaCambiada = solicitud.getimporteDivisaACambiar();
				CuentaDAO daoCuenta = CuentaDAOImpl.getInstance();
				//Cuenta que solicita el cambio de divisas
				Cuenta cuentaMonedaOrigen = daoCuenta.GetCuenta(cuentaSolicitante);
				//Cuenta que acepta el cambio de divisas
				Cuenta cuentaMonedaACambiar = daoCuenta.GetCuenta(cuentaAcepta);
				//Comprueba que hay fondos en las cuenta para realizar el cambio
				if(cuentaMonedaOrigen.getSaldo(solicitud.getDivisaPredeterminada())>=importeMonedaOriginal
						&& cuentaMonedaACambiar.getSaldo(solicitud.getDivisaCambio())>=importeMonedaCambiada){
					System.out.println("Hay fondos");
					//Registra la operación en la tabla MatchingCambioDivisas
					
					//Actualiza el saldo de las cuentas de los 2 clientes
					
					//Registra la operación en la tabla Transacciones
				}else{
					System.out.println("No hay fondos");
					resp.sendRedirect("/isst_grupo07_socialex");
				}
				
			}
			//Elimina Solicitud de cambio
			if(action.equals("Cancelar")){
				System.out.println("Cancelar");
				SolicitudCambioDivisas solicitudEliminar = daoSolicitud.read(idSolicitud);
				daoSolicitud.Delete(solicitudEliminar);
				
			}
			
		}
		resp.sendRedirect("/isst_grupo07_socialex");
		}
}
