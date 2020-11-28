package br.com.bank.count.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.ClienteService;
import br.com.bank.count.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	private TransferenciaRepository transRepository;
	private ClienteService cliService;

	@Override
	public List<TransferenciaDto> listTransferencias(Integer numConta) {
		List<TransferenciaDto> transferenciaDto = null;
		ClienteDto clienteDto = cliService.buscarClienteNumConta(numConta);
		if (!ObjectUtils.isEmpty(clienteDto)) {
			Cliente cli = new Cliente(clienteDto.getId(), clienteDto.getNumConta(), clienteDto.getNome(), clienteDto.getValor());
			List<Transferencia> findByTransferenciaList = transRepository.findByCliente(cli);
			if (CollectionUtils.isEmpty(findByTransferenciaList)) {
				transferenciaDto = new ArrayList<TransferenciaDto>();
				for (Transferencia transferencia : findByTransferenciaList) {
					transferenciaDto.add(new TransferenciaDto(transferencia));
				}
				return transferenciaDto;
			}
			
		}else {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}
		return transferenciaDto;
	}
	
	
	
}
