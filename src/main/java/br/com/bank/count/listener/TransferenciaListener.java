package br.com.bank.count.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.enums.EventEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.service.EventBidingService;
import br.com.bank.count.service.TransferenciaResponseService;

@Component
@EnableBinding(EventBidingService.class)
public class TransferenciaListener {
	
	private TransferenciaResponseService tranResponseService;
	
	public TransferenciaListener(TransferenciaResponseService tranResponseService) {
		this.tranResponseService = tranResponseService;
	}
	
	
	@StreamListener(target = EventEnum.INPUT, condition = "headers['TRANSFERENCIA']=='EVENT_TRANSFERENCIA'")
	public void getTransferencia(Message<TransferenciaDto> msgTransferenciaDto) {
		
		final TransferenciaDto payload = msgTransferenciaDto.getPayload();
		
		try {
			tranResponseService.realizaTransferencia(payload.getClienteEnvia(), payload.getClienteRecebe(), payload.getValor());
		} catch (BankBussinessException e) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.ERRO_PROCESSAR_MENSAGEM);
		}
	}

}
