package br.com.bank.count.exception;

import java.lang.reflect.Constructor;

import org.springframework.http.HttpStatus;

import br.com.bank.count.exceptions.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exceptions.enums.IErrorCode;
import lombok.AllArgsConstructor;

public class BankBussinessException extends BankException {

	private static final long serialVersionUID = 1L;
	
	public BankBussinessException(IErrorCode e) {
		super(e);
	}

	

}
