package br.com.bank.count.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.bank.count.dto.TransferenciaDtoResponse;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.repository.ClienteRepository;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	private TransferenciaRepository transRepository;
	private ClienteRepository cliRepository;
	
	@Autowired
	public TransferenciaServiceImpl(TransferenciaRepository transRepository, ClienteRepository cliRepository) {
		this.transRepository = transRepository;
		this.cliRepository = cliRepository;
	}

	@Override
	public List<TransferenciaDtoResponse> listTransferencias(String numConta) {
		List<TransferenciaDtoResponse> transferenciaDtoResponse = null;
		List<Cliente> clinteList = cliRepository.findByNumConta(numConta);
		if (!CollectionUtils.isEmpty(clinteList)) {
			Cliente cliente = clinteList.get(0);
			Iterable<Transferencia> transferenciaIt = transRepository.findAll();
			if (null != transferenciaIt) {
				List<Transferencia> list = toList(transferenciaIt);
				transferenciaDtoResponse = new ArrayList<TransferenciaDtoResponse>();
				for (Transferencia transferencia : list) {
					if (transferencia.getClienteEnvia().equals(cliente)) {
						TransferenciaDtoResponse response = new TransferenciaDtoResponse();
						response.setId(transferencia.getId());
						response.setClienteEnvia(transferencia.getClienteEnvia());
						response.setClienteRecebe(transferencia.getClienteRecebe());
						response.setStatus(transferencia.getStatus());
						response.setValor(transferencia.getValor());
						transferenciaDtoResponse.add(response);
					}
				}
				return transferenciaDtoResponse;
			}
			
		}else {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}
		return transferenciaDtoResponse;
	}
	
	
	private static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}
	
	
}
