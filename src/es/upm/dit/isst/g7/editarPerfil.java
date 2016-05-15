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
public class editarPerfil extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
         String nombre = req.getParameter("nombreNuevo");
         String pais = req.getParameter("paisNuevo");
         String user = req.getUserPrincipal().getName();
         int notificaciones;
         if(req.getParameter("notificaciones") == null){
             notificaciones = 0;
         }else{
             notificaciones = 1;
         }
         System.out.println("pais nuevo: "+pais);
             
         ClienteDAO dao = ClienteDAOImpl.getInstance();
         dao.editarCliente(user, nombre, pais, notificaciones);
        
        resp.sendRedirect("/isst_grupo07_socialex");
	}
}
