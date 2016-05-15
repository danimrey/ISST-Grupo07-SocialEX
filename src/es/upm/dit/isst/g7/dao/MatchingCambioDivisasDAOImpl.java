package es.upm.dit.isst.g7.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.MatchingCambioDivisas;
import es.upm.dit.isst.model.SolicitudCambioDivisas;

public class MatchingCambioDivisasDAOImpl implements MatchingCambioDivisasDAO {

	private static MatchingCambioDivisasDAOImpl instance; 
	private MatchingCambioDivisasDAOImpl() {
	}
	public static MatchingCambioDivisasDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  MatchingCambioDivisasDAOImpl( ) ;
		return instance;  
	}
	
	@Override
	public MatchingCambioDivisas Create(int modo, Long numeroSolicitud1,
			Long numeroSolicitud2, double comisionMassMoney) {
		MatchingCambioDivisas match = null;
		EntityManager em = EMFService.get().createEntityManager();
		match = new MatchingCambioDivisas(modo, numeroSolicitud1, numeroSolicitud2, comisionMassMoney);
		em.persist(match);
		em.close();
		return match;
	}

	@Override
	public MatchingCambioDivisas read(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		MatchingCambioDivisas match = null;
		try{
			match = em.find(MatchingCambioDivisas.class, id);
		}catch(Exception e){
			System.out.println("No encuentra match por id");
		}
		em.close();
		return match;
	}

	@Override
	public List<MatchingCambioDivisas> readAll() {
		EntityManager em = EMFService.get().createEntityManager();
		List<MatchingCambioDivisas> matches = new ArrayList<MatchingCambioDivisas>();
		matches = em.createQuery("select m from MatchingCambioDivisas m").getResultList();
		em.close();
		return matches;
	}

	@Override
	public List<MatchingCambioDivisas> readModo(int modo) {
		EntityManager em = EMFService.get().createEntityManager();
		List<MatchingCambioDivisas> matches = new ArrayList<MatchingCambioDivisas>();
		try{
			matches = em.createQuery("select m from MatchingCambioDivisas m where m.modo = :modo").setParameter("modo", modo).getResultList();
		}catch(Exception e){
			System.out.println("No encuentras matches por ID");
		}
		em.close();
		return matches;
	}

	@Override
	public void Update(MatchingCambioDivisas match) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(match);
		em.close();

	}

	@Override
	public void Delete(MatchingCambioDivisas match) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			MatchingCambioDivisas matchDel = em.find(MatchingCambioDivisas.class, match.getId());
			em.remove(matchDel);
		}finally{
			em.close();
		}
	}

}
