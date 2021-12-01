package com.bank.sg.domain.use_cases;

import java.util.List;

import com.bank.sg.domain.entities.Account;

public interface AccountPort {

	Account saveAccount(Account account);

	Account retrieve(Long accountNumber);
	
	List<Account> getAll();
}
