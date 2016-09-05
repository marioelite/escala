package br.gov.hucm.entidades;

public enum TipoFeriadoEnum {

	Municipal("MUNICIPAL"), Estadual("ESTADUAL"), Nacional("NACIONAL");
	
	private String descricao;

	private TipoFeriadoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}