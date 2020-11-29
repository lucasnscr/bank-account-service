package br.com.bank.count.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.bank.count.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Cliente clienteEnvia;
	private Cliente clienteRecebe;
	private BigDecimal valor;
	
}
