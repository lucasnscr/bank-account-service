package br.com.bank.count.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.repository.ClienteRepository;
import br.com.bank.count.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	private ClienteRepository cliRepo;

	@Override
	public ClienteDto cadastraCliente(Cliente cliente) {
		ClienteDto cliDto = null;
		if (!ObjectUtils.isEmpty(cliente)) {
			Cliente cli = cliRepo.save(cliente);
			if (ObjectUtils.isEmpty(cli)) {
				cliDto = new ClienteDto(cli);
			}
		}
		return cliDto;
	}

	@Override
	public List<ClienteDto> listClientes() {
		List<ClienteDto> clienteDtoList = null;
		Iterable<Cliente> clienteIt = cliRepo.findAll();
		if (null != clienteIt) {
			List<Cliente> clienteList = toList(clienteIt);
			if (!CollectionUtils.isEmpty(clienteList)) {
				for (Cliente cliente : clienteList) {
					clienteDtoList.add(new ClienteDto(cliente));
				}
				
			}
		}
		
		
		return clienteDtoList;
	}

	@Override
	public ClienteDto buscarClienteNumConta(Integer numConta) {
		ClienteDto cliDto = null;
		if (!ObjectUtils.isEmpty(numConta)) {
			Cliente cliente = cliRepo.findByNumConta(numConta);
			if (!ObjectUtils.isEmpty(cliente)) {
				cliDto =  new ClienteDto(cliente);
			}
		}
		return cliDto;
	}


	@Override
	public Boolean debitarValorDaTransacao(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia) {
		
		int saldoDescontado = Math.subtractExact(clienteEnvia.getValor().intValue(), valorTransferencia.intValue());
		BigDecimal novoSaldoEnvia = new BigDecimal(saldoDescontado);
		clienteEnvia.setValor(novoSaldoEnvia);
		Cliente saveEnvia = cliRepo.save(clienteEnvia);
		
		int saldoRecebe = Math.addExact(clienteRecebe.getValor().intValue(), valorTransferencia.intValue());
		BigDecimal novoSaldoRecebe = new BigDecimal(saldoRecebe);
		clienteRecebe.setValor(novoSaldoRecebe);
		Cliente saveRecebe = cliRepo.save(clienteRecebe);
		
		
		if (ObjectUtils.isEmpty(saveEnvia) && ObjectUtils.isEmpty(saveRecebe)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
		
	}

	public static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toList());
	}

	

}
