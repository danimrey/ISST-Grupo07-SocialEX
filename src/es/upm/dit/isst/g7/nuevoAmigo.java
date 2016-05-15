package es.upm.dit.isst.g7;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.g7.dao.ClienteDAO;
import es.upm.dit.isst.g7.dao.ClienteDAOImpl;
import es.upm.dit.isst.model.Cliente;

@SuppressWarnings("serial")
public class nuevoAmigo extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String correo = req.getParameter("correo");
		String user = req.getUserPrincipal().getName();

		ClienteDAO dao = ClienteDAOImpl.getInstance();
		Cliente usuario;
		Cliente nuevoAmigo;
		if(dao.GetClientebyCorreo(user) != null){
			System.out.println(correo);
			System.out.println(user);
			if((dao.GetClientebyCorreo(correo) != null) && !(correo.equals(user))){
				usuario = dao.GetClientebyCorreo(user);
				nuevoAmigo = dao.GetClientebyCorreo(correo);

				List<String> amigos1 = usuario.getAmigos();
				List<String> amigos2 = nuevoAmigo.getAmigos();
				List<String> solicitudesPendientes = nuevoAmigo.getSolicitudesPendientes();
				
				if(solicitudesPendientes.contains(user) || amigos1.contains(correo) || amigos2.contains(user)
						|| usuario.getSolicitudesPendientes().contains(correo)){
					//no hacemos nada porque ya se ha añadido al usuario o esta en la lista de amigos de alguno de los dos
				}else{
					solicitudesPendientes.add(user);
					nuevoAmigo.setSolicitudesPendientes(solicitudesPendientes);
					dao.update(nuevoAmigo);
				}
			}
		}
		
		
		resp.sendRedirect("/gestionAmigos");
	}
}