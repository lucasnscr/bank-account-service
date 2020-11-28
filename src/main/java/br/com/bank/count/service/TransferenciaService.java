package br.com.bank.count.service;

import java.util.List;

import br.com.bank.count.dto.TransferenciaDtoResponse;

public interface TransferenciaService {

	List<TransferenciaDtoResponse> listTransferencias(String numConta);
	
}
