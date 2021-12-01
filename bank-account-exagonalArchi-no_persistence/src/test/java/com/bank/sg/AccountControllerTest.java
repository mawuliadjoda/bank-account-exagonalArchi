package com.bank.sg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bank.sg.domain.application.rest.AccountController;
import com.bank.sg.domain.entities.Account;
import com.bank.sg.domain.use_cases.AccountPort;
import com.bank.sg.domain.use_cases.AccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountPort accountPort;

	static AccountService accountService;
	static Account account;

	@BeforeAll
	static void setup() {
		accountService = new AccountService();
		account = new Account();

		long accountNumber = 123;

		account.setAccountNumber(accountNumber);
		account.setBalance(0);
		accountService.saveAccount(account);
	}

	
	@Test
	@Order(1)
	void test() throws Exception {
		
		Account account = new Account();

		long accountNumber = 123;

		account.setAccountNumber(accountNumber);
		account.setBalance(100);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/accounts", account).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

}
