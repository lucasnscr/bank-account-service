package br.com.bank.count.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		List<Cliente> cliList = cliRepository.findByNumConta(numConta);
		if (!CollectionUtils.isEmpty(cliList)) {
			Cliente cliente = cliList.get(0);
			List<Transferencia> findByTransferenciaList = transRepository.findByCliente(cliente);
			if (CollectionUtils.isEmpty(findByTransferenciaList)) {
				transferenciaDtoResponse = new ArrayList<TransferenciaDtoResponse>();
				for (Transferencia transferencia : findByTransferenciaList) {
					TransferenciaDtoResponse response = new TransferenciaDtoResponse();
					response.setId(transferencia.getId());
					response.setClienteEnvia(transferencia.getClienteEnvia());
					response.setClienteRecebe(transferencia.getClienteRecebe());
					response.setStatus(transferencia.getStatus());
					response.setValor(transferencia.getValor());
					transferenciaDtoResponse.add(response);
				}
				return transferenciaDtoResponse;
			}
			
		}else {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}
		return transferenciaDtoResponse;
	}
	
	
	
}
