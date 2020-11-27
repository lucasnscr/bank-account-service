package br.com.bank.count.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cliente")
@RestController
public interface IClienteController {

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	
	@ApiOperation(value = "Endpoint que busca todos os clientes")
	@ApiResponse(code= 200, message="Busca realizada com sucesso")
	@GetMapping("/todos")
	ResponseEntity<List<ClienteDto>> listCliente();

	@ApiOperation(value = "Endpoint que busca por conta")
	@ApiResponse(code= 200, message="Busca realizada com sucesso")
	@GetMapping("/")
	ResponseEntity<ClienteDto> buscaPorNumConta(@PathVariable("id") Integer numConta);

	@ApiOperation(value = "Endpoint que cadastra clientes")
	@ApiResponse(code= 201, message="Cadastro realizada com sucesso")
	@PostMapping
	ResponseEntity<ClienteDto> cadastraCliente(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder);

}
