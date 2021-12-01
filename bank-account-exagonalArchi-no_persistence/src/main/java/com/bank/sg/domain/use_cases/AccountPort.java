package com.bank.sg.domain.use_cases;

import java.util.List;

import com.bank.sg.domain.entities.Account;

public interface AccountPort {

	Account retrieveAccount(long accountNumber);

	void saveAccount(Account account);

	List<Account> getAllAccount();
	
	void deposit(long accountNumber, double amount) throws BankAccountException;

	void withdraw(long accountNumber, double amount) throws BankAccountException;

	List<String> showHistory();
}
