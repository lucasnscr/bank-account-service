package br.com.bank.count.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.entity.Transferencia.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public TransferenciaDto(Transferencia transferencia) {
		this(transferencia.getId(), transferencia.getClienteEnvia(), transferencia.getClienteRecebe(), transferencia.getValor(), transferencia.getStatus(), LocalDateTime.now());
	}
	
	private String id;
	private Cliente clienteEnvia;
	private Cliente clienteRecebe;
	private BigDecimal valor;
	private Status status;
	private LocalDateTime data;
	
}
