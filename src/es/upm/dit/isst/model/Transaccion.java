package es.upm.dit.isst.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Transaccion implements Serializable {
	
	public enum Tipo{
		INGRESAR, SACAR, COMPRAR, CAMBIO_DIVISAS, VACIO
	}
	@Id
	String fechaServidor = "";
	String fechaCliente = "";
	String cliente = "";
	String monedaIncial = "";
	String monedaFinal = "";
	Double dinero = 0.0;
	String concepto = "";
	Tipo tipo = Tipo.VACIO;
	
	public Transaccion(String cliente,String fechaCliente, String monedaIncial,
			String monedaFinal, Double dinero, String concepto, Tipo tipo) {
		super();
		this.cliente = cliente;
		this.monedaIncial = monedaIncial;
		this.monedaFinal = monedaFinal;
		this.dinero = dinero;
		this.concepto = concepto;
		this.tipo = tipo;
		this.fechaCliente = fechaCliente; 
		
		//Fecha automatica
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaServidor = formatted+"-"+cliente;
		
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFecha() {
		return fechaServidor;
	}
	public void setFecha(String fecha) {
		this.fechaServidor = fecha;
	}
	public String getFechaCliente() {
		return fechaCliente;
	}
	public void getFechaCliente(String fecha) {
		this.fechaCliente = fecha;
	}
	public String getMonedaIncial() {
		return monedaIncial;
	}
	public void setMonedaIncial(String monedaIncial) {
		this.monedaIncial = monedaIncial;
	}
	public String getMonedaFinal() {
		return monedaFinal;
	}
	public void setMonedaFinal(String monedaFinal) {
		this.monedaFinal = monedaFinal;
	}
	public Double getDinero() {
		return dinero;
	}
	public void setDinero(Double dinero) {
		this.dinero = dinero;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
