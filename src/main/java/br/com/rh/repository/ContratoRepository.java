package br.com.rh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rh.model.Contrato;

@Repository
@Transactional
public interface ContratoRepository extends CrudRepository<Contrato, Long>{

}
