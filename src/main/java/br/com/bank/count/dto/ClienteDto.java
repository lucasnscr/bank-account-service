package br.com.bank.count.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.bank.count.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClienteDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank  @Size(max=100)
	private String nome;

	@NotBlank  @Size(max=19)
	private Integer numConta;

	@NotNull
	private BigDecimal valor;

	
	public ClienteDto(Cliente c){
		this(c.getId(), c.getNome(), c.getNumConta(), c.getValor());		
		
		
	}

}
