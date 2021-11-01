package com.forte.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

import com.forte.backend.entity.TipoPermiso;

@Entity
@Table(name = "jcpp_permiso")
public class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nombreEmpleado;
	
	@Column
	private String apellidosEmpleado;
	
	@Column
	private Long tipoPermiso;
	
	@Column
	private Date fechaPermiso;
	
	@OneToOne(mappedBy = "permiso")
	private TipoPermiso tipoPer;
		
	public Permiso() {

	}

	public Permiso(String nombreEmpleado, String apellidosEmpleado, Long tipoPermiso, Date fechaPermiso) {
		this();
		this.nombreEmpleado = nombreEmpleado;
		this.apellidosEmpleado = apellidosEmpleado;
		this.tipoPermiso = tipoPermiso;
		this.fechaPermiso = fechaPermiso;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	public String getApellidosEmpleado() {
		return apellidosEmpleado;
	}
	
	public void setApellidosEmpleado(String apellidosEmpleado) {
		this.apellidosEmpleado = apellidosEmpleado;
	}
	
	public Long getTipoPermiso() {
		return tipoPermiso;
	}
	
	public void setTipoPermiso(Long tipoPermiso) {
		this.tipoPermiso = tipoPermiso;
	}
	
	public Date getFechaPermiso() {
		return fechaPermiso;
	}
	
	public void setFechaPermiso(Date fechaPermiso) {
		this.fechaPermiso = fechaPermiso;
	}
	
	

}
