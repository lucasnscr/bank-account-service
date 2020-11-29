package br.com.bank.count.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bank.count.service.EventBidingService;
import br.com.bank.count.utils.TransferenciaValidateUtils;

@ExtendWith(MockitoExtension.class)
public class TransferenciaRequestServiceImplTest  {

	@Mock
	private EventBidingService serviceBiding;
	
	@Mock
	private TransferenciaValidateUtils validateUtils;
	
	@InjectMocks
	private TransferenciaRequestServiceImpl serviceImpl;
	
	@Test
	public void testConstructor() {
		serviceImpl = new TransferenciaRequestServiceImpl(serviceBiding, validateUtils);
		assertNotNull(serviceImpl, "not be null");
	}
	
	
}
