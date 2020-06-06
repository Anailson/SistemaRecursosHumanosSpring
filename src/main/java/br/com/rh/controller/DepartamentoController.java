package br.com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.rh.repository.DepartamentoRepository;

@Controller
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@RequestMapping(method =  RequestMethod.GET, value = "/cadastrodepartamento")
	public String inicio() {
		
		return "cadastro/cadastrodepartamento";
	}
}
