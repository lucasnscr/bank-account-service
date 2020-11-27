package br.com.bank.count.exceptions.enums;

import java.util.Properties;

import org.springframework.http.HttpStatus;

public interface IErrorCode {
	
	String getMessage();
	Integer getCodError();
	String getOrigin();
	String getOperation();
	Properties getProperties();
	HttpStatus getHttpCode();

}
