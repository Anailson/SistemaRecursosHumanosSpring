package br.com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String inicio() {
			
	
		return "cadastro/cadastrocargo";
			
		
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/salvarcargo")
	public String salvar(Cargo cargo) {
		cargoRepository.save(cargo);
		return "cadastro/cadastrocargo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listacargos")
	public ModelAndView cargos () {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocargo");
		Iterable<Cargo> cargoIt = cargoRepository.findAll();
		andView.addObject("cargos", cargoIt );

		return andView;
	}
	

}
