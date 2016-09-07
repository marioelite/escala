package br.gov.hucm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_foto")
public class Foto implements Serializable {

	private static final long serialVersionUID = -5943268039275238415L;

	@Id
	@GeneratedValue
	@Column(name = "id_foto")
	private Integer id;

	@Column(name = "foto")
	private byte[] foto; 

	@Column(name = "nome_foto")
	private String nomeFoto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}
}