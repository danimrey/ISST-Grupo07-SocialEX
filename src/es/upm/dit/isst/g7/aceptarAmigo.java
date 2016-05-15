package es.upm.dit.isst.g7;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.model.Cliente;

@SuppressWarnings("serial")
public class aceptarAmigo extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String aceptar = req.getParameter("aceptarA");
		String user = req.getUserPrincipal().getName();

		ClienteDAO dao = ClienteDAOImpl.getInstance();
		Cliente usuario = dao.GetClientebyCorreo(user);
		Cliente usuarioAceptar = dao.GetClientebyCorreo(aceptar);

		List<String> amigos1 = usuario.getAmigos();
		List<String> amigos2 = usuarioAceptar.getAmigos();
		List<String> listaSolicitudes = usuario.getSolicitudesPendientes();
		
		if(!(amigos1.contains(aceptar)) || !(amigos2.contains(user))){
			amigos1.add(aceptar);
			listaSolicitudes.remove(aceptar);
			amigos2.add(user);

			usuario.setAmigos(amigos1);
			usuarioAceptar.setAmigos(amigos2);
			usuario.setSolicitudesPendientes(listaSolicitudes);

			dao.update(usuario);
			dao.update(usuarioAceptar);
			if(dao.GetClientebyCorreo(aceptar).getNotificaciones() == 1){
				try{
					Message msg = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
	            	msg.setFrom(new InternetAddress("ISSTGrupo07SocialEX@gmail.com", "Sistema de cambio de divisas"));
	            	msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(aceptar, "Nuevo amigo"));
	            	msg.setSubject("Nuevo amigo");
	            	msg.setText("Ha sido añadido a la lista de amigos de " + user);
	            	Transport.send(msg);
				}catch(MessagingException e){ 
					resp.setContentType("text/plain");
					resp.getWriter().println("Algo ha ido mal. Por favor, inténtelo otra vez.");
				}
			}
		}		
		resp.sendRedirect("/gestionAmigos");
	}
}
