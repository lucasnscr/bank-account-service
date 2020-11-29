package br.com.bank.count.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bank.count.dto.TransferenciaDtoResponse;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.repository.ClienteRepository;
import br.com.bank.count.repository.TransferenciaRepository;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceImplTest {
	
	@Mock
	private TransferenciaRepository transRepository;
	
	@Mock
	private ClienteRepository cliRepository;
	
	@InjectMocks
	private TransferenciaServiceImpl serviceImpl;
	
	@Test
	public void testConstructor() {
		serviceImpl = new TransferenciaServiceImpl(transRepository, cliRepository);
	}
	
	@Test
	public void testlistTransferencias() {
		Cliente buildCliente = buildCliente("1", "Lucas", "1", new BigDecimal(10));
		List<Cliente> value = new ArrayList<Cliente>();
		value.add(buildCliente);
		when(cliRepository.findByNumConta("1")).thenReturn(value);
		List<TransferenciaDtoResponse> listTransferencias = serviceImpl.listTransferencias("1");
		assertNotNull(listTransferencias);
	}
	
	private Cliente buildCliente(String id, String nome, String numConta, BigDecimal valor) {
		Cliente cli = new Cliente();
		cli.setId(id);
		cli.setNome(nome);
		cli.setNumConta(numConta);
		cli.setValor(valor);
		return cli;
	}
	

}
