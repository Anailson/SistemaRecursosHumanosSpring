package br.com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rh.model.Funcionario;

@Repository
@Transactional
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

	@Query("select p from Funcionario p where p.nome like %?1% ")
	List<Funcionario> findFuncionarioByName(String nome);
	
}
