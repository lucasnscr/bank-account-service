package br.com.bank.count.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bank.count.controller.IClienteController;
import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.service.ClienteService;

@RestController
@RequestMapping("/Clientes")
public class ClienteController implements IClienteController {
	

	private ClienteService cliService;
	
	@GetMapping("/todos")
	public
	ResponseEntity<List<ClienteDto>> listCliente() {
		List<ClienteDto> listClientes = cliService.listClientes();
		return ResponseEntity.ok(listClientes);
	}

	@GetMapping("/")
	public
	ResponseEntity<ClienteDto> buscaPorNumConta(@PathVariable("id") Integer numConta) {
		ClienteDto buscaClienteNumConta = cliService.buscarClienteNumConta(numConta);
		return ResponseEntity.ok(buscaClienteNumConta);
	}

	@PostMapping
	public
	ResponseEntity<ClienteDto> cadastraCliente(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
		ClienteDto cadastraCliente = cliService.cadastraCliente(cliente);
		ResponseEntity<ClienteDto> responseEntity = new ResponseEntity<>(cadastraCliente,HttpStatus.CREATED);
		responseEntity.created(uriBuilder.path("/Clientes/{id}").buildAndExpand(cadastraCliente.getId()).toUri());
		return responseEntity;
	}

}
