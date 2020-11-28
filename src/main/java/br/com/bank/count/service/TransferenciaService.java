package br.com.bank.count.service;

import java.util.List;

import br.com.bank.count.dto.TransferenciaDto;

public interface TransferenciaService {

	List<TransferenciaDto> listTransferencias(String numConta);
	
}
