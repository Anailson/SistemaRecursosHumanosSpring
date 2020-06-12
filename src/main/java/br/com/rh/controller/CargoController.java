package br.com.rh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rh.model.Cargo;
import br.com.rh.model.Departamento;
import br.com.rh.repository.CargoRepository;

@Controller
public class CargoController {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrocargo")
	public ModelAndView inicio() {
			

		ModelAndView andView = new ModelAndView("cadastro/cadastrocargo");
		andView.addObject("cargosobj", new Cargo());
		
		return andView;
			
		
	}
	/*salvar cargos*/
	@RequestMapping(method =  RequestMethod.POST, value = "**/salvarcargo")
	public ModelAndView salvar(Cargo cargo) {
		cargoRepository.save(cargo);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocargo");
		Iterable<Cargo> cargoIt = cargoRepository.findAll();
		
		andView.addObject("cargos", cargoIt);
		andView.addObject("cargosobj", new Cargo());
		
		return andView;
	}
	
	/*Lista cargos*/
	@RequestMapping(method = RequestMethod.GET, value = "**/listacargos")
	public ModelAndView cargos () {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocargo");
		Iterable<Cargo> cargoIt = cargoRepository.findAll();
		andView.addObject("cargos", cargoIt );
		
		andView.addObject("cargosobj", new Cargo());

		return andView;
	}
	
/*--------------EDITAR-------------------*/
	
	@GetMapping("/editarcargo/{idcargo}")
	public ModelAndView editar(@PathVariable("idcargo") Long idcargo) {
		
		Optional<Cargo> cargo = cargoRepository.findById(idcargo);
				
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocargo");
		modelAndView.addObject("cargosobj",cargo.get());
				
		return modelAndView;
		

	}
	
	

}
