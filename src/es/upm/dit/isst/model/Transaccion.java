package es.upm.dit.isst.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
public class Transaccion implements Serializable {
	
	public enum Tipo{
		INGRESAR, SACAR, COMPRAR, CAMBIO_DIVISAS, VACIO
	}
	
	@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Id Long id;
	private Long cuentaAsociada;
	private String fechaServidor = "";
	private String fechaCliente = "";
	private String divisa = "";
	private Double importe = 0.0;
	private String concepto = "";
	private Tipo tipo = Tipo.VACIO;
	
	public Transaccion(Long cuentaAsociada,String fechaCliente, String divisa,
			Double importe, String concepto, Tipo tipo) {
		super();
		this.cuentaAsociada = cuentaAsociada;
		this.divisa = divisa;
		this.importe = importe;
		this.concepto = concepto;
		this.tipo = tipo;
		this.fechaCliente = fechaCliente; 
		
		//Fecha automatica
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaServidor = formatted;
		
	}

	public Long getCuentaAsociada() {
		return cuentaAsociada;
	}

	public void setCuentaAsociada(Long cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}

	public String getFechaServidor() {
		return fechaServidor;
	}

	public void setFechaServidor(String fechaServidor) {
		this.fechaServidor = fechaServidor;
	}

	public String getFechaCliente() {
		return fechaCliente;
	}

	public void setFechaCliente(String fechaCliente) {
		this.fechaCliente = fechaCliente;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
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

	public Long getId() {
		return id;
	}
	
	
}
