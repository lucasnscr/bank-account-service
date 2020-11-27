package br.com.bank.count.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.bank.count.entity.Cliente;
import br.com.bank.count.entity.Transferencia;
import br.com.bank.count.entity.Transferencia.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransferenciaDto implements Serializable{
	
	public TransferenciaDto(Transferencia transferencia) {
		this(transferencia.getId(), transferencia.getCliente(), transferencia.getValor(), transferencia.getStatus(), LocalDateTime.now());
	}

	private static final long serialVersionUID = 1L;
	private Long id;
	private Cliente cliente;
	private BigDecimal valor;
	private Status status;
	private LocalDateTime data;
	
}
