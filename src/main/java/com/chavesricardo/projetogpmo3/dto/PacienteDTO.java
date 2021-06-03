package com.chavesricardo.projetogpmo3.dto;

import java.io.Serializable;

import com.chavesricardo.projetogpmo3.domain.Paciente;

public class PacienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer prontuario;
	
	public PacienteDTO() {
	}
	
	public PacienteDTO(Paciente obj) {
		id = obj.getId();
		prontuario = obj.getProntuario();
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

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}
	
	
	
}
