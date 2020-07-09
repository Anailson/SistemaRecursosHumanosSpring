package br.com.rh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rh.model.Funcionario;
import br.com.rh.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ReportUtil reportUtil;  //DEPENDENCIA DO RELATORIO
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrofuncionario")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofuncionario");
		modelAndView.addObject("funcionarioobj", new Funcionario());  //CRIANDO NOVO OBJETO AO INICIAR NOVA TELA DE CADASTRO 
				
		return modelAndView;
		
	}
	/*------------------METODO DE SALVAR-------------------------*/
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarfuncionario")
	public ModelAndView salvar(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrofuncionario");
		Iterable<Funcionario> funcionarioIt = funcionarioRepository.findAll();
	    andView.addObject("funcionarios", funcionarioIt);
	    
	    andView.addObject("funcionarioobj", new Funcionario());
		
		
	    return andView;
	}
	
	
	/*------------------METODO DE LISTA FUNCIONARIOS-------------------------*/
	@RequestMapping(method = RequestMethod.GET, value = "**/listafuncionarios")
	public ModelAndView funcionarios() {
		ModelAndView andView = new ModelAndView("cadastro/cadastrofuncionario");

	    Iterable<Funcionario> funcionarioIt = funcionarioRepository.findAll(); 
	    andView.addObject("funcionarios", funcionarioIt);
	    
	    
	    andView.addObject("funcionarioobj", new Funcionario());  //Whitelabel Error Page
	    
		
		return andView;
		
		
	}
	

	/*------------------METODO DE EDITAR-------------------------*/
	@GetMapping("/editarfuncionario/{idfuncionario}")
	public ModelAndView editar(@PathVariable("idfuncionario") Long idfuncionario){
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idfuncionario);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofuncionario");
		modelAndView.addObject("funcionarioobj", funcionario.get());
		
		return modelAndView;
		
	}
	
	
	/*------------------METODO DE EXCLUIR-------------------------*/
	@GetMapping("/excluirfuncionario/{idfuncionario}")
	public ModelAndView excluir(@PathVariable("idfuncionario") Long idfuncionario){
		
		
		funcionarioRepository.deleteById(idfuncionario); //EXCLUINDO POR ID
		
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofuncionario");
		modelAndView.addObject("funcionarios", funcionarioRepository.findAll());//LISTA TODOS AO EXCLUIR
		modelAndView.addObject("funcionarioobj", new Funcionario()); //CRIANDO UM NOVO OBJETO
	   
		return modelAndView;
		
		
	}
	/*------------------METODO DE CONSULTA POR NOME E SEXO-------------------------*/
	@PostMapping("**/pesquisafuncionario")
	public ModelAndView pesquisa(@RequestParam("nomepesquisa")String nomepesquisa,
			@RequestParam("pesqsexo")String pesqsexo) {
		
		/*IMPLEMENTAÇÃO DA CONSULTA DINAMICA NOME E SEXO*/
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		if (pesqsexo != null && !pesqsexo.isEmpty()) {
			 funcionarios = funcionarioRepository.findFuncionarioByNameSexo(nomepesquisa, pesqsexo);
			 
		}else { //CASO NÃO TENHA SEXO
		   funcionarios = funcionarioRepository.findFuncionarioByName(nomepesquisa)	;//RETORNA APENAS O NOME DA PESSOA
		}
			
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofuncionario");
		modelAndView.addObject("funcionarios", funcionarios);
		modelAndView.addObject("funcionarioobj", new Funcionario());
		
		return modelAndView;
	}
	
	
	/*------------------METODO PARA O PDF RELATORIOO-------------------------*/
	@GetMapping("**/pesquisafuncionario")
	public void imprimePDF(@RequestParam("nomepesquisa")String nomepesquisa, 
			@RequestParam("pesqsexo")String pesqsexo, 	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		if (pesqsexo != null && !pesqsexo.isEmpty() && nomepesquisa !=null &&nomepesquisa.isEmpty()) {  //BUSCA O NOME E SEXO
			funcionarios = funcionarioRepository.findFuncionarioByNameSexo(nomepesquisa, pesqsexo);
			
		}else if(nomepesquisa !=null &&nomepesquisa.isEmpty()) {
			funcionarios = funcionarioRepository.findFuncionarioByName(nomepesquisa);
		}else { //BUSCA TODOS - caso nao informe nenhum dados
			Iterable<Funcionario> iterator = funcionarioRepository.findAll();
			for (Funcionario funcionario : iterator) {
				funcionarios.add(funcionario);
			}
		}
		
		/*CHAMA O SERVIÇO DE GERAÇÃO DO RELATORIO*/
		
	
		byte[] pdf = reportUtil.gerarRelatorio(funcionarios, "funcionario", request.getServletContext());
		
		/*---------------REGRA PARA O DOWLOAD INICIO*/
		
		 /*TAMANHO DA RESPOSTA*/
		response.setContentLength(pdf.length);
		
		/*DEFINIR NA RESPOSTA O TIPO DE ARQUIVO*/
		response.setContentType("application/octet-stream");
		
		/*DEFINIR O CABEÇALHO DA RESPOSTA*/
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "relatorio.pdf");
		response.setHeader(headerKey, headerValue);
		
		/*FINALIZA A RESPOSTA PARA O NAVEGADOR*/
		response.getOutputStream().write(pdf);
			
		/*---------------REGRA PARA O DOWLOAD FIM----*/		
			
	
	}
	
}
