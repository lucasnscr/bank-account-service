package br.com.bank.count.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.bank.count.exception.BankException;

@ControllerAdvice
public class BankErrorAdvice {
	
	@ExceptionHandler(BankException.class)
	public ResponseEntity<ServiceError> handleBankBussinessException(BankException e){
		ServiceError error = new ServiceError(e.getCodError(), e.getMessage(), e.getOrigin(), e.getOperation());
		return new ResponseEntity<>(error, e.getHttpStatus());
	}
	

}
