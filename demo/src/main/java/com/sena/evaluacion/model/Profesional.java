package com.sena.evaluacion.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesional")
public class Profesional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "Especialidad")
	private String especialidad;
	
	@Column(name = "Hora de inicio")
	private LocalTime horaInicio;
	
	@Column(name = "Hora fin")
	private LocalTime horaFin;
	
	@OneToMany
	private List<Usuario> usuario = new ArrayList<>();
	
	@OneToMany
	private List<Cita> cita = new ArrayList<>();

	public Profesional() {}

	public Profesional(Integer id, String especialidad, LocalTime horaInicio, LocalTime horaFin, List <Usuario> usuario,
			List<Cita> cita) {
		super();
		this.id = id;
		this.especialidad = especialidad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.usuario = usuario;
		this.cita = cita;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public List <Usuario>  getUsuario() {
		return usuario;
	}

	public void setUsuario(List <Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<Cita> getCita() {
		return cita;
	}

	public void setCita(List<Cita> cita) {
		this.cita = cita;
	}

	@Override
	public String toString() {
		return "Profesional [id=" + id + ", especialidad=" + especialidad + ", horaInicio=" + horaInicio + ", horaFin="
				+ horaFin + ", usuario=" + usuario + ", cita=" + cita + "]";
	}

	
	
}


