package es.upm.dit.isst.g7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.g7.dao.TransaccionDAO;
import es.upm.dit.isst.g7.dao.TransaccionDAOImpl;
import es.upm.dit.isst.model.Cliente;

@SuppressWarnings("serial")
public class nuevaTarjeta extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
         String nombre = req.getParameter("nombre");
         String apellidos = req.getParameter("apellidos");
         String pais = req.getParameter("pais");
         int notificaciones;
         if(req.getParameter("notificaciones") == null){
             notificaciones = 0;
         }else{
             notificaciones = 1;
         }
         String user = req.getUserPrincipal().getName();
         
         if(nombre.isEmpty() || apellidos.isEmpty() || pais.isEmpty()){
        	 System.out.println("Campos vacios. Vuelve a la página principal");
         }
         else{
        	 //Crea un nuevo cliente
        	 ClienteDAO dao = ClienteDAOImpl.getInstance();
        	 dao.Create(nombre+" "+apellidos, user, pais, notificaciones);
         }
         resp.sendRedirect("/isst_grupo07_socialex");
	}
}
