package br.com.bank.count.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@RedisHash("Cliente")
public class Cliente implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@Indexed
	private String numConta;
	
	private String nome;
	private BigDecimal valor;


}
