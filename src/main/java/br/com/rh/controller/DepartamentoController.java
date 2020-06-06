package br.com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rh.model.Departamento;
import br.com.rh.repository.DepartamentoRepository;

@Controller
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@RequestMapping(method =  RequestMethod.GET, value = "/cadastrodepartamento")
	public String inicio() {
		
		return "cadastro/cadastrodepartamento";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvardepartamento")
	public ModelAndView salvar(Departamento departamento) {
		
		departamentoRepository.save(departamento);
		

		ModelAndView andView = new ModelAndView("cadastro/cadastrodepartamento");
		Iterable<Departamento> departamentoIt = departamentoRepository.findAll();
		andView.addObject("departamentos", departamentoIt);
		
		return andView;
	}
	
	/*---------------LISTAR------------------------*/
	@RequestMapping(method =  RequestMethod.GET, value = "/listadepartamentos")
	public ModelAndView departamentos() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrodepartamento");
		Iterable<Departamento> departamentoIt = departamentoRepository.findAll();
		andView.addObject("departamentos", departamentoIt);
		
		return andView;
	}
	
	
}



