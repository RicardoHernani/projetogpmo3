package com.chavesricardo.projetogpmo3.dto;

import java.io.Serializable;
import java.util.Date;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CirurgiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	public CirurgiaDTO() {
	}
	
	public CirurgiaDTO(Cirurgia obj) {
		id = obj.getId();
		data = obj.getData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
