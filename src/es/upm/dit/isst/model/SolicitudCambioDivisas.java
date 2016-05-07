package es.upm.dit.isst.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class SolicitudCambioDivisas implements Serializable {
	@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Id private Long id;
	//1=pendiente; 2= esperandoAceptación(matching); 3=Finalizado
	private int estado;
	//Importe solicitado
	private double importeCambio;
	//Divisa Solicitada a Cambiar
	private String divisaCambio;
	//Divisa principal de la cuenta
	private String divisaPredeterminada;
	//Cuenta que solicita cambio de divisas
	private Long cuentaSolicitante;
	private String fechaSolicitud;
	//1=Modo normal; 2=modo amigo
	private int modoSolicitud;
	
	public SolicitudCambioDivisas(int estado, double importeCambio,
			String divisaCambio, String divisaPredeterminada,
			Long cuentaSolicitante, int modoSolicitud) {
		super();
		this.estado = estado;
		this.importeCambio = importeCambio;
		this.divisaCambio = divisaCambio;
		this.divisaPredeterminada = divisaPredeterminada;
		this.cuentaSolicitante = cuentaSolicitante;
		
		//Fecha
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaSolicitud=formatted;
		
		this.modoSolicitud = modoSolicitud;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public double getImporteCambio() {
		return importeCambio;
	}

	public void setImporteCambio(double importeCambio) {
		this.importeCambio = importeCambio;
	}

	public String getDivisaCambio() {
		return divisaCambio;
	}

	public void setDivisaCambio(String divisaCambio) {
		this.divisaCambio = divisaCambio;
	}

	public String getDivisaPredeterminada() {
		return divisaPredeterminada;
	}

	public void setDivisaPredeterminada(String divisaPredeterminada) {
		this.divisaPredeterminada = divisaPredeterminada;
	}

	public Long getCuentaSolicitante() {
		return cuentaSolicitante;
	}

	public void setCuentaSolicitante(Long cuentaSolicitante) {
		this.cuentaSolicitante = cuentaSolicitante;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getModoSolicitud() {
		return modoSolicitud;
	}

	public void setModoSolicitud(int modoSolicitud) {
		this.modoSolicitud = modoSolicitud;
	}

	public Long getId() {
		return id;
	}
	

}
