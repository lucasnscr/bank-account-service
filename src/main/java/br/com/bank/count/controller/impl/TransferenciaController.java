package br.com.bank.count.controller.impl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.controller.ITransferenciaController;
import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.service.TransferenciaService;

@RestController
@RequestMapping("/Transferencia")
public class TransferenciaController implements ITransferenciaController {
	

	private TransferenciaService tranService;
	
	@GetMapping("/")
	public
	ResponseEntity<List<TransferenciaDto>> listTransferencias(@PathVariable("numConta") Integer numConta) {
		List<TransferenciaDto> transferenciaDtoList = tranService.listTransferencias(numConta);
		return ResponseEntity.ok(transferenciaDtoList);
	}

	@PostMapping
	public
	ResponseEntity<TransferenciaDto> realizaTransferencia(@RequestBody Cliente clienteEnvia, Cliente clienteRecebe, BigDecimal valor,  UriComponentsBuilder uriBuilder) {
		TransferenciaDto transferenciaDto = tranService.realizaTransferencia(clienteEnvia, clienteRecebe, valor);
		URI path = uriBuilder.path("/Transferencia/{id}").buildAndExpand(transferenciaDto.getId()).toUri();
		return ResponseEntity.created(path).body(transferenciaDto);
	}

}
