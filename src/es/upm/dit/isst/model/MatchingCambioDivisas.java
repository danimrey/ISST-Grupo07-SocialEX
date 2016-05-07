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
public class MatchingCambioDivisas implements Serializable {
	@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Id private Long id;
	private String fecha;
	private int modo;
	private Long numeroSolicitud1;
	private Long numeroSolicitud2;
	private double comisionMassMoney;
	
	public MatchingCambioDivisas(int modo, Long numeroSolicitud1,
			Long numeroSolicitud2, double comisionMassMoney) {
		super();
		this.modo = modo;
		this.numeroSolicitud1 = numeroSolicitud1;
		this.numeroSolicitud2 = numeroSolicitud2;
		this.comisionMassMoney = comisionMassMoney;
		
		//Fecha
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fecha=formatted;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public Long getNumeroSolicitud1() {
		return numeroSolicitud1;
	}

	public void setNumeroSolicitud1(Long numeroSolicitud1) {
		this.numeroSolicitud1 = numeroSolicitud1;
	}

	public Long getNumeroSolicitud2() {
		return numeroSolicitud2;
	}

	public void setNumeroSolicitud2(Long numeroSolicitud2) {
		this.numeroSolicitud2 = numeroSolicitud2;
	}

	public double getComisionMassMoney() {
		return comisionMassMoney;
	}

	public void setComisionMassMoney(double comisionMassMoney) {
		this.comisionMassMoney = comisionMassMoney;
	}

	public Long getId() {
		return id;
	}
	
}
