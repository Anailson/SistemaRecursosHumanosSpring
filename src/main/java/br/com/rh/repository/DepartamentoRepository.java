package br.com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rh.model.Departamento;

@Repository
@Transactional
public interface DepartamentoRepository extends CrudRepository<Departamento, Long>{

	
	@Query("select p from Departamento p where p.nomedepartamento like %?1%")
	List<Departamento> findDepartamentoByName(String nomedepartamento);
	
	
}
