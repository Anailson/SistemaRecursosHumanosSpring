package br.com.rh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rh.model.Departamento;
import br.com.rh.model.Funcionario;
import br.com.rh.repository.DepartamentoRepository;

@Controller
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@RequestMapping(method =  RequestMethod.GET, value = "/cadastrodepartamento")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodepartamento");
		modelAndView.addObject("departamentosobj", new Departamento());
		
		return modelAndView;
	}
	
	/*--------------------SALVAR------------------*/
	@RequestMapping(method = RequestMethod.POST, value = "**/salvardepartamento")
	public ModelAndView salvar(Departamento departamento) {
		
		departamentoRepository.save(departamento);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrodepartamento");
		Iterable<Departamento> departamentoIt = departamentoRepository.findAll();
		andView.addObject("departamentos", departamentoIt);
		
		andView.addObject("departamentosobj", new Departamento());
		
		return andView;
	}
	
	/*---------------LISTAR------------------------*/
	@RequestMapping(method =  RequestMethod.GET, value = "**/listadepartamentos")
	public ModelAndView departamentos() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrodepartamento");
		Iterable<Departamento> departamentoIt = departamentoRepository.findAll();
		andView.addObject("departamentos", departamentoIt);
		

		andView.addObject("departamentosobj", new Departamento());  //Whitelabel Error Page
		
		return andView;
	}
	
	/*--------------EDITAR-------------------*/
	
	@GetMapping("/editardepartamento/{iddepartamento}")
	public ModelAndView editar(@PathVariable("iddepartamento") Long iddepartamento) {
		
		Optional<Departamento> departamento = departamentoRepository.findById(iddepartamento);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodepartamento");
		modelAndView.addObject("departamentosobj",departamento.get());
				
		return modelAndView;
	}
	
	/*--------------EXCLUIR-------------------*/
	@GetMapping("/removerdepartamento/{iddepartamento}")
	public ModelAndView excluir(@PathVariable("iddepartamento") Long iddepartamento) {
		
	    departamentoRepository.deleteById(iddepartamento);
	    
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodepartamento");
		modelAndView.addObject("departamentos", departamentoRepository.findAll());
		modelAndView.addObject("departamentosobj", new Departamento());
				
		return modelAndView;
	}
	
	
	/*----PESQUISA DEPARTAMENTO------*/
	@PostMapping("**/pesquisadepartamento")
	public ModelAndView pesquisa(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodepartamento");
		modelAndView.addObject("departamentos", departamentoRepository.findDepartamentoByName(nomepesquisa));
		modelAndView.addObject("departamentosobj", new Departamento());
		
		return modelAndView;
	}
	
}



