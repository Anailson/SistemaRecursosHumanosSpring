package br.com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rh.model.Cargo;


@Transactional
@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long>{

	@Query("select p from Cargo p where p.nomecargo like %?1%")
	List<Cargo> findCargoByName(String nomecargo);
	
}
