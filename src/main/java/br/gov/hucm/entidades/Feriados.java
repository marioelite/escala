package br.gov.hucm.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_feriados")
public class Feriados implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_FERIADO", nullable = false, unique = true)
	private int id_feriado;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DT_FERIADO")
	private Date data_feriado;

	@Column(name = "DS_FERIADO")
	private String descricao_feriado;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "TP_FERIADO")
	private TipoFeriadoEnum tipoFeriado;

	public int getId_feriado() {
		return id_feriado;
	}

	public void setId_feriado(int id_feriado) {
		this.id_feriado = id_feriado;
	}

	public String getDescricao_feriado() {
		return descricao_feriado;
	}

	public void setDescricao_feriado(String descricao_feriado) {
		this.descricao_feriado = descricao_feriado;
	}

	public TipoFeriadoEnum getTipoFeriado() {
		return tipoFeriado;
	}

	public void setTipoFeriado(TipoFeriadoEnum tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}

	public Date getData_feriado() {
		return data_feriado;
	}

	public void setData_feriado(Date data_feriado) {
		this.data_feriado = data_feriado;
	}
}