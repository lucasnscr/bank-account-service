package br.com.bank.count.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.entity.Transferencia.Status;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.repository.TransferenciaRepository;
import br.com.bank.count.service.ClienteService;
import br.com.bank.count.service.TransferenciaResponseService;
import br.com.bank.count.utils.TransferenciaValidateUtils;

@Service
public class TransferenciaResponseServiceImpl implements TransferenciaResponseService {

	private TransferenciaRepository transRepository;
	private TransferenciaValidateUtils validateUtils;
	private ClienteService cliService;

	@Autowired
	public TransferenciaResponseServiceImpl(TransferenciaRepository transRepository,
			TransferenciaValidateUtils validateUtils, ClienteService cliService) {
		this.transRepository = transRepository;
		this.validateUtils = validateUtils;
		this.cliService = cliService;
	}

	@Override
	public void realizaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia) {
		try {
			validateUtils.validateTransfer(clienteEnvia, clienteRecebe, valorTransferencia);
			if (ObjectUtils.isEmpty(clienteEnvia) && ObjectUtils.isEmpty(clienteRecebe)) {
				Boolean debitarValorDaTransacao = cliService.debitarValorDaTransacao(clienteEnvia, clienteRecebe,
						valorTransferencia);
				if (!ObjectUtils.isEmpty(debitarValorDaTransacao)) {

					Transferencia tran = new Transferencia();
					tran.setClienteEnvia(clienteEnvia);
					tran.setClienteRecebe(clienteRecebe);
					tran.setValor(valorTransferencia);
					tran.setStatus(Status.SUCESSO);
					transRepository.save(tran);
				}
			}

		} catch (BankBussinessException e) {
			saveTransactionError(clienteEnvia, clienteRecebe, valorTransferencia);
		}
	}

	private void saveTransactionError(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor) {
		Transferencia tran = new Transferencia();
		tran.setClienteEnvia(clienteEnvia);
		tran.setClienteRecebe(clienteRecebe);
		tran.setValor(valor);
		tran.setStatus(Status.ERRO);
		transRepository.save(tran);
	}
}
