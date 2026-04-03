package com.julianosena.interviews.revolut;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankingServiceTest {

	@Test
	void itShouldOpenAccountWithSucces(){
		Map<String, Account> accounts = new HashMap<>();
		BankingService service = new BankingService(accounts);

		var accountNumber = "12345";
		var amount = BigDecimal.ZERO;
		service.openAccount(accountNumber, amount);

		var expectedAmountOfAccounts = 1;
		assertEquals(expectedAmountOfAccounts, accounts.size());
	}

	@Test
	void itShouldValidateAccountNumberWhenOpeningAccount(){
		Map<String, Account> accounts = new HashMap<>();
		BankingService service = new BankingService(accounts);

		var accountNumber = "1234";
		var amount = BigDecimal.ZERO;
		InvalidAccountNumberException e = assertThrows(InvalidAccountNumberException.class, () -> {
			service.openAccount(accountNumber, amount);
		});

		assertEquals("Invalid account number", e.getMessage());
	}

	@Test
	void itShouldNotAllowOpenDuplicatedAccounts(){
		Map<String, Account> accounts = new HashMap<>(Map.of(
				"12345", new Account("12345", BigDecimal.ZERO),
				"54321", new Account("54321", BigDecimal.ZERO)
		));
		BankingService service = new BankingService(accounts);

		var accountNumber = "12345";
		var amount = BigDecimal.ZERO;
		DuplicatedAccountException e = assertThrows(DuplicatedAccountException.class, () -> {
			service.openAccount(accountNumber, amount);
		});

		assertEquals("Account already exists", e.getMessage());
	}

	@Test
	void itShouldGetBalanceFromExistentAccount(){
		Map<String, Account> accounts = new HashMap<>(Map.of(
				"12345", new Account("12345", BigDecimal.ZERO),
				"54321", new Account("54321", BigDecimal.ZERO)
		));
		BankingService service = new BankingService(accounts);

		var existentAccountNumber = "12345";
		BigDecimal balance = service.getBalance(existentAccountNumber);

		assertEquals(BigDecimal.ZERO, balance);
	}

	@Test
	void itShouldValidateAccountNumberWhenGettingBalance(){
		Map<String, Account> accounts = new HashMap<>();
		BankingService service = new BankingService(accounts);

		var accountNumber = "1234";
		InvalidAccountNumberException e = assertThrows(InvalidAccountNumberException.class, () -> {
			service.getBalance(accountNumber);
		});

		assertEquals("Invalid account number", e.getMessage());
	}

	@Test
	void itShouldValidateAccountExistsWhenGettingBalance(){
		Map<String, Account> accounts = new HashMap<>(Map.of(
				"12345", new Account("12345", BigDecimal.ZERO),
				"54321", new Account("54321", BigDecimal.ZERO)
		));
		BankingService service = new BankingService(accounts);

		var accountNumber = "123456";
		AccountDoesNotExistException e = assertThrows(AccountDoesNotExistException.class, () -> {
			service.getBalance(accountNumber);
		});

		assertEquals("Account does not exist", e.getMessage());
	}

	@Test
	void itShouldWithdrawMoneyFromExistentAccountWithSuccess(){
		var accountNumber = "12345";
		Map<String, Account> accounts = new HashMap<>(Map.of(
				"12345", new Account("12345", new BigDecimal("900"))
		));
		BankingService service = new BankingService(accounts);
		service.withdraw(accountNumber, new BigDecimal("300"));

		var account = accounts.get(accountNumber);
		assertEquals(account.getBalance(), new BigDecimal("600"));
	}

	@Test
	void itShouldDepositMoneyInExistentAccountWithSuccess(){
		var accountNumber = "12345";
		Map<String, Account> accounts = new HashMap<>(Map.of(
				"12345", new Account("12345", new BigDecimal("900"))
		));
		BankingService service = new BankingService(accounts);
		service.deposit(accountNumber, new BigDecimal("300"));

		var account = accounts.get(accountNumber);
		assertEquals(account.getBalance(), new BigDecimal("1200"));
	}
}