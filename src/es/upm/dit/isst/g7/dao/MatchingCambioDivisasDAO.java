package es.upm.dit.isst.g7.dao;

import java.util.List;

import es.upm.dit.isst.model.MatchingCambioDivisas;

public interface MatchingCambioDivisasDAO {
	
	public MatchingCambioDivisas Create(int modo, Long numeroSolicitud1,
			Long numeroSolicitud2, double comisionMassMoney);
	
	public MatchingCambioDivisas read(Long id);
	
	public List<MatchingCambioDivisas> readAll();
	
	public List<MatchingCambioDivisas> readModo(int modo);
	
	public void Update(MatchingCambioDivisas match);
	
	public void Delete(MatchingCambioDivisas match);


}
