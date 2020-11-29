package br.com.bank.count.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bank.count.entity.Transferencia;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long>{
	

}
