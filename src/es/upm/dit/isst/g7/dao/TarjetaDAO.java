package es.upm.dit.isst.g7.dao;

import java.util.List;

import es.upm.dit.isst.model.Tarjeta;

public interface TarjetaDAO {
	
	public Tarjeta Create(String titularTarjeta, String direccion, String ciudad,
			String provincia, String pais, String codigoPostal,
			String numeroTarjeta, String caducidadTarjeta, String codigoSecreto);
	
	public List<Tarjeta> getTodasTarjetas();
	
	public Tarjeta getTarjeta(Long id);
	
	public Tarjeta getTarjetaNumero(String numero);
	
	public void update(Tarjeta tarjeta);
	
	public void Delete(Tarjeta tarjeta);

}
