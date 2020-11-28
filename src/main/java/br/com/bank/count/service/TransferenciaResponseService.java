package br.com.bank.count.service;

import java.math.BigDecimal;

import br.com.bank.count.entity.Cliente;

public interface TransferenciaResponseService {

	void realizaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia);

}
