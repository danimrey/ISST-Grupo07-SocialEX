package es.upm.dit.isst.g7.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

public interface TransaccionDAO {
	
	public void createTransaccion(String cliente,String fechaCliente, String monedaIncial,
			String monedaFinal, Double dinero, String concepto, Tipo tipo);

	public List<Transaccion> getAllTransacciones();

	public List<Transaccion> getTransaccionesbyUser(String user);

}
