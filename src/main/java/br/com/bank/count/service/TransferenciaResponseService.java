package br.com.bank.count.service;

import java.math.BigDecimal;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;

public interface TransferenciaResponseService {

	TransferenciaDto realizaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valorTransferencia);

}
