package br.com.rh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rh.model.Departamento;

@Repository
@Transactional
public interface DepartamentoRepository extends CrudRepository<Departamento, Long>{

}
