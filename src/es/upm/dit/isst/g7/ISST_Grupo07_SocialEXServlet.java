package es.upm.dit.isst.g7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

@SuppressWarnings("serial")
public class ISST_Grupo07_SocialEXServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		/*
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		ClienteDAO dao = ClienteDAOImpl.getInstance();
		TransaccionDAO dao2 = TransaccionDAOImpl.getInstance();
		
		//Fromatear fecha
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		
		dao2.createTransaccion("cliente1", formatted, "EUR", "EUR", 20.0, "concepto22", "Up");
		
		//dao.Create("Pepe", "pepe@pepe.com", "1234", "España");
		//dao.Create("Juan", "juan@juan.com", "1234", "España");
		//dao.AddSaldo("Juan", "EUR", 40.0);
		//dao.AddSaldo("Pepe", "EUR", 20.0);
		
		for(Cliente cliente: dao.GetAllClientes()){
			resp.getWriter().println(cliente.getNombre());
			resp.getWriter().println(cliente.getCorreo());
			resp.getWriter().println(cliente.getSaldo());
		}
		*/
		//Control de usuarios. Solo cuentas de Google
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI()); 
		String urlLinktext = "Login"; 
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService. createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout"; 
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		ClienteDAO dao = ClienteDAOImpl.getInstance();
		TransaccionDAO dao2 = TransaccionDAOImpl.getInstance();
		System.out.println("Antes de null");
		
		if(req.getUserPrincipal() != null){
			System.out.println("Despues de null");
			//Si el usuario logeado esta registardo muestra perfil
			if(dao.GetClientebyCorreo(req.getUserPrincipal().getName())!=null){
				
				System.out.println(user);
				String cliente = dao.GetClientebyCorreo(user).getNombre();
				req.getSession().setAttribute("cliente", cliente);
				
				String pais = dao.GetClientebyCorreo(user).getPais();
				req.getSession().setAttribute("pais", pais);
				
				String notifString;
				int notificaciones = dao.GetClientebyCorreo(user).getNotificaciones();
				if (notificaciones == 1){
					notifString = "Sí";
				}else{
					notifString = "No";
				}
				req.getSession().setAttribute("notificaciones", notifString);
				
				//Muestra saldo de divisas
				Double saldoEUR = dao.GetClientebyCorreo(user).getSaldo("EUR");
				req.getSession().setAttribute("saldoEUR", saldoEUR);
				
				Double saldoDOL = dao.GetClientebyCorreo(user).getSaldo("USD");
				req.getSession().setAttribute("saldoDOL", saldoDOL);
				
				Double saldoGBP = dao.GetClientebyCorreo(user).getSaldo("GBP");
				req.getSession().setAttribute("saldoGBP", saldoGBP);
				
				Double saldoJPY = dao.GetClientebyCorreo(user).getSaldo("JPY");
				req.getSession().setAttribute("saldoJPY", saldoJPY);
				
				//Cargar transacciones
				List<Transaccion> tran = dao2.getTransaccionesbyUser(user);
				req.getSession().setAttribute("transacciones", new ArrayList<Transaccion>(tran));
				//tran.get(0).getFecha();
				//Carga perfil
				System.out.println("Carga perfil");
				RequestDispatcher view = req.getRequestDispatcher("perfil.jsp") ;
				view.forward(req, resp);
			
			}else{
				//Si no esta registrado muestra formulario de nueva tarjeta
				RequestDispatcher view = req.getRequestDispatcher("solicitaTarjeta.jsp") ;
				view.forward(req, resp);
			}
		}else{
			//Si el usuario no esta logeado solo muestra la web de inicio
			RequestDispatcher view = req.getRequestDispatcher("index.jsp") ;
			view.forward(req, resp);
		}
	}
}
