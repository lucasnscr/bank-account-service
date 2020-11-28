package br.com.bank.count.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.enums.EventEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.exception.BankException;
import br.com.bank.count.service.EventBidingService;
import br.com.bank.count.service.TransferenciaRequestService;
import br.com.bank.count.utils.TransferenciaValidateUtils;

@EnableBinding(EventBidingService.class)
public class TransferenciaRequestServiceImpl implements TransferenciaRequestService {
	
	private EventBidingService serviceBiding;
	private TransferenciaValidateUtils validateUtils;
	
	@Autowired
	public TransferenciaRequestServiceImpl(EventBidingService serviceBiding, TransferenciaValidateUtils validateUtils) {
		this.serviceBiding = serviceBiding;
		this.validateUtils = validateUtils;
	}
	
	@Transactional
	@Override
	public HttpStatus solicitaTransferencia(TransferenciaDto transferenciaDto) {
		try {
			if (!ObjectUtils.isEmpty(transferenciaDto)) {
				validateUtils.validateTransfer(transferenciaDto.getClienteEnvia(), transferenciaDto.getClienteRecebe(), transferenciaDto.getValor());
				serviceBiding
				.saida()
				.send(
						MessageBuilder
						.withPayload(transferenciaDto).setHeaderIfAbsent(EventEnum.EVENT_ID, EventEnum.EVENT_TRANSFERENCIA.getDescricao()).build());
				return HttpStatus.OK;
				
			}else {
				throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
			}
		} catch (BankBussinessException e) {
			throw new BankException(e.getMessage(), e.getCodError(), e.getOperation(), e.getOrigin(), e.getHttpStatus());
		}
	}
}

