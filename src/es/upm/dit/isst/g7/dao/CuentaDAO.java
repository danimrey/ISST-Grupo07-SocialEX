package es.upm.dit.isst.g7.dao;

import java.util.List;

import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Cuenta;

public interface CuentaDAO {
	
	public Cuenta Create(String cliente, String paisCuenta,
			String divisaPredeterminada);
	//Devuelve cuenta por nombre
	public Cuenta GetCuenta(long id);
	//Devuelve lista con todos las cuenta
	public List<Cuenta> GetAllCuentas();
	//Devuelve cuenta con correo
	public Cuenta GetCuentabyCliente(String cliente);
	
	//Actualiza cuenta
	public void update(Cuenta cuenta);
	
	//Elimina cuenta
	public void Delete(Cuenta cuenta);

}
