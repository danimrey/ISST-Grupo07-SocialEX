package es.upm.dit.isst.g7.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.Cuenta;

public class CuentaDAOImpl implements CuentaDAO {
	private static CuentaDAOImpl instance; 
	private CuentaDAOImpl() {
	}
	public static CuentaDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  CuentaDAOImpl( ) ;
		return instance;  
	}
	@Override
	public Cuenta Create(String cliente, String paisCuenta,
		String divisaPredeterminada, String tarjetaAsociada, String titularTarjeta, String caducidadTarjeta) {
		EntityManager em2 = EMFService.get().createEntityManager();
		Cuenta cuenta = new Cuenta(cliente, paisCuenta, divisaPredeterminada, tarjetaAsociada, titularTarjeta, caducidadTarjeta);
		
		em2.persist(cuenta);
		em2.close();
		return cuenta;
	}

	@Override
	public Cuenta GetCuenta(long id) {
		EntityManager em2 = EMFService.get().createEntityManager();
		Cuenta cuenta = null;
		//Intenta buscar TFG por nombre de autor.
		//Si no lo encuentra devuelve null.
		try{
			cuenta = (Cuenta) em2.createQuery("select t from Cuenta t where t.id = :id").setParameter("id", id).getSingleResult();
			System.out.println("TEST");
		}
		catch (Exception e) { 
			System.out.println("No encuentra cuenta por numero");  
		}
		em2.close();
		return cuenta;
	}

	@Override
	public List<Cuenta> GetAllCuentas() {
		EntityManager em2 = EMFService.get().createEntityManager();
		System.out.println("Todas las cuentas 1");
		List<Cuenta> todasCuentas = new ArrayList<Cuenta>();
		try {
			todasCuentas = em2.createQuery("select m from Cuenta m").getResultList();
			System.out.println(todasCuentas);
		} catch (Exception e) {
			System.out.println("No encuentra cuentas");
			System.out.println(e);
			//todasCuentas = new ArrayList<Cuenta>();
		}
		em2.close();
		return todasCuentas;
	}

	@Override
	public List<Cuenta> GetCuentabyCliente(String cliente) {
		EntityManager em = EMFService.get().createEntityManager();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		//Intenta buscar TFG por nombre de autor.
		//Si no lo encuentra devuelve null.
		try{
			cuentas= (List<Cuenta>) em.createQuery("select t from Cuenta t where t.cliente = :cliente").setParameter("cliente", cliente).getResultList();
		}
		catch (Exception e) { 
			System.out.println("No encuentra cuenta por correo cliente");  
		}
		em.close();
		return cuentas;
	}

	@Override
	public void update(Cuenta cuenta) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(cuenta);
		em.close();
	}

	@Override
	public void Delete(Cuenta cuenta) {
		EntityManager em = EMFService.get().createEntityManager();
		System.out.println(cuenta);
		Cuenta cuenta1= (Cuenta) em.createQuery("select t from Cuenta t where t.numeroCuenta = :numeroCuenta").setParameter("numeroCuenta", cuenta.getNumeroCuenta()).getSingleResult();
		em.remove(cuenta1);
		em.close();

	}

}
