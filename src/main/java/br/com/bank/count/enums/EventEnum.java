package br.com.bank.count.enums;

public enum EventEnum {
	
	EVENT_TRANSFERENCIA("EVENT_TRANSFERENCIA");

	public static final String EVENT_ID = "TRANSFERENCIA";
	public static final String INPUT = "input";
	public static final String OUTPUT = "output";
	
	private String descricao;
	
	 EventEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
