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

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties; 

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAO;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.SolicitudCambioDivisas;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

@SuppressWarnings("serial")
public class mercadoDivisas extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

			UserService userService = UserServiceFactory.getUserService();
			String url = userService.createLoginURL(req.getRequestURI()); 
			String urlLinktext = "Login"; 
			String user = "";
				
				if(req.getUserPrincipal() != null){
					user = req.getUserPrincipal().getName();
					url = userService.createLogoutURL(req.getRequestURI());
					urlLinktext = "Logout"; 
					
					req.getSession().setAttribute("user", user);
					req.getSession().setAttribute("url", url);
					req.getSession().setAttribute("urlLinktext", urlLinktext);
					
					//Cuenta
					CuentaDAO daoCuenta = CuentaDAOImpl.getInstance();
					Cuenta cuenta = daoCuenta.GetCuentabyCliente(user);
					req.getSession().setAttribute("cuenta", cuenta);
					
					//Amigos
					ClienteDAO daoCliente = ClienteDAOImpl.getInstance();
					Cliente cliente = daoCliente.GetClientebyCorreo(user);
					List<String> amigos = cliente.getAmigos();
					req.getSession().setAttribute("amigos", new ArrayList<String>(amigos));
					
					//Todas las solicitudes de cambio de amigos
					SolicitudCambioDivisasDAO daoSolicitudes = SolicitudCambioDivisasDAOImpl.getInstance();
					List<SolicitudCambioDivisas> todasSolicitudes = new ArrayList<SolicitudCambioDivisas>();
					for(int i=0; i<amigos.size(); i++){
						Cuenta unaCuentaDeAmigo = daoCuenta.GetCuentabyCliente(amigos.get(i));
						List<SolicitudCambioDivisas> todasSolicitudesUnAmigo = daoSolicitudes.readCuenta(unaCuentaDeAmigo.getId());
						for(int j=0; j<todasSolicitudesUnAmigo.size(); j++){
							todasSolicitudes.add(todasSolicitudesUnAmigo.get(j));
						}
					}
					req.getSession().setAttribute("todasSolicitudes", new ArrayList<SolicitudCambioDivisas>(todasSolicitudes));
					//Solicitudes euros
					List<SolicitudCambioDivisas> solicitudesEuros = new ArrayList<SolicitudCambioDivisas>();
					for(int i=0; i<todasSolicitudes.size(); i++){
						if(Objects.equals(todasSolicitudes.get(i).getDivisaCambio(),new String("EUR"))){ 
							solicitudesEuros.add(todasSolicitudes.get(i));
						}
					}
					req.getSession().setAttribute("solicitudesEuros", new ArrayList<SolicitudCambioDivisas>(solicitudesEuros));
					//Solicitudes dolares
					List<SolicitudCambioDivisas> solicitudesDolares = new ArrayList<SolicitudCambioDivisas>();
					for(int i=0; i<todasSolicitudes.size(); i++){
						if(Objects.equals(todasSolicitudes.get(i).getDivisaCambio(),new String("USD"))){ 
							solicitudesDolares.add(todasSolicitudes.get(i));
						}
					}
					req.getSession().setAttribute("solicitudesDolares", new ArrayList<SolicitudCambioDivisas>(solicitudesDolares));
					//Solicitudes libras
					List<SolicitudCambioDivisas> solicitudesLibras = new ArrayList<SolicitudCambioDivisas>();
					for(int i=0; i<todasSolicitudes.size(); i++){
						if(Objects.equals(todasSolicitudes.get(i).getDivisaCambio(),new String("GBP"))){ 
							solicitudesLibras.add(todasSolicitudes.get(i));
						}
					}
					req.getSession().setAttribute("solicitudesLibras", new ArrayList<SolicitudCambioDivisas>(solicitudesLibras));
					//Solicitudes yenes
					List<SolicitudCambioDivisas> solicitudesYenes = new ArrayList<SolicitudCambioDivisas>();
					for(int i=0; i<todasSolicitudes.size(); i++){
						if(Objects.equals(todasSolicitudes.get(i).getDivisaCambio(),new String("JPY"))){ 
							solicitudesYenes.add(todasSolicitudes.get(i));
						}
					}
					req.getSession().setAttribute("solicitudesYenes", new ArrayList<SolicitudCambioDivisas>(solicitudesYenes));

					
					//Carga vista
					RequestDispatcher view = req.getRequestDispatcher("mercadoDivisas.jsp") ;
					view.forward(req, resp);
				}else{
					req.getSession().setAttribute("user", user);
					req.getSession().setAttribute("url", url);
					req.getSession().setAttribute("urlLinktext", urlLinktext);
					resp.sendRedirect("/isst_grupo07_socialex");
				}
				 

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
	}
}
