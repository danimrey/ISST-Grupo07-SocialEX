package es.upm.dit.isst.g7.dao;

import java.util.List;

import es.upm.dit.isst.model.SolicitudCambioDivisas;

public interface SolicitudCambioDivisasDAO {
	
	//Crea una nueva solicitud
	public SolicitudCambioDivisas Create(int estado, double importeCambio,
			String divisaCambio, String divisaPredeterminada,
			Long cuentaSolicitante, int modoSolicitud);
	
	//Devuelve todas las solcitudes de cambio
	public List<SolicitudCambioDivisas> readAll();
	
	//Devuelve todas las solcitudes de cambio
	public SolicitudCambioDivisas read(Long id);
	
	//Devuelve solicitudes de cambio por cuenta asociada
	public List<SolicitudCambioDivisas> readCuenta(Long cuenta);
	
	//Devuelve solicitudes de cambio por divisa solicitada
	public List<SolicitudCambioDivisas> readDivisaCambio(String divisaCambio);
	
	//Devuelve solicitudes de cambio por divisa predeterminda de cuenta
	public List<SolicitudCambioDivisas> readDivisaCuenta(String divisaPredeterminada);
	
	//Devuelve solicitudes de cambio por estado
	public List<SolicitudCambioDivisas> readEstado(int estado);
	
	//Devuelve solicitudes de cambio por modo
	public List<SolicitudCambioDivisas> readModo(int modo);
	
	public void Update(SolicitudCambioDivisas solicitud);
	
	public void Delete(SolicitudCambioDivisas solicitud);
	
	

}
