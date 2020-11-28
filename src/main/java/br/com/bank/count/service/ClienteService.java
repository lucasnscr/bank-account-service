package br.com.bank.count.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;

public interface ClienteService {
	
	String cadastraCliente(ClienteDto cliente);
	List<ClienteDto> listClientes();
	ClienteDto buscarClienteNumConta(String numConta);
	Boolean debitarValorDaTransacao(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia);
}
