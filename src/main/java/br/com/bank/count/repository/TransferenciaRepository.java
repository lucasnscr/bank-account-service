package br.com.bank.count.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.bank.count.entity.Transferencia;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long>{
	
	List<Transferencia> findById(String id);

}
