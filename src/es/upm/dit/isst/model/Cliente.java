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

@Entity
public class Cliente implements Serializable {
	String nombre = "";
	@Id
	String correo = "";
	String pais = "";
	String fechaCreacion = "";
	int notificaciones;
	//Inicialmente 4 monedas. Posibilidad de ampliacion
	//Se pone EAGER porque esta configurado por defecto como LAZY
	@ManyToMany(fetch=FetchType.EAGER) 
	Map<String, Double> saldo = new HashMap<String, Double>();
	
	public Cliente(String nombre, String correo, String pais, int notificaciones) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.pais = pais;
		this.notificaciones = notificaciones;
		//4 monedas iniciales
		this.saldo.put("EUR", 0.0);
		this.saldo.put("GBP", 0.0);
		this.saldo.put("USD", 0.0);
		this.saldo.put("JPY", 0.0);
		
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaCreacion = formatted;
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

	public Map<String, Double> getSaldo() {
		return this.saldo;
	}
	
	public Double getSaldo(String moneda) {
		return this.saldo.get(moneda);
	}

	public void setNewModeda(Map<String, Double> moneda) {
		this.saldo = saldo;
	}
	
	public void addSaldo(String moneda, Double cantidad) {
		Double oldSaldo = this.saldo.get(moneda);
		saldo.put(moneda, oldSaldo+cantidad);
	}
}
