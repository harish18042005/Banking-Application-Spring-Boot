package practice.BankApplication.service;

import java.util.List;

import practice.BankApplication.dto.AccountDto;

public interface AccountService {
    
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(Long id, AccountDto savedAccount);

    void deleteAccountById(Long id);

    void deleteAllAccounts();

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id,double amount);

}
