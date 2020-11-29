package br.com.bank.count.service;

import br.com.bank.count.dto.TransferenciaDto;

public interface TransferenciaResponseService {
	
	/**
	 * Metodo que recebe uma mensagem sobre o evento de transferencia
	 * @param transferenciaDto
	 */
	void realizaTransferencia(TransferenciaDto transferenciaDto);

}
