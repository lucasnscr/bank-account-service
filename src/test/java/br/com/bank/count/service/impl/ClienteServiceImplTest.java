package br.com.bank.count.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest  {
	
	@Mock
	private ClienteRepository cliRepo;
	
	@InjectMocks
	private ClienteServiceImpl serviceImpl;
	
	@Test
	public void testConstructor() {
		serviceImpl = new ClienteServiceImpl(cliRepo);
		assertNotNull(serviceImpl, "not be null");
	}
	

	@Test
	public void testBuscaClienteNumConta() {
		Cliente buildCliente = buildCliente();
		
		List<Cliente> clinteList = new ArrayList<Cliente>();
		clinteList.add(buildCliente);
		
		when(cliRepo.findByNumConta("1")).thenReturn(clinteList);
		ClienteDto buscarClienteNumConta = serviceImpl.buscarClienteNumConta("1");
		assertNotNull(buscarClienteNumConta);
	}
	
	@Test
	public void testBuscaTodosClientes() {
		Cliente buildCliente = buildCliente();
		
		List<Cliente> clinteList = new ArrayList<Cliente>();
		clinteList.add(buildCliente);
		
		when(cliRepo.findAll()).thenReturn(clinteList);
		List<ClienteDto> listClientes = serviceImpl.listClientes();
		assertNotNull(listClientes);
	}
	
	@Test
	public void testDebitarValorDaTransacao() {
		
		Cliente buildCliente = buildCliente();
		Cliente buildCliente2 = buildCliente("2", "Melanie", "2", new BigDecimal(100));
		
		when(cliRepo.save(buildCliente)).thenReturn(buildCliente);
		
		Boolean debitarValorDaTransacao = serviceImpl.debitarValorDaTransacao(buildCliente, buildCliente2, new BigDecimal(10));
		assertTrue(!debitarValorDaTransacao);
	}
	
	private ClienteDto buildClienteDto() {
		ClienteDto cliDto =  new ClienteDto();
		cliDto.setId("teste");
		cliDto.setNome("lucas");
		cliDto.setNumConta("188");
		cliDto.setValor(new BigDecimal(100));
		return cliDto;
	}
	
	private Cliente buildCliente() {
		Cliente cli =  new Cliente();
		cli.setId("teste");
		cli.setNome("lucas");
		cli.setNumConta("99");
		cli.setValor(new BigDecimal(100));
		return cli;
	}
	
	private Cliente buildCliente(String id, String nome, String numConta, BigDecimal valor) {
		Cliente cli =  new Cliente();
		cli.setId(id);
		cli.setNome(nome);
		cli.setNumConta(numConta);
		cli.setValor(valor);
		return cli;
	}

}
