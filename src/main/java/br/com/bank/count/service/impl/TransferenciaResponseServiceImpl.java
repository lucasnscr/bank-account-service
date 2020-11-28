package br.com.bank.count.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.entity.Transferencia.Status;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.ClienteService;
import br.com.bank.count.service.TransferenciaResponseService;

@Service
public class TransferenciaResponseServiceImpl implements TransferenciaResponseService {

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
					tran.setClienteEnvia(clienteEnvia);
					tran.setClienteRecebe(clienteRecebe);
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
			TransferenciaDto saveTransactionError = saveTransactionError(clienteEnvia, clienteRecebe, valorTransferencia);
			return saveTransactionError;
		}

		return transferenciaDto;
	}

	private TransferenciaDto saveTransactionError(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor) {
		Transferencia tran = new Transferencia();
		TransferenciaDto tranResponse = new TransferenciaDto();
		tran.setClienteEnvia(clienteEnvia);
		tran.setClienteRecebe(clienteRecebe);
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
