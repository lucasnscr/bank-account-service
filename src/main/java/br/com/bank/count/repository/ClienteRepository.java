package br.com.bank.count.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.bank.count.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	/**
	 * Consulta para buscar cliente pelo numero da conta
	 * @param numConta
	 * @return List<Cliente>
	 */
	 List<Cliente> findByNumConta(String numConta);
	
	
}
