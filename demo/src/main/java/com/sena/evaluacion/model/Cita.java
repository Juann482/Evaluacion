package com.sena.evaluacion.model;

import java.time.DateTimeException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cita")
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = " Fecha y hora")
	private DateTimeException Fecha_hora;
	
	@Column(name = "Estado")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idProfesional")
	private Profesional profesional;
	
	@ManyToOne
	@JoinColumn(name = "idServicio")
	private Servicio servicio;

	public Cita() {}

	public Cita(Integer id, DateTimeException fecha_hora, String estado, Usuario usuario, Profesional profesional,
			Servicio servicio) {
		super();
		this.id = id;
		Fecha_hora = fecha_hora;
		this.estado = estado;
		this.usuario = usuario;
		this.profesional = profesional;
		this.servicio = servicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DateTimeException getFecha_hora() {
		return Fecha_hora;
	}

	public void setFecha_hora(DateTimeException fecha_hora) {
		Fecha_hora = fecha_hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", Fecha_hora=" + Fecha_hora + ", estado=" + estado + ", usuario=" + usuario
				+ ", profesional=" + profesional + ", servicio=" + servicio + "]";
	}
	
		
}
