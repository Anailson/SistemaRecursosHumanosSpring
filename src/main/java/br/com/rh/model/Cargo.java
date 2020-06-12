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
	
	private String nomecargo;
	
	private int valorsalario;
	
	private int valorrefeicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomecargo() {
		return nomecargo;
	}

	public void setNomecargo(String nomecargo) {
		this.nomecargo = nomecargo;
	}

	public int getValorsalario() {
		return valorsalario;
	}

	public void setValorsalario(int valorsalario) {
		this.valorsalario = valorsalario;
	}

	public int getValorrefeicao() {
		return valorrefeicao;
	}

	public void setValorrefeicao(int valorrefeicao) {
		this.valorrefeicao = valorrefeicao;
	}

	
	
}
