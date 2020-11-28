package br.com.bank.count.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia.Status;
import br.com.bank.count.enums.EventEnum;
import br.com.bank.count.service.EventBidingService;
import br.com.bank.count.service.TransferenciaRequestService;

@EnableBinding(EventBidingService.class)
public class TransferenciaRequestServiceImpl implements TransferenciaRequestService {
	
	private EventBidingService serviceBiding;
	
	@Autowired
	public TransferenciaRequestServiceImpl(EventBidingService serviceBiding) {
		this.serviceBiding = serviceBiding;
	}
	
	@Override
	public HttpStatus solicitaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor) {
		
		TransferenciaDto transferenciaDto = new TransferenciaDto();
		transferenciaDto.setClienteEnvia(clienteEnvia);
		transferenciaDto.setClienteRecebe(clienteRecebe);
		transferenciaDto.setData(LocalDateTime.now());
		transferenciaDto.setValor(valor);
		transferenciaDto.setStatus(Status.SUCESSO);
		
		serviceBiding
		.saida()
			.send(
					MessageBuilder
					.withPayload(transferenciaDto).setHeaderIfAbsent(EventEnum.EVENT_ID, EventEnum.EVENT_TRANSFERENCIA.getDescricao()).build());
		
		return null;
	}

}
