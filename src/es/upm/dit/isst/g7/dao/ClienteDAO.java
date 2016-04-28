package es.upm.dit.isst.g7.dao;

import java.util.List;

import es.upm.dit.isst.model.Cliente;

public interface ClienteDAO {
	
	//Crea un nuevo Cliente
	public Cliente Create(String nombre,String apellidos, String correo, String pais, int notificaciones);
	
	//Devuelve Cliente por nombre
	public Cliente GetClienteByNombre(String nombre);
	//Devuelve lista con todos los clientes
	public List<Cliente> GetAllClientes();
	//Devuelve cliente con correo
	public Cliente GetClientebyCorreo(String correo);
	
	public void update(Cliente cliente);
	
	//Elimina cliente
	public void Delete(String nombre);

	public void editarCliente(String correo, String nombre, String pais, int notificaciones);

}
