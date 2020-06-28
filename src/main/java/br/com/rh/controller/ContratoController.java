package br.com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.rh.model.Contrato;
import br.com.rh.repository.ContratoRepository;

@Controller
public class ContratoController {
	
	@SuppressWarnings("unused")
	@Autowired
	private ContratoRepository contratoRepository;
	
	@RequestMapping(method =  RequestMethod.GET, value = "/cadastrocontrato")
	public String inicio() {
		
		return "cadastro/cadastrocontrato";
		
	}
	@RequestMapping(method = RequestMethod.POST, value = "/salvarcontrato")
	public String salvar(Contrato contrato) {
		
		contratoRepository.save(contrato);
		return "cadastro/cadastrocontrato";
		
	}
	
	

}
