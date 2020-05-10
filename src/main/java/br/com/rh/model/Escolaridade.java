package br.com.rh.model;

public enum Escolaridade {
	
	ESPECIALIZACAO("Especialização"),
	GRADUACAO("Graduação"),
	MEDIO("Médio"),
	FUNDAMENTAL("Fundamental");
	
	private String nome;
	
	private Escolaridade (String nome) {  //criando um construtor para o ENUM
	  this.nome = nome;
		
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {

		return this.name(); //name é proprio do enum
	}
	
}
