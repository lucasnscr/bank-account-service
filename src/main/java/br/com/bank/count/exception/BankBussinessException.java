package br.com.bank.count.exception;

import br.com.bank.count.enums.IErrorCode;

public class BankBussinessException extends BankException {

	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da Excecao criada para o modelo de negocio do servico
	 * @param e
	 */
	public BankBussinessException(IErrorCode e) {
		super(e);
	}

	

}
