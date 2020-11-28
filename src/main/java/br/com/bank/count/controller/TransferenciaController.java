package br.com.bank.count.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.service.TransferenciaRequestService;
import br.com.bank.count.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
	

	private TransferenciaService tranService;
	private TransferenciaRequestService tranRequestService;
	
	public TransferenciaController(TransferenciaService tranService, TransferenciaRequestService tranRequestService) {
		this.tranService = tranService;
		this.tranRequestService = tranRequestService;
	}

	@GetMapping("/")
	public
	ResponseEntity<List<TransferenciaDto>> listTransferencias(@PathVariable("numConta") String numConta) {
		List<TransferenciaDto> transferenciaDtoList = tranService.listTransferencias(numConta);
		return ResponseEntity.ok(transferenciaDtoList);
	}

	@PostMapping
	public
	HttpStatus realizaTransferencia(@Valid @RequestBody Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor,  UriComponentsBuilder uriBuilder) {
		HttpStatus solicitaTransferencia = tranRequestService.solicitaTransferencia(clienteEnvia, clienteRecebe, valor);
		return solicitaTransferencia;
	}

}
