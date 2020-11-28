package br.com.bank.count.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import br.com.bank.count.enums.EventEnum;

public interface EventBidingService {
	
	@Output(EventEnum.OUTPUT)
	MessageChannel saida();
	
	@Input(EventEnum.INPUT)
	SubscribableChannel entrada();

}
