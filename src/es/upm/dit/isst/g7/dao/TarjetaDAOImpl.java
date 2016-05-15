package es.upm.dit.isst.g7.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.Tarjeta;

public class TarjetaDAOImpl implements TarjetaDAO {
	private static TarjetaDAOImpl instance; 
	private TarjetaDAOImpl() {
	}
	public static TarjetaDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  TarjetaDAOImpl( ) ;
		return instance;  
	}
	@Override
	public Tarjeta Create(String titularTarjeta, String direccion, String ciudad,
			String provincia, String pais, String codigoPostal,
			String numeroTarjeta, String caducidadTarjeta, String codigoSecreto) {
		EntityManager em = EMFService.get().createEntityManager();
		Tarjeta tarjeta = new Tarjeta(titularTarjeta, direccion, ciudad, provincia, pais, codigoPostal, numeroTarjeta, caducidadTarjeta, codigoSecreto);
		em.persist(tarjeta);
		em.close();
		return tarjeta;
	}

	@Override
	public List<Tarjeta> getTodasTarjetas() {
		EntityManager em = EMFService.get().createEntityManager();
		List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
		try{
			tarjetas = em.createQuery("select m from Tarjeta m").getResultList();
		}catch(Exception e){
			tarjetas = new ArrayList<Tarjeta>();
		}
		em.close();
		return tarjetas;
	}

	@Override
	public Tarjeta getTarjeta(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Tarjeta t = null;
		try{
			Tarjeta tarjeta = em.find(Tarjeta.class, id);
			t = tarjeta;
		}finally{
			em.close();
		}
		return t;
	}

	@Override
	public Tarjeta getTarjetaNumero(String numero) {
		EntityManager em = EMFService.get().createEntityManager();
		Tarjeta t = null;
		try{
			Tarjeta tarjeta = (Tarjeta) em.createQuery("select m from Tarjeta where m.numeroTarjeta = : numeroTarjeta").setParameter("numeroTarjeta", numero).getSingleResult();
			t = tarjeta;
		}finally{
			em.close();
		}
		return t;
	}

	@Override
	public void update(Tarjeta tarjeta) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(tarjeta);
		em.close();

	}

	@Override
	public void Delete(Tarjeta tarjeta) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			Tarjeta t = em.find(Tarjeta.class, tarjeta.getId());
			em.remove(tarjeta);
		}finally{
			em.close();
		}
	}

}
