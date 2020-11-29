package br.com.bank.count.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import br.com.bank.count.enums.EventEnum;

public interface EventBidingService {
	
	
	/**
	 * Metodo que realiza o mapeamento da mensagem de saida
	 * @return MessageChannel
	 */
	@Output(EventEnum.OUTPUT)
	MessageChannel saida();
	
	
	/**
	 * Metodo que realiza o mapeamento da mensagem de saida
	 * @return MessageChannel
	 */
	@Input(EventEnum.INPUT)
	SubscribableChannel entrada();

}
