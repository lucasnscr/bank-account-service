package br.com.bank.count.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash("Transferencia")
public class Transferencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static enum Status {
		SUCESSO, ERRO;
	}
	
	@Id
	private Long id;
	private Cliente cliente;
	private BigDecimal valor;
	private Status status;
	private LocalDateTime data;
	
	
	

}
