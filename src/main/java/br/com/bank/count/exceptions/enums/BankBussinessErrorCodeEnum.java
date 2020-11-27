package br.com.bank.count.exceptions.enums;

import java.util.Properties;

import org.springframework.http.HttpStatus;

public enum BankBussinessErrorCodeEnum implements IErrorCode{
	
	SALDO_INVALIDO("Saldo insuficiente", 1, "BANK-COUNT-SERVICE", "CONSULTA_SALDO", HttpStatus.UNPROCESSABLE_ENTITY),
	DADOS_INVALIDO("DADOS INVALIDOS", 2, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	NAO_EXISTE_CLIENTE_RECEBE("Não existe cliente para receber", 3, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	NAO_EXISTE_CLIENTE_ENVIO("Não existe cliente de envio", 4, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	TRANSFERENCIA_LIMITE("Valor da transferência maior que valor máximo definido", 6, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY);
	
	
	BankBussinessErrorCodeEnum(
			String message,
			Integer codError,
			String origin,
			String operation,
			HttpStatus httpCode){
			this.codError = codError;
			this.httpCode = httpCode;
			this.message = message;
			this.operation = operation;
			this.origin = origin;
			this.properties.put(codError, this);
		
	}
	
	private String message;
	private  Integer codError;
	private  String origin;
	private  String operation;
	private Properties properties;
	private  HttpStatus httpCode;
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public Integer getCodError() {
		return codError;
	}
	
	@Override
	public String getOrigin() {
		return origin;
	}
	
	@Override
	public String getOperation() {
		return operation;
	}
	
	@Override
	public Properties getProperties() {
		return properties;
	}
	
	@Override
	public HttpStatus getHttpCode() {
		return httpCode;
	}
	
	
	
	
	
	

}
