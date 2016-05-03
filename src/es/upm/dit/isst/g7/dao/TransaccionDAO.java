package es.upm.dit.isst.g7.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import es.upm.dit.isst.model.Transaccion;
import es.upm.dit.isst.model.Transaccion.Tipo;

public interface TransaccionDAO {
	
	public Transaccion createTransaccion(Long cuentaAsociada, String fechaCliente, String divisa,
			Double importe, String concepto, Tipo tipo, String numeroTarjeta);

	public List<Transaccion> getAllTransacciones();

	public List<Transaccion> getTransaccionesbyCuenta(Long cuentaAsociada);
	
	public void update(Transaccion transaccion);

}
