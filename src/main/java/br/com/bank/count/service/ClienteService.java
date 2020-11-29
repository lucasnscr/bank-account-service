package br.com.bank.count.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;

public interface ClienteService {
	
	/**
	 * Metodo que cadastra cliente e retorna um hash
	 * @param cliente
	 * @return String
	 */
	String cadastraCliente(ClienteDto cliente);
	
	
	/**
	 * Metodo que retorna lista com todos os clientes
	 * @return List<ClienteDto>
	 */
	List<ClienteDto> listClientes();
	
	/**
	 * Método que busca um cliente pelo numero da chave
	 * @param numConta
	 * @return ClienteDto
	 */
	ClienteDto buscarClienteNumConta(String numConta);
	
	
	/**
	 * Metodo que realiza o debito do valor da transferencia, debito realizado da conta do cliente que envia e creditado na conta do cliente que recebe
	 * @param clienteEnvia
	 * @param clienteRecebe
	 * @param valorTransferencia
	 * @return
	 */
	Boolean debitarValorDaTransacao(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia);
}
