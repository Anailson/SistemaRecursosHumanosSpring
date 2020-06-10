package br.com.rh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cargo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nomeCargo;
	
	private int ValorSalario;
	
	private int ValorRefeicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public int getValorSalario() {
		return ValorSalario;
	}

	public void setValorSalario(int valorSalario) {
		ValorSalario = valorSalario;
	}

	public int getValorRefeicao() {
		return ValorRefeicao;
	}

	public void setValorRefeicao(int valorRefeicao) {
		ValorRefeicao = valorRefeicao;
	}

	
	
	

}
