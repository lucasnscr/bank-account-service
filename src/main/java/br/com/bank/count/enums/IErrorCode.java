package br.com.bank.count.enums;

import org.springframework.http.HttpStatus;

public interface IErrorCode {
	
	String getMessage();
	Integer getCodError();
	String getOrigin();
	String getOperation();
	HttpStatus getHttpCode();

}
