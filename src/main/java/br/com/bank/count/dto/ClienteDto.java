package br.com.bank.count.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.bank.count.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private String id;

	@NotBlank  @Size(max=100)
	private String nome;

	@NotBlank  @Size(max=20)
	private String numConta;

	@NotNull
	private BigDecimal valor;

	
	public ClienteDto(Cliente c){
		this(c.getId(), c.getNome(), c.getNumConta(), c.getValor());		
	}

}
