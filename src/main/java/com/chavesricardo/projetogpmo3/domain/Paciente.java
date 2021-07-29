package com.chavesricardo.projetogpmo3.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer prontuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="paciente")
	private List<Cirurgia> cirurgias = new ArrayList<>();
	
	public Paciente() {
	}

	public Paciente(Integer id, Integer prontuario, Usuario usuario) {
		super();
		this.id = id;
		this.prontuario = prontuario;
		this.usuario = usuario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer registro) {
		this.prontuario = registro;
	}

	public List<Cirurgia> getCirurgias() {
		return cirurgias;
	}

	public void setCirurgias(List<Cirurgia> cirurgias) {
		this.cirurgias = cirurgias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
