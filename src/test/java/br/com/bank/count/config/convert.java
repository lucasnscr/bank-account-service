package br.com.bank.count.config;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bank.count.dto.TransferenciaDto;
import br.com.bank.count.entity.Cliente;

public class convert {

	public static void main(String[] args) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			
			Cliente cliEnvia = new Cliente();
			
			cliEnvia.setId("e9a54a9f-5c34-4947-b6ac-8a40320bd7d2");
			cliEnvia.setNome("Melanie");
			cliEnvia.setNumConta("3");
			cliEnvia.setValor(new BigDecimal(1000.00));
			
			
			Cliente cliRecebe = new Cliente();
			cliRecebe.setId("6753334683531074454");
			cliRecebe.setNome("Lucas");
			cliRecebe.setNumConta("2");
			cliRecebe.setValor(new BigDecimal(1000.00));
			
			
		  TransferenciaDto transferenciaDto = new TransferenciaDto();
		  transferenciaDto.setClienteEnvia(cliEnvia);
		  transferenciaDto.setClienteRecebe(cliRecebe);
		  transferenciaDto.setValor(new BigDecimal(500));
		String json = mapper.writeValueAsString(transferenciaDto);
		  System.out.println("ResultingJSONstring = " + json);
		  //System.out.println(json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}

	}

}
