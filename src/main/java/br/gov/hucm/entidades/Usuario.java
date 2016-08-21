package br.gov.hucm.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@Column(name = "id_usuario", nullable = false, unique = true)
	private int id;

	@Column(name = "name", nullable = false, unique = true)
	private String nomeUsuario;

	@Column(name = "senha", nullable = false, unique = false)
	private String senha;

//	@Column(name = "lastAccess", unique = true)
//	@Temporal(TemporalType.DATE)
//	private Date ultimoAcesso;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public Date getUltimoAcesso() {
//		return ultimoAcesso;
//	}
//
//	public void setUltimoAcesso(Date ultimoAcesso) {
//		this.ultimoAcesso = ultimoAcesso;
//	}
}