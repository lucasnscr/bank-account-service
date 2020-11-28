package br.com.bank.count.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.TransferenciaDto;
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
	public void realizaTransferencia(TransferenciaDto transferenciaDto) {
		try {
			validateUtils.validateTransfer(transferenciaDto.getClienteEnvia(), transferenciaDto.getClienteRecebe(), transferenciaDto.getValor());
			
			if (transferenciaDto.getClienteEnvia().getValor().doubleValue() < transferenciaDto.getValor().doubleValue()) {
				saveTransactionError(transferenciaDto);
			}else {
				if (!ObjectUtils.isEmpty(transferenciaDto.getClienteEnvia()) && !ObjectUtils.isEmpty(transferenciaDto.getClienteRecebe())) {
					Boolean debitarValorDaTransacao = cliService.debitarValorDaTransacao(transferenciaDto.getClienteEnvia(), transferenciaDto.getClienteRecebe(),
							transferenciaDto.getValor());
					if (!ObjectUtils.isEmpty(debitarValorDaTransacao)) {
						
						Transferencia tran = new Transferencia();
						tran.setClienteEnvia(transferenciaDto.getClienteEnvia());
						tran.setClienteRecebe(transferenciaDto.getClienteRecebe());
						tran.setValor(transferenciaDto.getValor());
						tran.setStatus(Status.SUCESSO);
						transRepository.save(tran);
					}
				}
			}
		} catch (BankBussinessException e) {
			saveTransactionError(transferenciaDto);
		}
	}

	private void saveTransactionError(TransferenciaDto transferenciaDto) {
		Transferencia tran = new Transferencia();
		tran.setClienteEnvia(transferenciaDto.getClienteEnvia());
		tran.setClienteRecebe(transferenciaDto.getClienteRecebe());
		tran.setValor(transferenciaDto.getValor());
		tran.setStatus(Status.ERRO);
		transRepository.save(tran);
	}
}
