package br.com.bank.count.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	

	private ClienteService cliService;
	
	@Autowired
	public ClienteController(ClienteService cliService) {
		this.cliService = cliService;
	}
	
	@GetMapping("/todos")
	public
	ResponseEntity<List<ClienteDto>> listCliente() {
		try {
			List<ClienteDto> listClientes = cliService.listClientes();
			return ResponseEntity.ok(listClientes);
		} catch (BankBussinessException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

	@GetMapping("/")
	public
	ResponseEntity<ClienteDto> buscaPorNumConta(@RequestParam("numConta") String numConta) {
		try {
			ClienteDto buscaClienteNumConta = cliService.buscarClienteNumConta(numConta);
			return ResponseEntity.ok(buscaClienteNumConta);
			
		} catch (BankBussinessException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

	@ExceptionHandler({ BankBussinessException.class})
	@PostMapping
	public
	ResponseEntity<String> cadastraCliente(@Valid @RequestBody ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
		try {
			String cadastraCliente = cliService.cadastraCliente(clienteDto);
			ResponseEntity<String> responseEntity = new ResponseEntity<>(cadastraCliente,HttpStatus.CREATED);
			responseEntity.created(uriBuilder.path("/Clientes/{id}").buildAndExpand(cadastraCliente).toUri());
			return responseEntity;
			
		} catch (BankBussinessException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

}
