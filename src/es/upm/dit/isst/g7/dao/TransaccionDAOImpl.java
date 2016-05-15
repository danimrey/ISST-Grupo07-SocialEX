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
	public Transaccion createTransaccion(Long cuentaAsociada,String fechaCliente, String divisa,
			Double importe, String concepto, Tipo tipo, String numeroTarjeta) {
		
		EntityManager em1 = EMFService.get().createEntityManager();
		Transaccion tra = new Transaccion(cuentaAsociada, fechaCliente, divisa,
				importe, concepto, tipo, numeroTarjeta);
		em1.persist(tra);
		em1.close();
		return tra;
		
	}
	
	@Override
	public List<Transaccion> getAllTransacciones() {
		EntityManager em1 = EMFService.get().createEntityManager();
		List<Transaccion> todasTransacciones = em1.createQuery("select m from Transaccion m").getResultList();
		em1.close();
		return todasTransacciones;	
	}
	
	@Override
	public List<Transaccion> getTransaccionesbyCuenta(Long cuentaAsociada) {
		EntityManager em1 = EMFService.get().createEntityManager();
		List<Transaccion> todasTransacciones = new ArrayList<Transaccion>();
		try{
			todasTransacciones = (List<Transaccion>) em1.createQuery("select t from Transaccion t where t.cuentaAsociada = :cuentaAsociada").setParameter("cuentaAsociada", cuentaAsociada).getResultList();
			System.out.println("test: "+todasTransacciones.size());
			
		}catch(Exception e){
			System.out.println("Error en getTransaccionesbyUser");
		}
		em1.close();
		return todasTransacciones;	
	}
	@Override
	public void update(Transaccion transaccion) {
		// TODO Auto-generated method stub
		EntityManager em1 = EMFService.get().createEntityManager();
		em1.merge(transaccion);
		em1.close();
		
	}

}
