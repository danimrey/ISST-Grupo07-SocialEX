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
public class rechazarAmigo extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String rechazar = req.getParameter("rechazarA");
		String user = req.getUserPrincipal().getName();

		ClienteDAO dao = ClienteDAOImpl.getInstance();
		Cliente usuario = dao.GetClientebyCorreo(user);

		List<String> listaSolicitudes = usuario.getSolicitudesPendientes();
		
		for(int i=0; i<listaSolicitudes.size(); i++){
			String tempName = listaSolicitudes.get(i);
            if(tempName.equals(rechazar)){
            	System.out.println("se tiene que haber rechazado");
                listaSolicitudes.remove(i);
            }
		}
		
		usuario.setSolicitudesPendientes(listaSolicitudes);

		dao.update(usuario);
		
		resp.sendRedirect("/gestionAmigos");
	}
}
