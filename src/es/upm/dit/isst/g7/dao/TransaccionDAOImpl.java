package es.upm.dit.isst.g7.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.Cliente;
import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

public class TransaccionDAOImpl implements TransaccionDAO {
	private static TransaccionDAOImpl instance; 
	private TransaccionDAOImpl() {
	}
	public static TransaccionDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  TransaccionDAOImpl( ) ;
		return instance;  
	}
	@Override
	public void createTransaccion(String cliente, String fechaCliente,
			String monedaIncial, String monedaFinal, Double dinero,
			String concepto, Tipo tipo) {
		
		EntityManager em1 = EMFService.get().createEntityManager();
		Transaccion tra = new Transaccion(cliente,fechaCliente, monedaIncial, monedaFinal, dinero, concepto, tipo);
		em1.persist(tra);
		em1.close();
		
	}
	
	@Override
	public List<Transaccion> getAllTransacciones() {
		EntityManager em1 = EMFService.get().createEntityManager();
		List<Transaccion> todasTransacciones = em1.createQuery("select m from Transaccion m").getResultList();
		em1.close();
		return todasTransacciones;	
	}
	
	@Override
	public List<Transaccion> getTransaccionesbyUser(String user) {
		EntityManager em1 = EMFService.get().createEntityManager();
		List<Transaccion> todasTransacciones = new ArrayList<Transaccion>();
		try{
			todasTransacciones = em1.createQuery("select t from Transaccion t where t.cliente = :cliente").setParameter("cliente", user).getResultList();
			
		}catch(Exception e){
			System.out.println("Error en getTransaccionesbyUser");
		}
		em1.close();
		return todasTransacciones;	
	}

}
