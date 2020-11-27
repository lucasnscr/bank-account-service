package br.com.bank.count.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.entity.Transferencia.Status;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.exceptions.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.ClienteService;
import br.com.bank.count.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	private static final Double transferMax = 1000.00;

	private TransferenciaRepository transRepository;
	private ClienteService cliService;

	@Override
	public TransferenciaDto realizaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe,
			BigDecimal valorTransferencia) {
		TransferenciaDto transferenciaDto = null;
		try {
			validateTransfer(clienteEnvia, clienteRecebe, valorTransferencia);

			if (ObjectUtils.isEmpty(clienteEnvia) && ObjectUtils.isEmpty(clienteRecebe)) {
				Boolean debitarValorDaTransacao = cliService.debitarValorDaTransacao(clienteEnvia, clienteRecebe,
						valorTransferencia);
				if (!ObjectUtils.isEmpty(debitarValorDaTransacao)) {

					Transferencia tran = new Transferencia();
					tran.setCliente(clienteEnvia);
					tran.setValor(valorTransferencia);
					tran.setStatus(Status.SUCESSO);
					Transferencia save = transRepository.save(tran);
					if (!ObjectUtils.isEmpty(save)) {
						transferenciaDto = new TransferenciaDto(save);
						return transferenciaDto;
					}
				}
			}

		} catch (BankBussinessException e) {
			TransferenciaDto saveTransactionError = saveTransactionError(clienteEnvia, valorTransferencia);
			return saveTransactionError;
		}

		return transferenciaDto;
	}



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
	
	private TransferenciaDto saveTransactionError(Cliente cliente, BigDecimal valor) {
		Transferencia tran = new Transferencia();
		TransferenciaDto tranResponse = new TransferenciaDto();
		tran.setCliente(cliente);
		tran.setValor(valor);
		tran.setStatus(Status.ERRO);
		Transferencia transferencia = transRepository.save(tran);
		if (!ObjectUtils.isEmpty(transferencia)) {
			tranResponse = new TransferenciaDto(transferencia);
		
			
			
			return tranResponse;
		}
		return null;
	}

	private void validateTransfer(Cliente cliEnvia, Cliente cliRecebe, BigDecimal valor) throws BankBussinessException {

		if (ObjectUtils.isEmpty(cliEnvia)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}

		if (ObjectUtils.isEmpty(cliRecebe)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}

		ClienteDto buscarClienteEnvia = cliService.buscarClienteNumConta(cliEnvia.getNumConta());
		if (ObjectUtils.isEmpty(buscarClienteEnvia)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.NAO_EXISTE_CLIENTE_ENVIO);
		}

		ClienteDto buscarClienteRecebe = cliService.buscarClienteNumConta(cliRecebe.getNumConta());
		if (ObjectUtils.isEmpty(buscarClienteRecebe)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.NAO_EXISTE_CLIENTE_RECEBE);
		}

		if (transferMax < valor.doubleValue()) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.TRANSFERENCIA_LIMITE);
		}

		if (buscarClienteEnvia.getValor().doubleValue() < valor.doubleValue()) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.SALDO_INVALIDO);
		}

	}
	
}
