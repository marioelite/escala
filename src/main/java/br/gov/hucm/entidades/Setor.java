package br.gov.hucm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_setor")
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_SETOR", nullable = false, unique = true)
	private int id_setor;
	
	@Column(name = "DS_SETOR")
	private String descricao_setor;

	public int getId_setor() {
		return id_setor;
	}

	public void setId_setor(int id_setor) {
		this.id_setor = id_setor;
	}

	public String getDescricao_setor() {
		return descricao_setor;
	}

	public void setDescricao_setor(String descricao_setor) {
		this.descricao_setor = descricao_setor;
	}
}