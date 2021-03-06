package com.bank.sg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.sg.domain.entities.Account;
import com.bank.sg.domain.use_cases.AccountPort;
import com.bank.sg.domain.use_cases.AccountService;
import com.bank.sg.domain.use_cases.BankAccountException;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@InjectMocks
	AccountService accountService;

	@Mock
	AccountPort accountPort;

	@Test
	void when_deposit_amount_shouldPositive_account() throws BankAccountException {
		Account account = new Account();
		long accountNumber = 12345;
		account.setAccountNumber(accountNumber);
		account.setBalance(Double.valueOf(0));
		
		Mockito.when(accountPort.retrieve(accountNumber)).thenReturn(account);
		Mockito.when(accountPort.saveAccount(account)).thenReturn(account);
		
		
		double amount = 500;
		Account result = accountService.deposit(accountNumber, amount);
		assertThat(result).extracting(Account::getBalance).isEqualTo(amount);
		
	}
	
	@Test
	void when_withdraw_amount_shouldPositive_account() throws BankAccountException {
		Account account = new Account();
		long accountNumber = 12345;
		account.setAccountNumber(accountNumber);
		account.setBalance(Double.valueOf(500));
		
		Mockito.when(accountPort.retrieve(accountNumber)).thenReturn(account);
		Mockito.when(accountPort.saveAccount(account)).thenReturn(account);
		
		
		double amount = 300.0;
		Account result = accountService.withdraw(accountNumber, amount);
		assertThat(result).extracting(Account::getBalance).isEqualTo(200.0);
		
	}

}
