package br.com.bank.count.service;

import org.springframework.http.HttpStatus;

import br.com.bank.count.dto.TransferenciaDto;

public interface TransferenciaRequestService {

	/**
	 * Metodo que envia um evento de transferencia para o Message Broker
	 * @param transferenciaDto
	 * @return HttpStatus
	 */
	HttpStatus solicitaTransferencia(TransferenciaDto transferenciaDto);

}
