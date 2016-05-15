package es.upm.dit.isst.g7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAO;
import es.upm.dit.isst.g7.dao.SolicitudCambioDivisasDAOImpl;
import es.upm.dit.isst.g7.dao.TarjetaDAO;
import es.upm.dit.isst.g7.dao.TarjetaDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.SolicitudCambioDivisas;
import es.upm.dit.isst.model.Tarjeta;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

@SuppressWarnings("serial")
public class ISST_Grupo07_SocialEXServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		/*ClienteDAO dao1 = ClienteDAOImpl.getInstance();
		TransaccionDAO dao3 = TransaccionDAOImpl.getInstance();
		//CuentaDAO dao2 = new CuentaDAOImpl();
		System.out.println("size:" +dao1.GetAllClientes().size());
		Tipo tipo = null;
		dao3.createTransaccion("a", "a", "a", "a", 0.0, "a", tipo.CAMBIO_DIVISAS);
		System.out.println("t: "+dao3.getAllTransacciones().size());
		
		CuentaDAO dao2 = CuentaDAOImpl.getInstance();
		dao2.Create("a@a.com", "España", "Euro", "123456", "pepe", "1/1/2020");
		System.out.println("size:" +dao2.GetAllCuentas().size());
		//dao1.Create("b@a.com", "b@a.com", "España", 0);
		/*Tipo tipo = null;
		dao3.createTransaccion("a", "a", "a", "a", 0.0, "a", tipo.CAMBIO_DIVISAS);
		System.out.println("t: "+dao3.getAllTransacciones().size());
		dao2.Create("a@a.com", "España", "Euro", "123456");
		System.out.println("size:" +dao2.GetAllCuentas().size());
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
				
				CuentaDAO daoCuentas = CuentaDAOImpl.getInstance();
				req.getSession().setAttribute("cuenta", daoCuentas.GetCuentabyCliente(user));
				
				//Cuenta
				req.getSession().setAttribute("cuenta", daoCuentas.GetCuentabyCliente(user));
				System.out.println("divisa: "+daoCuentas.GetCuentabyCliente(user).getDivisaPredeterminada());
				//Muestra saldo de divisas
				Double saldoEUR = daoCuentas.GetCuentabyCliente(user).getSaldo("EUR");
				req.getSession().setAttribute("saldoEUR", saldoEUR);
				
				Double saldoDOL = daoCuentas.GetCuentabyCliente(user).getSaldo("USD");
				req.getSession().setAttribute("saldoDOL", saldoDOL);
				
				Double saldoGBP = daoCuentas.GetCuentabyCliente(user).getSaldo("GBP");
				req.getSession().setAttribute("saldoGBP", saldoGBP);
				
				Double saldoJPY = daoCuentas.GetCuentabyCliente(user).getSaldo("JPY");
				req.getSession().setAttribute("saldoJPY", saldoJPY);
				
				
				//Tarjetas
				TarjetaDAO daoTarjetas = TarjetaDAOImpl.getInstance();
				List<Tarjeta> todasTarjetas = (List<Tarjeta>) daoTarjetas.getTodasTarjetas();
				List<Tarjeta> numerosTarjeta = new ArrayList<Tarjeta>();
				
				//Obten numero de tarjetas tarjetas
				List<Long> listaTarjetas = daoCuentas.GetCuentabyCliente(user).getTarjetas();
				System.out.println("Numero tarjetas: "+listaTarjetas.size());
				System.out.println("Numero tarjetas cliente: "+numerosTarjeta.size());
				for (int i = 0; i < listaTarjetas.size(); i++) {
					numerosTarjeta.add(daoTarjetas.getTarjeta(listaTarjetas.get(i)));
				}
				System.out.println("Numero tarjetas cliente: "+numerosTarjeta.size());
				req.getSession().setAttribute("tarjetas", new ArrayList<Tarjeta>(numerosTarjeta));
				
				//Cargar transacciones
				List<Transaccion> tran = dao2.getTransaccionesbyCuenta(daoCuentas.GetCuentabyCliente(user).getNumeroCuenta());
				System.out.println("tran: "+tran.size());
				System.out.println("Numero de cuenta: "+daoCuentas.GetCuentabyCliente(user).getNumeroCuenta());
				req.getSession().setAttribute("transacciones", new ArrayList<Transaccion>(tran));
				
				//Solicitudes de cambio
				SolicitudCambioDivisasDAO daoSolicitudes = SolicitudCambioDivisasDAOImpl.getInstance();
				List<SolicitudCambioDivisas> solicitudesCambio = daoSolicitudes.readCuenta(daoCuentas.GetCuentabyCliente(user).getId());
				req.getSession().setAttribute("solicitudesCambio", new ArrayList<SolicitudCambioDivisas>(solicitudesCambio)); 
				//Todas las solicitudes de cambio
				List<SolicitudCambioDivisas> todasSolicitudes = daoSolicitudes.readAll();
				req.getSession().setAttribute("todasSolicitudes", new ArrayList<SolicitudCambioDivisas>(todasSolicitudes)); 

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

	private ClienteDAO ClienteDAOImpl() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
