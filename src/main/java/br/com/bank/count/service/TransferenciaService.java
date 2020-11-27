package br.com.bank.count.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;

public interface TransferenciaService {

	TransferenciaDto realizaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia);
	List<TransferenciaDto> listTransferencias(Integer numConta);

}
