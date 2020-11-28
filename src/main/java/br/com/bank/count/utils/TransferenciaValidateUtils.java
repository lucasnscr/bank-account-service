package br.com.bank.count.utils;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.com.bank.count.dto.ClienteDto;
import br.com.bank.count.entity.Cliente;
import br.com.bank.count.enums.BankBussinessErrorCodeEnum;
import br.com.bank.count.exception.BankBussinessException;
import br.com.bank.count.service.ClienteService;

@Component
public class TransferenciaValidateUtils {
	
	private static final Double transferMax = 1000.00;
	
	private ClienteService cliService;
	
	public TransferenciaValidateUtils(ClienteService cliService) {
		this.cliService = cliService;
	}
	
	public void validateTransfer(Cliente cliEnvia, Cliente cliRecebe, BigDecimal valor) {

		if (ObjectUtils.isEmpty(cliEnvia)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}

		if (ObjectUtils.isEmpty(cliRecebe)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.DADOS_INVALIDO);
		}

		ClienteDto buscarClienteEnvia;
		buscarClienteEnvia = cliService.buscarClienteNumConta(cliEnvia.getNumConta());
		if (ObjectUtils.isEmpty(buscarClienteEnvia)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.NAO_EXISTE_CLIENTE_ENVIO);
		}

		ClienteDto buscarClienteRecebe;
		buscarClienteRecebe = cliService.buscarClienteNumConta(cliRecebe.getNumConta());
		if (ObjectUtils.isEmpty(buscarClienteRecebe)) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.NAO_EXISTE_CLIENTE_RECEBE);
		}

		if (transferMax < valor.doubleValue()) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.TRANSFERENCIA_LIMITE);
		}

		if (buscarClienteEnvia.getValor().doubleValue() < valor.doubleValue()) {
			throw new BankBussinessException(BankBussinessErrorCodeEnum.SALDO_INVALIDO);
		}

	}

}
