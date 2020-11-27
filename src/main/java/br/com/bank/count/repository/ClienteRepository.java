package br.com.bank.count.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bank.count.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	Cliente findByNumConta(Integer numConta);
	
}
