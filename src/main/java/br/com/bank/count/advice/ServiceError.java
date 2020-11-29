package br.com.bank.count.advice;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bank.count.enums.IErrorCode;
import lombok.Data;

@Data
public class ServiceError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("code")
	private Integer code = null;

	@JsonProperty("message")
	private String message = null;
	
	@JsonProperty("info")
	private String info = null;
	
	@JsonProperty("time")
	private String time = Instant.now().toString();
	
	ServiceError(IErrorCode errorcode){
		this.code = errorcode.getCodError();
		this.message = errorcode.getMessage();
		this.info = errorcode.getOrigin() + " - " + errorcode.getOperation();
		this.time = Instant.now().toString();
		
	}

	ServiceError(Integer codError, String message, String origin, String operation) {
		this.code = codError;
		this.message = message;
		this.info = origin + " - " + operation;
		this.time = Instant.now().toString();
	}
}
