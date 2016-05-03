package es.upm.dit.isst.g7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.CuentaDAO;
import es.upm.dit.isst.g7.dao.CuentaDAOImpl;
import es.upm.dit.isst.g7.dao.TarjetaDAO;
import es.upm.dit.isst.g7.dao.TarjetaDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Cuenta;
import es.upm.dit.isst.model.Tarjeta;

@SuppressWarnings("serial")
public class nuevaTarjeta extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//Cliente
         String nombre = req.getParameter("nombre");
         String apellidos = req.getParameter("apellidos");
         String pais = req.getParameter("pais");
         int notificaciones;
         //Cuenta
         String titularTarjeta = req.getParameter("titularTarjeta");
         String tarjeta = req.getParameter("tarjeta");
         String caducidadTarjeta = req.getParameter("caducidadTarjeta");
         String divisaTarjeta = req.getParameter("divisaTarjeta");
         String paisTarjeta = req.getParameter("paisTarjeta");
         String direccion = req.getParameter("direccion");
         String ciudad = req.getParameter("ciudad");
         String provincia = req.getParameter("provincia");
         String codigoPostal = req.getParameter("codigoPostal");
         String codigoSecreto = req.getParameter("codigoSecreto");
       
         if(req.getParameter("notificaciones") == null){
             notificaciones = 0;
         }else{
             notificaciones = 1;
         }
         String user = req.getUserPrincipal().getName();
         
         if(nombre.isEmpty() || apellidos.isEmpty() || pais.isEmpty() || titularTarjeta.isEmpty() || tarjeta.isEmpty()
        		 || caducidadTarjeta.isEmpty() || divisaTarjeta.isEmpty() || paisTarjeta.isEmpty()){
        	 System.out.println("Campos vacios. Vuelve a la página principal");
         }
         else{
        	 //Crea un nuevo cliente
        	 ClienteDAO dao = ClienteDAOImpl.getInstance();
        	 Cliente cliente = dao.Create(nombre,apellidos, user, pais, notificaciones);
        	 
        	 //Nueva tarjeta
        	 TarjetaDAO daoTarjeta = TarjetaDAOImpl.getInstance();
        	 Tarjeta nuevaTarjeta = daoTarjeta.Create(titularTarjeta, direccion, ciudad, provincia, pais, codigoPostal, tarjeta, caducidadTarjeta, codigoSecreto);
        	 
        	//Crea una nueva cuenta
        	 CuentaDAO dao2 = CuentaDAOImpl.getInstance();
        	 Cuenta cuenta = dao2.Create(user, paisTarjeta, divisaTarjeta);
        	 //Añade cuenta al cliente
        	 cliente.setCuenta(cuenta.getNumeroCuenta());
        	 //Añade tarjeta a la cuenta
        	 cuenta.addTarjeta(nuevaTarjeta.getId());
        	 dao2.update(cuenta);
        	 dao.update(cliente);
        	 
        	 
         }
         resp.sendRedirect("/isst_grupo07_socialex");
	}
}
