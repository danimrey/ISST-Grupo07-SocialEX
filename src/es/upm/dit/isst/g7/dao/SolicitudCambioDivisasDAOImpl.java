package es.upm.dit.isst.g7.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import es.upm.dit.isst.model.SolicitudCambioDivisas;

public class SolicitudCambioDivisasDAOImpl implements SolicitudCambioDivisasDAO {
	
	private static SolicitudCambioDivisasDAOImpl instance; 
	private SolicitudCambioDivisasDAOImpl() {
	}
	public static SolicitudCambioDivisasDAOImpl getInstance() {
		if (instance == null) 
			instance  = new  SolicitudCambioDivisasDAOImpl( ) ;
		return instance;  
	}
	
	@Override
	public SolicitudCambioDivisas Create(int estado, double importeCambio,
			String divisaCambio, String divisaPredeterminada,
			Long cuentaSolicitante, int modoSolicitud) {
		EntityManager em = EMFService.get().createEntityManager();
		SolicitudCambioDivisas solicitud = new SolicitudCambioDivisas(estado, importeCambio, divisaCambio, divisaPredeterminada, cuentaSolicitante, modoSolicitud);
		em.persist(solicitud);
		em.close();
		return solicitud;
	}
	
	@Override
	public SolicitudCambioDivisas read(Long id){
		EntityManager em = EMFService.get().createEntityManager();
		SolicitudCambioDivisas solicitud = null;
		try{
			solicitud = em.find(SolicitudCambioDivisas.class, id);
		}catch(Exception e){
			System.out.println("No encuentra solicitud por id");
		}
		em.close();
		return solicitud;
		
	}
	@Override
	public List<SolicitudCambioDivisas> readAll() {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		solicitudes = em.createQuery("select m from SolicitudCambioDivisas m").getResultList();
		em.close();
		return solicitudes;
	}

	@Override
	public List<SolicitudCambioDivisas> readCuenta(Long cuenta) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		try{
			solicitudes = em.createQuery("select m from SolicitudCambioDivisas m where m.cuentaSolicitante = :cuenta").setParameter("cuenta", cuenta).getResultList();

		}catch(Exception e){
			System.out.println("No encuentra solicitud por nº de cuenta");
		}
		em.close();
		return solicitudes;
	}

	@Override
	public List<SolicitudCambioDivisas> readDivisaCambio(String divisaCambio) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		try{
			solicitudes = em.createQuery("select m from SolicitudCambioDivisas m where m.divisaCambio = :divisaCambio").setParameter("divisaCambio", divisaCambio).getResultList();

		}catch(Exception e){
			System.out.println("No encuentra solicitud por divisaCambio");
		}
		em.close();
		return solicitudes;
	}

	@Override
	public List<SolicitudCambioDivisas> readDivisaCuenta(
			String divisaPredeterminada) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		try{
			solicitudes = em.createQuery("select m from SolicitudCambioDivisas m where m.divisaPredeterminada = :divisaPredeterminada").setParameter("divisaPredeterminada", divisaPredeterminada).getResultList();

		}catch(Exception e){
			System.out.println("No encuentra solicitud por divisaPredeterminada");
		}
		em.close();
		return solicitudes;
	}

	@Override
	public List<SolicitudCambioDivisas> readEstado(int estado) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		try{
			solicitudes = em.createQuery("select m from SolicitudCambioDivisas m where m.estado = :estado").setParameter("estado", estado).getResultList();

		}catch(Exception e){
			System.out.println("No encuentra solicitud por estado");
		}
		em.close();
		return solicitudes;
	}

	@Override
	public List<SolicitudCambioDivisas> readModo(int modo) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SolicitudCambioDivisas> solicitudes = new ArrayList<SolicitudCambioDivisas>();
		try{
			solicitudes = em.createQuery("select m from SolicitudCambioDivisas m where m.modo = :modo").setParameter("modo", modo).getResultList();

		}catch(Exception e){
			System.out.println("No encuentra solicitud por modo");
		}
		em.close();
		return solicitudes;
	}

	@Override
	public void Update(SolicitudCambioDivisas solicitud) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(solicitud);
		em.close();
	}

	@Override
	public void Delete(SolicitudCambioDivisas solicitud) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			SolicitudCambioDivisas solicitudDel = em.find(SolicitudCambioDivisas.class, solicitud.getId());
			em.remove(solicitudDel);
		}finally{
			em.close();
		}
	}

}
