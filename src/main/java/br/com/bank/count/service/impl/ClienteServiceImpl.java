package br.com.bank.count.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.repository.ClienteRepository;
import br.com.bank.count.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository cliRepo;

	@Autowired
	public ClienteServiceImpl(ClienteRepository cliRepo) {
		this.cliRepo = cliRepo;
	}

	@Override
	public String cadastraCliente(ClienteDto clienteDto) {
		String cliDto = null;
		if (!ObjectUtils.isEmpty(clienteDto)) {
			List<Cliente> clinteList = cliRepo.findByNumConta(clienteDto.getNumConta());
			if (CollectionUtils.isEmpty(clinteList)) {
				Cliente cliente = new Cliente();
				cliente.setNome(clienteDto.getNome());
				cliente.setNumConta(clienteDto.getNumConta());
				cliente.setValor(clienteDto.getValor());
				Cliente cli = cliRepo.save(cliente);
				if (!ObjectUtils.isEmpty(cli)) {
					cliDto = cli.getId();
				} else {
					throw new BankBussinessException(BankBussinessErrorCodeEnum.ERRO_GRAVAR_DADO);
				}

			} else {
				throw new BankBussinessException(BankBussinessErrorCodeEnum.CLIENTE_EXISTENTE);
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
			clienteDtoList = new ArrayList<ClienteDto>();
			if (!CollectionUtils.isEmpty(clienteList)) {
				for (Cliente cliente : clienteList) {
					clienteDtoList.add(new ClienteDto(cliente));
				}

			}
		} else {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.BUSCA_SEM_RESULTADO);
		}

		return clienteDtoList;
	}

	@Override
	public ClienteDto buscarClienteNumConta(String numConta) {
		ClienteDto cliDto = null;
		if (!ObjectUtils.isEmpty(numConta)) {
			List<Cliente> clinteList = cliRepo.findByNumConta(numConta);
			if (!CollectionUtils.isEmpty(clinteList)) {
				Cliente cliente = clinteList.get(0);
				cliDto = new ClienteDto(cliente);
			}else {
				throw new BankBussinessException(BankBussinessErrorCodeEnum.BUSCA_SEM_RESULTADO);
			}
		} else {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
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

	private static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

}
