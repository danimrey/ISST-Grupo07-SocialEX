package es.upm.dit.isst.g7.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.Cliente;


public class ClienteDAOImpl implements ClienteDAO {
	
	private static ClienteDAOImpl instance; 
	private ClienteDAOImpl() {
	}
	public static ClienteDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  ClienteDAOImpl( ) ;
		return instance;  
	}
	
	@Override
	public Cliente Create(String nombre,String apellidos, String correo, String pais, int notificaciones) {
		EntityManager em = EMFService.get().createEntityManager();
		Cliente cliente = new Cliente(nombre,apellidos, correo, pais, notificaciones);
		em.persist(cliente);
		em.close();
		return cliente;
		// TODO Auto-generated method stub
	}

	@Override
	public Cliente GetClienteByNombre(String nombre) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Cliente cliente = null;
		//Intenta buscar TFG por nombre de autor.
		//Si no lo encuentra devuelve null.
		try{
			cliente= (Cliente) em.createQuery("select t from Cliente t where t.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
		}
		catch (Exception e) { 
			System.out.println("No encuentra cliente por nombre");  
		}
		em.close();
		return cliente;
	}

	@Override
	public List<Cliente> GetAllClientes() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		List<Cliente> todosClientes = em.createQuery("select m from Cliente m").getResultList();
		em.close();
		return todosClientes;
	}

	@Override
	public Cliente GetClientebyCorreo(String correo) {
		// TODO Auto-generated method stub
				EntityManager em = EMFService.get().createEntityManager();
				Cliente cliente = null;
				//Intenta buscar TFG por nombre de autor.
				//Si no lo encuentra devuelve null.
				try{
					cliente= (Cliente) em.createQuery("select t from Cliente t where t.correo = :correo").setParameter("correo", correo).getSingleResult();
				}
				catch (Exception e) { 
					System.out.println("No encuentra cliente por correo");  
				}
				em.close();
				return cliente;
	}

	@Override
	public void editarCliente(String correo, String nombre, String pais, int notificaciones) {
		EntityManager em = EMFService.get().createEntityManager();
		Cliente cliente = null;
		try{
			cliente= (Cliente) em.createQuery("select t from Cliente t where t.correo = :correo").setParameter("correo", correo).getSingleResult();
			if(!nombre.isEmpty()){
				cliente.setNombre(nombre);
			}
			if(!pais.isEmpty()){
				cliente.setPais(pais);
			}
			cliente.setNotificaciones(notificaciones);
			em.merge(cliente);
		}
		catch (Exception e) { 
			System.out.println("No encuentra cliente para saldo");  
		}
		em.close();
		// TODO Auto-generated method stub

	}

	@Override
	public void Delete(String nombre) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		Cliente cliente = null;
		//Intenta buscar TFG por nombre de autor.
		//Si no lo encuentra devuelve null.
		try{
			cliente= (Cliente) em.createQuery("select t from Cliente t where t.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
			em.remove(cliente);
		}
		catch (Exception e) { 
			System.out.println("No encuentra cliente para borrar");  
		}
		em.close();

	}
	@Override
	public void update(Cliente cliente) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cliente);
		em.close();	
	}
}
