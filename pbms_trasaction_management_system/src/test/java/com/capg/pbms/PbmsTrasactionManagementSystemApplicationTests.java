package com.capg.pbms;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;

import com.capg.pbms.exception.InsufficientBalanceException;
import com.capg.pbms.model.Cheque;
import com.capg.pbms.service.ITransactionService;

@SpringBootTest
class PbmsTrasactionManagementSystemApplicationTests {

	@Autowired
	ITransactionService transactionService;
	
	Cheque cheque,cheque1,cheque2;
	
	@BeforeEach
	void init()
	{
		cheque=new Cheque("", 2, "548910101000123", 500.0, "Ravikumar", "Pecunia Bank of India", "PECB0001714", LocalDateTime.now(),"Pending" );
		cheque1=new Cheque("", 3, "548910101000123", 500000.0, "Manikanta", "Pecunia Bank of India", "PECB0001714", LocalDateTime.now(),"Pending" );
	}
	
	@Test
	void creditAmountUsingCheque() throws RestClientException, URISyntaxException {
		assertEquals(true, transactionService.creditUsingCheque(cheque, "credit", "548910101000123")!=null);
	}
	
	@Test
	void debitAmountUsingCheque() throws RestClientException, URISyntaxException{
		assertEquals(true, transactionService.debitUsingCheque(cheque, "debit")!=null);
	}
	
	@Test
	void checkingErrorCaseofCredit() throws RestClientException, URISyntaxException {
		Assertions.assertThrows(Exception.class, ()->
		{
			transactionService.creditUsingCheque(cheque, "credit", "9123456");
		}
		);
	}
	
	@Test
	void checkingErrorCaseofDebit() throws RestClientException, URISyntaxException {
		Assertions.assertThrows(InsufficientBalanceException.class, ()->
		{
			transactionService.debitUsingCheque(cheque1, "debit");
		}
		);
	}

}
