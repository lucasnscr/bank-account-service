package br.com.bank.count.service;

import java.util.List;

import br.com.bank.count.dto.TransferenciaDtoResponse;

public interface TransferenciaService {
	
	/**
	 * Metodo que lista todas as transferencias de um cliente, busca realizada pelo numero da conta
	 * @param numConta
	 * @return List<TransferenciaDtoResponse>
	 */
	List<TransferenciaDtoResponse> listTransferencias(String numConta);
	
}
