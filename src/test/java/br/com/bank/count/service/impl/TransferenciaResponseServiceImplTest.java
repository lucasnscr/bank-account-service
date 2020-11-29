package br.com.bank.count.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.ClienteService;
import br.com.bank.count.utils.TransferenciaValidateUtils;

@ExtendWith(MockitoExtension.class)
public class TransferenciaResponseServiceImplTest {
	@Mock
	private TransferenciaRepository transRepository;

	@Mock
	private TransferenciaValidateUtils validateUtils;

	@Mock
	private ClienteService cliService;

	@InjectMocks
	private TransferenciaResponseServiceImpl serviceImpl;

	@Test
	public void testConstructor() {
		serviceImpl = new TransferenciaResponseServiceImpl(transRepository, validateUtils, cliService);
		assertNotNull(serviceImpl, "not be null");
	}

	@Test
	public void testRealizaTransferencia() {

		Transferencia transferencia = new Transferencia();
		
		Cliente buildCliente = buildCliente("1", "lucas", "1", new BigDecimal(1000));
		Cliente buildCliente2 = buildCliente("2", "Melanie", "2", new BigDecimal(1000));
		TransferenciaDto buildTransferDto = buildTransferDto(buildCliente, buildCliente2);
		serviceImpl.realizaTransferencia(buildTransferDto);

	}

	private TransferenciaDto buildTransferDto(Cliente clienteEnvia, Cliente clienteRecebe) {

		TransferenciaDto dto = new TransferenciaDto();
		dto.setValor(new BigDecimal(10));
		dto.setClienteEnvia(clienteEnvia);
		dto.setClienteRecebe(clienteRecebe);
		return dto;
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
