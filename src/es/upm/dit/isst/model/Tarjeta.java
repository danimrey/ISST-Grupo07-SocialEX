package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Tarjeta implements Serializable {
	@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Id
	private Long id;
	private String titularTarjeta = "";
	private String direccion = "";
	private String ciudad = "";
	private String provincia = "";
	private String pais = "";
	private String codigoPostal = "";
	private String numeroTarjeta = "";
	private String caducidadTarjeta = "";
	private String codigoSecreto = "";
	
	public Tarjeta(String titularTarjeta, String direccion, String ciudad,
			String provincia, String pais, String codigoPostal,
			String numeroTarjeta, String caducidadTarjeta, String codigoSecreto) {
		super();
		this.titularTarjeta = titularTarjeta;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.numeroTarjeta = numeroTarjeta;
		this.caducidadTarjeta = caducidadTarjeta;
		this.codigoSecreto = codigoSecreto;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCaducidadTarjeta() {
		return caducidadTarjeta;
	}

	public void setCaducidadTarjeta(String caducidadTarjeta) {
		this.caducidadTarjeta = caducidadTarjeta;
	}

	public String getCodigoSecreto() {
		return codigoSecreto;
	}

	public void setCodigoSecreto(String codigoSecreto) {
		this.codigoSecreto = codigoSecreto;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
