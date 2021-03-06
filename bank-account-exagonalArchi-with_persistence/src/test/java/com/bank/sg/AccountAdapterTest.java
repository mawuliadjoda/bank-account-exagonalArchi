package com.bank.sg;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.sg.domain.entities.Account;
import com.bank.sg.infrastructure.database.AccountAdapter;
import com.bank.sg.infrastructure.database.AccountJpa;
import com.bank.sg.infrastructure.database.AccountJpaRepository;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

	@InjectMocks
	AccountAdapter accountAdapter;

	@Mock
	AccountJpaRepository accountJpaRepository;

	@Test
	void find_all_return_record() {
		AccountJpa accountJpa = new AccountJpa();
		long accountNumber = 12345;
		accountJpa.setAccountNumber(accountNumber);
		accountJpa.setBalance(Double.valueOf(0));
		
		List<AccountJpa> accountsJpas = List.of(accountJpa);
		
		Mockito.when(accountJpaRepository.findAll()).thenReturn(accountsJpas);
		
		
		List<Account> accounts = accountAdapter.getAll();
		assertThat(accounts).size().isEqualTo(1);
	}
	
	@Test
	void retrieveAccoun_should_return_an_account() {
		AccountJpa accountJpa = new AccountJpa();
		long accountNumber = 12345;
		accountJpa.setAccountNumber(accountNumber);
		accountJpa.setBalance(Double.valueOf(0));
		Mockito.when(accountJpaRepository.getById(accountNumber)).thenReturn(accountJpa);
		
		Account account = accountAdapter.retrieve(accountNumber);
		assertThat(account).extracting(Account::getAccountNumber).isEqualTo(accountNumber);
	}

}
