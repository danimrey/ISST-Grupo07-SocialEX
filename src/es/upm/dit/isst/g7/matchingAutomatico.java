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
import java.util.Iterator;
import java.util.List;
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
import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.MatchingCambioDivisas;
import es.upm.dit.isst.model.SolicitudCambioDivisas;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

@SuppressWarnings("serial")
public class matchingAutomatico extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fecha = format1.format(hoy.getTime());

		System.out.println("Servlet automatico");
		SolicitudCambioDivisasDAO daoSolicitudes = SolicitudCambioDivisasDAOImpl.getInstance();

		List<SolicitudCambioDivisas> todasSolicitudes = daoSolicitudes.readAll();
		List<SolicitudCambioDivisas> todasSolicitudes2 = daoSolicitudes.readAll();
		
		CuentaDAO daoCuenta = CuentaDAOImpl.getInstance();
		
		int control = 0;
		for(int i=0; i<todasSolicitudes.size();i++){
			for(int j=0; j<todasSolicitudes2.size();j++){
				if(todasSolicitudes.get(i).getEstado() == 1 && todasSolicitudes2.get(j).getEstado() == 1 && control == 0){
					//Encuentra cambios
					//Se toma el ejemplo cambio de USD a EUR para entender el codigo
					if(todasSolicitudes.get(i).getDivisaPredeterminada().equals(todasSolicitudes2.get(j).getDivisaCambio())){
						System.out.println("Cambiar "+ todasSolicitudes.get(i).getDivisaPredeterminada()+" a "+ todasSolicitudes.get(i).getDivisaCambio());
						//Cuenta que tiene USD
						Long idCuenta1 = todasSolicitudes.get(i).getCuentaSolicitante();
						Cuenta cuenta1 = daoCuenta.GetCuenta(idCuenta1);
						//Euros tras el cambio cuenta 1
						Double importeSolicitadoCuenta1 = todasSolicitudes.get(i).getimporteDivisaACambiar();
						System.out.println("importeSolicitadoCuenta1: "+importeSolicitadoCuenta1);
						//Dolares que quiere cambiar a euros la cuenta 1
						Double importeMonedaOriginalCuenta1 = todasSolicitudes.get(i).getimporteDivisaOriginal();
						System.out.println("importeMonedaOriginalCuenta1: "+importeMonedaOriginalCuenta1);
						//Cuenta que tiene EUR
						Long idCuenta2 = todasSolicitudes.get(j).getCuentaSolicitante();
						Cuenta cuenta2 = daoCuenta.GetCuenta(idCuenta2);
						//Dolares que quiere la cuenta 2
						Double importeSolicitadoCuenta2 = todasSolicitudes.get(j).getimporteDivisaACambiar();
						Double importeMonedaOriginalCuenta2 = todasSolicitudes.get(j).getimporteDivisaOriginal();
						System.out.println("importeMonedaOriginalCuenta2: "+importeMonedaOriginalCuenta2);

						//Comprobar si hay saldo en las cuentas para hacer el cambio
						//Saldo en DOL cuenta que quiere EUR mayor que el importe solicictado por la cuenta 2
						Double diferencia1 = cuenta1.getSaldo(todasSolicitudes.get(i).getDivisaPredeterminada()) - importeSolicitadoCuenta2;
						//Euros en cuenta2 menos euros solicitados
						Double diferencia2 = cuenta2.getSaldo(todasSolicitudes.get(j).getDivisaPredeterminada()) - importeSolicitadoCuenta2;
						System.out.println("Diferencia1: "+diferencia1);
						System.out.println("Aa: "+cuenta2.getSaldo(todasSolicitudes.get(j).getDivisaPredeterminada()));
						System.out.println("Diferencia2: "+diferencia2);
						//Diferencia cambio divisa 1 (USD)
						Double diferenciaCambio1 = Math.abs(importeMonedaOriginalCuenta1-importeSolicitadoCuenta2);
						//Diferencia cambio divisa 2 (EUR)
						Double diferenciaCambio2 = Math.abs(importeMonedaOriginalCuenta2-importeSolicitadoCuenta1);
						//OK
						System.out.println("diferenciaCambio1: "+diferenciaCambio1);
						System.out.println("diferenciaCambio2: "+diferenciaCambio2);
						//Hay fondos en las dos cuentas
						if(diferencia1 > 0.0 && diferencia2 > 0.0 
								&& diferenciaCambio1 <= 5.0 && diferenciaCambio2 <= 5.0) {
							System.out.println("Hay fondos. Direfencia menor o igual a 5.");
							System.out.println("Fondos cuenta 1: "+cuenta1.getSaldo(todasSolicitudes.get(i).getDivisaPredeterminada()));
							System.out.println("Importe solicitado 2: "+importeSolicitadoCuenta2);
							System.out.println("Diferencia1: "+diferencia1);
							//Hay que comprobar la diferencia entre entre las divisas que se vana  cambiar.
							//Si la diferencia es mayor a 5 no se realiza el cambio.
							//Massmoney garantiza un umbral de cambio automatico de 5 unidades.
							TransaccionDAO daoTransacciones = TransaccionDAOImpl.getInstance();
							MatchingCambioDivisasDAO daoMatching = MatchingCambioDivisasDAOImpl.getInstance();
							//Cuenta Massmoney
							//Aporta las diferencias
		
							if(daoCuenta.GetCuentabyCliente("massmoney")==null){
								ClienteDAO daoCliente = ClienteDAOImpl.getInstance();
								Cliente cliente = daoCliente.Create("MassMoney", "MassMoney", "massmoney", "España", 0);
								Cuenta cuentaMassmoney = daoCuenta.Create( "massmoney", "España", "EUR");
								cuentaMassmoney.addSaldo("EUR", 10000.0);
								cuentaMassmoney.addSaldo("USD", 10000.0);
								cuentaMassmoney.addSaldo("GBP", 10000.0);
								cuentaMassmoney.addSaldo("JPY", 10000.0);
								daoCuenta.update(cuentaMassmoney);
					
							}
							Cuenta cuentaMassmoney = daoCuenta.GetCuentabyCliente("massmoney");
							cuentaMassmoney.addSaldo(todasSolicitudes.get(i).getDivisaPredeterminada(), (-1)*diferenciaCambio1);
							cuentaMassmoney.addSaldo(todasSolicitudes.get(j).getDivisaPredeterminada(), (-1)*diferenciaCambio2);
							daoCuenta.update(cuentaMassmoney);
							//Transacciones
							Transaccion tranMassMoney1 = daoTransacciones.createTransaccion(cuentaMassmoney.getId(), fecha, todasSolicitudes.get(i).getDivisaPredeterminada(), (-1)*diferenciaCambio1, "Match automatico cambio divisas", Tipo.AJUSTE_MATCH_AUTO, "Fondo Massmoney");
							Transaccion tranMassMoney2 = daoTransacciones.createTransaccion(cuentaMassmoney.getId(), fecha, todasSolicitudes.get(j).getDivisaPredeterminada(), (-1)*diferenciaCambio2, "Match automatico cambio divisas", Tipo.AJUSTE_MATCH_AUTO, "Fondo Massmoney");
							//Cuenta1 (USD)
							cuenta1.addSaldo(todasSolicitudes.get(i).getDivisaPredeterminada(), (-1)*importeMonedaOriginalCuenta1);
							cuenta1.addSaldo(todasSolicitudes.get(i).getDivisaCambio(), importeSolicitadoCuenta1);
							daoCuenta.update(cuenta1);
							//Transacciones
							Transaccion tranCuenta1Quitar = daoTransacciones.createTransaccion(idCuenta1, fecha, todasSolicitudes.get(i).getDivisaPredeterminada(), (-1)*importeMonedaOriginalCuenta1, "Cambio divisas", Tipo.CAMBIO_DIVISAS_SACAR, "0");
							Transaccion tranCuenta1Add = daoTransacciones.createTransaccion(idCuenta1, fecha, todasSolicitudes.get(i).getDivisaCambio(), importeSolicitadoCuenta1, "Cambio divisas", Tipo.CAMBIO_DIVISAS_ADD, "0");
		
							//Cuenta2 (EUR)
							cuenta2.addSaldo(todasSolicitudes.get(j).getDivisaPredeterminada(), (-1)*importeMonedaOriginalCuenta2);
							cuenta2.addSaldo(todasSolicitudes.get(j).getDivisaCambio(), importeSolicitadoCuenta2);
							daoCuenta.update(cuenta2);
							//Transacciones
							Transaccion tranCuenta2Quitar = daoTransacciones.createTransaccion(idCuenta2, fecha, todasSolicitudes.get(j).getDivisaPredeterminada(), (-1)*importeMonedaOriginalCuenta2, "Cambio divisas", Tipo.CAMBIO_DIVISAS_SACAR, "0");
							Transaccion tranCuenta2Add = daoTransacciones.createTransaccion(idCuenta2, fecha, todasSolicitudes.get(j).getDivisaCambio(), importeSolicitadoCuenta2, "Cambio divisas", Tipo.CAMBIO_DIVISAS_ADD, "0");
							
							//Actualizacion solicitud
							SolicitudCambioDivisas sol1 = daoSolicitudes.read(todasSolicitudes.get(i).getId());
							sol1.setEstado(2);
							daoSolicitudes.Update(sol1);
							
							SolicitudCambioDivisas sol2 = daoSolicitudes.read(todasSolicitudes.get(j).getId());
							sol2.setEstado(2);
							daoSolicitudes.Update(sol2);
							
							//Math
							MatchingCambioDivisas match = daoMatching.Create(1, idCuenta1, idCuenta2, 0.0);
							//Control
							control = 1;
							
							//Enviamos la notificacion por correo, si lo tienen activado, a los usuarios de las dos cuentas
							ClienteDAO dao = ClienteDAOImpl.getInstance();
							if(dao.GetClientebyCorreo(cuenta1.getCliente()).getNotificaciones() == 1){
								try{
									Message msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
						            msg.setFrom(new InternetAddress("ISSTGrupo07SocialEX@gmail.com", "Sistema de cambio de divisas"));
						            msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(cuenta1.getCliente(), "Propietario de la cuenta"));
						            msg.setSubject("Solicitud completada");
						            msg.setText("Se ha completado una solicitud de cambio de divisas, mire su perfil para más información.");
						            Transport.send(msg);
								}catch(MessagingException e){ 
									resp.setContentType("text/plain");
						            resp.getWriter().println("Algo ha ido mal. Por favor, inténtelo otra vez.");
						        } 
							}
							if(dao.GetClientebyCorreo(cuenta2.getCliente()).getNotificaciones() == 1){
								try{
									Message msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
						            msg.setFrom(new InternetAddress("ISSTGrupo07SocialEX@gmail.com", "Sistema de cambio de divisas"));
						            msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(cuenta2.getCliente(), "Propietario de la cuenta"));
						            msg.setSubject("Solicitud completada");
						            msg.setText("Se ha completado una solicitud de cambio de divisas, mire su perfil para más información.");
						            Transport.send(msg);
								}catch(MessagingException e){ 
									resp.setContentType("text/plain");
						            resp.getWriter().println("Algo ha ido mal. Por favor, inténtelo otra vez.");
						        } 
							}
							
							
						}else{
							System.out.println("No hay fondos");
						}
						 
						System.out.println("Cuenta 1: "+idCuenta1);
						System.out.println("Cuenta 2: "+idCuenta2);
						System.out.println("BREAK");
					}
				}else{
					System.out.println("No se encuentran solicictudes compatibles");
				}
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("Servlet automatico");
	}
}
