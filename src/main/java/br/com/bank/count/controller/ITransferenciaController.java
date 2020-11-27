package br.com.bank.count.controller;

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

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.service.TransferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Transferencias")
@RestController
public interface ITransferenciaController {

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	@ApiOperation(value = "Endpoint que busca todas as transferencias de um cliente")
	@ApiResponse(code = 200, message = "busca por transferencias com sucesso")
	@GetMapping("/")
	ResponseEntity<List<TransferenciaDto>> listTransferencias(@PathVariable("numConta") Integer numConta);

	@ApiOperation(value = "Endpoint que realiza transferencia")
	@ApiResponse(code= 200, message="transferencia concluida com sucesso")
	@PostMapping
	ResponseEntity<TransferenciaDto> realizaTransferencia(@RequestBody Cliente clienteEnvia, Cliente clienteRecebe,
			BigDecimal valor, UriComponentsBuilder uriBuilder);
}
