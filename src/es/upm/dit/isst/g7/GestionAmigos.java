package es.upm.dit.isst.g7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Cuenta;

@SuppressWarnings("serial")
public class GestionAmigos extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
				
				//Amigos
				ClienteDAO daoCliente = ClienteDAOImpl.getInstance();
				Cliente cliente = daoCliente.GetClientebyCorreo(user);
				List<String> amigos = cliente.getAmigos();
				req.getSession().setAttribute("amigos", new ArrayList<String>(amigos));
				
				//Solicitudes pendientes
				List<String> solicitudes = cliente.getSolicitudesPendientes();
				req.getSession().setAttribute("solicitudes", solicitudes);	
				
				//Carga vista
				RequestDispatcher view = req.getRequestDispatcher("gestionAmigos.jsp") ;
				view.forward(req, resp);
			}else{
				req.getSession().setAttribute("user", user);
				req.getSession().setAttribute("url", url);
				req.getSession().setAttribute("urlLinktext", urlLinktext);
				resp.sendRedirect("/isst_grupo07_socialex");
			}
			
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	}
}