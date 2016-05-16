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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties; 

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.g7.dao.MatchingCambioDivisasDAO;
import es.upm.dit.isst.g7.dao.MatchingCambioDivisasDAOImpl;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAO;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.MatchingCambioDivisas;
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
				//Datos de solicitud
				double importeMonedaOriginal = solicitud.getimporteDivisaOriginal();
				double importeMonedaCambiada = solicitud.getimporteDivisaACambiar();
				String divisaOriginal = solicitud.getDivisaPredeterminada();
				String divisaCambio = solicitud.getDivisaCambio();
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
					MatchingCambioDivisasDAO daoMatching = MatchingCambioDivisasDAOImpl.getInstance();
					//Crea una solicitud para el cliente 2. Tarjeta es 0 porque el cambio se hace con el saldo de la cuenta.
					SolicitudCambioDivisas nuevaSol = daoSolicitud.Create(2, importeMonedaCambiada, divisaOriginal, divisaCambio, cuentaAcepta, 2, (long) 0, importeMonedaOriginal);
					//Comisión
					YahooCurrencyConverter comision1 = new YahooCurrencyConverter();
					Double comisionCuenta1 = importeMonedaOriginal*0.01;
					Double comisionCuenta2 = importeMonedaCambiada*0.01;
					Double comisionFinal = comisionCuenta1*(comision1.convert(divisaOriginal, "EUR"))+comisionCuenta2*(comision1.convert(divisaCambio, "EUR"));
					MatchingCambioDivisas matching = daoMatching.Create(1, idSolicitud, nuevaSol.getId(), comisionFinal);
					//Actualiza el saldo de las cuentas de los 2 clientes
					//Cuenta 1
					cuentaMonedaOrigen.addSaldo(divisaOriginal, importeMonedaOriginal*(-1));
					cuentaMonedaOrigen.addSaldo(divisaOriginal, comisionCuenta1*(-1));
					cuentaMonedaOrigen.addSaldo(divisaCambio, importeMonedaCambiada);
					daoCuenta.update(cuentaMonedaOrigen);
					//Cuenta 2
					cuentaMonedaACambiar.addSaldo(divisaOriginal, importeMonedaOriginal);
					cuentaMonedaACambiar.addSaldo(divisaCambio, importeMonedaCambiada*(-1));
					cuentaMonedaACambiar.addSaldo(divisaCambio, comisionCuenta2*(-1));
					daoCuenta.update(cuentaMonedaACambiar);

					//Registra la operación en la tabla Transacciones
					TransaccionDAO daoTransaccion = TransaccionDAOImpl.getInstance();
					//Fecha
					Calendar hoy = Calendar.getInstance();
					SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					String formatted = format1.format(hoy.getTime());
					//
					Transaccion trancuentaMonedaOrigenAddSaldo = daoTransaccion.createTransaccion(cuentaSolicitante, formatted, divisaOriginal, importeMonedaOriginal*(-1), "Cambio de divisas", Tipo.CAMBIO_DIVISAS_SACAR, "Cuenta");
					Transaccion trancuentaMonedaOrigenComision1 = daoTransaccion.createTransaccion(cuentaSolicitante, formatted, divisaOriginal, comisionCuenta1*(-1), "Cambio de divisas - Comisión", Tipo.CAMBIO_DIVISAS_COMISION, "Cuenta");
					Transaccion trancuentaMonedaOrigenQuitaSaldo = daoTransaccion.createTransaccion(cuentaSolicitante, formatted, divisaCambio, importeMonedaCambiada, "Cambio de divisas", Tipo.CAMBIO_DIVISAS_ADD, "Cuenta");
					daoTransaccion.update(trancuentaMonedaOrigenComision1);
					daoTransaccion.update(trancuentaMonedaOrigenQuitaSaldo);
					daoTransaccion.update(trancuentaMonedaOrigenAddSaldo);
					//
					Transaccion trancuentaMonedaACambiarAddSaldo = daoTransaccion.createTransaccion(cuentaAcepta, formatted, divisaOriginal, importeMonedaOriginal, "Cambio de divisas", Tipo.CAMBIO_DIVISAS_ADD, "Cuenta");
					Transaccion trancuentaMonedaACambiarQuitaSaldo = daoTransaccion.createTransaccion(cuentaAcepta, formatted, divisaCambio, importeMonedaCambiada*(-1), "Cambio de divisas", Tipo.CAMBIO_DIVISAS_SACAR, "Cuenta");
					Transaccion trancuentaMonedaACambiarComision2 = daoTransaccion.createTransaccion(cuentaAcepta, formatted, divisaCambio, comisionCuenta2*(-1), "Cambio de divisas - Comision", Tipo.CAMBIO_DIVISAS_COMISION, "Cuenta");
					daoTransaccion.update(trancuentaMonedaACambiarComision2);
					daoTransaccion.update(trancuentaMonedaACambiarAddSaldo);
					daoTransaccion.update(trancuentaMonedaACambiarQuitaSaldo);
					
					//Actualiza estado
					solicitud.setEstado(2);
					daoSolicitud.Update(solicitud);
					
					//Mensaje
					ClienteDAO dao = ClienteDAOImpl.getInstance();
					if(dao.GetClientebyCorreo(cuentaMonedaOrigen.getCliente()).getNotificaciones() == 1){
						try{
							Message msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
			            	msg.setFrom(new InternetAddress("ISSTGrupo07SocialEX@gmail.com", "Sistema de cambio de divisas"));
			            	msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(cuentaMonedaOrigen.getCliente(), "Amigo"));
			            	msg.setSubject("Cambio realizado");
			            	msg.setText("Se ha realizado el cambio de divisas de " + importeMonedaOriginal + 
			            			" " + divisaOriginal + " a " + importeMonedaCambiada + " " + divisaCambio + " con su amigo " + cuentaMonedaACambiar.getCliente());
			            	Transport.send(msg);
						}catch(MessagingException e){ 
							resp.setContentType("text/plain");
							resp.getWriter().println("Algo ha ido mal. Por favor, inténtelo otra vez.");
						}
					}
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
