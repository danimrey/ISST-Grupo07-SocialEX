package es.upm.dit.isst.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Cliente implements Serializable {
	@Id
	private String correo = "";
	private String nombre = "";
	private String apellidos = "";
	private String pais = "";
	private String fechaCreacion = "";
	private int notificaciones;
	//Inicialmente 4 monedas. Posibilidad de ampliacion
	//Se pone EAGER porque esta configurado por defecto como LAZY
	@ManyToMany(fetch=FetchType.EAGER) 
	private List<Long> cuentas;
	@ManyToMany(fetch=FetchType.EAGER) 
	private List<String> amigos;
	@ManyToMany(fetch=FetchType.EAGER) 
	private List<String> solicitudesPendientes;
	
	public Cliente(String nombre,String apellidos, String correo, String pais, int notificaciones) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.pais = pais;
		this.notificaciones = notificaciones;
		
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaCreacion = formatted;
		this.cuentas = new ArrayList<Long>();
		this.amigos = new ArrayList<String>();
		this.solicitudesPendientes = new ArrayList<String>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public int getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(int notificaciones) {
		this.notificaciones = notificaciones;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<Long> getCuentas() {
		return cuentas;
	}


	public List<String> getAmigos() {
		return amigos;
	}
	
	public void setCuenta(Long cuenta){
		this.cuentas.add(cuenta);
	}
	public void setAmigos(List<String> amigos) {
		this.amigos = amigos;
	}
	
	public List<String> getSolicitudesPendientes() {
		return solicitudesPendientes;
	}
	public void setSolicitudesPendientes(List<String> solicitudesPendientes) {
		this.solicitudesPendientes = solicitudesPendientes;
	}
}
