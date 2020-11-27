package br.com.bank.count.exception;

import java.lang.reflect.Constructor;

import org.springframework.http.HttpStatus;

import br.com.bank.count.exceptions.enums.IErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

	private Integer codError;
	
	private String origin;
	
	private String operation;
	
	private  HttpStatus httpStatus;
	
	
	/**
	 * 
	 * BankException {@link Constructor} with throwable
	 * 
	 * @param message
	 * @param throwable
	 */
	
	public BankException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public BankException(IErrorCode e) {
		super(e.getMessage());
		this.message = e.getMessage();
		this.codError = e.getCodError();
		this.httpStatus = e.getHttpCode();
		this.origin = e.getOrigin();
		this.operation = e.getOperation();
		
		
	}


	

}
