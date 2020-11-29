package br.com.bank.count.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.dto.TransferenciaDtoResponse;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.exception.BankException;
import br.com.bank.count.service.TransferenciaRequestService;
import br.com.bank.count.service.TransferenciaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
	

	private TransferenciaService tranService;
	private TransferenciaRequestService tranRequestService;
	
	public TransferenciaController(TransferenciaService tranService, TransferenciaRequestService tranRequestService) {
		this.tranService = tranService;
		this.tranRequestService = tranRequestService;
	}

	@ApiOperation(value = "busca todas as transferencias de um cliente")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 422, message = "Excecao"),
		})
	@GetMapping("/")
	public
	ResponseEntity<List<TransferenciaDtoResponse>> listTransferencias(@RequestParam("numConta") String numConta) {
		try {
			List<TransferenciaDtoResponse> transferenciaDtoList = tranService.listTransferencias(numConta);
			return ResponseEntity.ok(transferenciaDtoList);
			
		} catch (BankBussinessException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

	@ApiOperation(value = "Posta na fila uma mensagem do Evento de Transferencia")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "OK"),
		    @ApiResponse(code = 422, message = "Excecao"),
		})
	@PostMapping
	public
	HttpStatus realizaTransferencia(@Valid @RequestBody TransferenciaDto transferenciaDto,  UriComponentsBuilder uriBuilder) {
		try {
			HttpStatus solicitaTransferencia = tranRequestService.solicitaTransferencia(transferenciaDto);
			return solicitaTransferencia;
		} catch (BankBussinessException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		} catch (BankException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

}
