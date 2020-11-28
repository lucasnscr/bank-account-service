package br.com.bank.count.service;

import org.springframework.http.HttpStatus;

import br.com.bank.count.dto.TransferenciaDto;

public interface TransferenciaRequestService {

	HttpStatus solicitaTransferencia(TransferenciaDto transferenciaDto);

}
