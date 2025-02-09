package practice.BankApplication.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import practice.BankApplication.dto.AccountDto;
import practice.BankApplication.entity.Account;
import practice.BankApplication.exception.ResourceNotFoundException;
import practice.BankApplication.mapper.AccountMapper;
import practice.BankApplication.repository.AccountRepository;
import practice.BankApplication.service.AccountService;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{

    
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Account not exist with the given id : " + id));
        return AccountMapper.mapTAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapTAccountDto(account))
        .collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto savedAccount) {
        Account account = accountRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Account not exist with the given id : " + id));

        account.setAccountHolderName(savedAccount.getAccountHolderName());
        account.setBalance(savedAccount.getBalance());

        Account savedAccountobj = accountRepository.save(account);
        
        return AccountMapper.mapTAccountDto(savedAccountobj);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Account not exist with the given id : " + id));
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Account not exist with the given id : " + id));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not exist with the given id :" + id));

            if(account.getBalance() < amount ){
                throw new RuntimeException("Insufficient Balance"); 
            }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }
    
}
