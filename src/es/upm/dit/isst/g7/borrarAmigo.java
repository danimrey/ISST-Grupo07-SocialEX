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
public class borrarAmigo extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String aBorrar = req.getParameter("aBorrar");
		String user = req.getUserPrincipal().getName();

		ClienteDAO dao = ClienteDAOImpl.getInstance();
		Cliente usuario = dao.GetClientebyCorreo(user);
		Cliente usuarioABorrar = dao.GetClientebyCorreo(aBorrar);

		List<String> amigos1 = usuario.getAmigos();
		List<String> amigos2 = usuarioABorrar.getAmigos();

		for(int i=0; i<amigos1.size(); i++){
			String tempName = amigos1.get(i);
			System.out.println(tempName);
            if(tempName.equals(aBorrar)){
                amigos1.remove(i);
                System.out.println("se tiene que haber borrado");
            }
		}
		for(int j=0; j<amigos2.size(); j++){
			String tempName = amigos2.get(j);
			System.out.println(tempName);
            if(tempName.equals(user)){
                amigos2.remove(j);
                System.out.println("se tiene que haber borrado");
            }
		}

		usuario.setAmigos(amigos1);
		usuarioABorrar.setAmigos(amigos2);

		dao.update(usuario);
		dao.update(usuarioABorrar);

		resp.sendRedirect("/gestionAmigos");
	}
}
