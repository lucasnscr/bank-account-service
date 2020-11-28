package br.com.bank.count.enums;

import org.springframework.http.HttpStatus;

public enum BankBussinessErrorCodeEnum implements IErrorCode{
	
	SALDO_INVALIDO("Saldo insuficiente", 1, "BANK-COUNT-SERVICE", "CONSULTA_SALDO", HttpStatus.UNPROCESSABLE_ENTITY),
	DADOS_INVALIDO("DADOS INVALIDOS", 2, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	NAO_EXISTE_CLIENTE_RECEBE("Nao existe cliente para receber", 3, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	NAO_EXISTE_CLIENTE_ENVIO("Nao existe cliente de envio", 4, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	TRANSFERENCIA_LIMITE("Valor da transferência maior que valor máximo definido", 6, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	ERRO_PROCESSAR_MENSAGEM("Erro ao processar mensagem", 7, "BANK-COUNT-SERVICE", "TRANSFERENCIA", HttpStatus.UNPROCESSABLE_ENTITY),
	ERRO_GRAVAR_DADO("Erro ao gravar o dado no banco", 8, "BANK-COUNT-SERVICE", "CADASTRO-CLIENTE", HttpStatus.UNPROCESSABLE_ENTITY),
	CLIENTE_EXISTENTE("Cliente ja existe", 9, "BANK-COUNT-SERVICE", "CADASTRO-CLIENTE", HttpStatus.UNPROCESSABLE_ENTITY),
	BUSCA_SEM_RESULTADO("A consulta nao obteve resultado", 10, "BANK-COUNT-SERVICE", "Consulta", HttpStatus.UNPROCESSABLE_ENTITY);
	
	
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
		
	}
	
	private String message;
	private  Integer codError;
	private  String origin;
	private  String operation;
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
	public HttpStatus getHttpCode() {
		return httpCode;
	}
	
	
	
	
	
	

}
