package br.com.bank.count.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

import br.com.bank.count.entity.Cliente;

public interface TransferenciaRequestService {

	HttpStatus solicitaTransferencia(Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor);

}
