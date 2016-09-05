package br.gov.hucm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_enfermeiro")
public class Enfermeiro implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ENFERMEIRO", nullable = false, unique = true)
	private int id_enfermeiro;
	
	@Column(name = "NM_ENFERMEIRO", nullable = false)
	private String nome_enfermeiro;
	
	@Column(name = "DS_EMAIL", nullable = false)
	private String email;
	
	@OneToOne
	@JoinColumn(name = "ID_SETOR")
	private Setor setor;

	public int getId_enfermeiro() {
		return id_enfermeiro;
	}

	public void setId_enfermeiro(int id_enfermeiro) {
		this.id_enfermeiro = id_enfermeiro;
	}

	public String getNome_enfermeiro() {
		return nome_enfermeiro;
	}

	public void setNome_enfermeiro(String nome_enfermeiro) {
		this.nome_enfermeiro = nome_enfermeiro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}