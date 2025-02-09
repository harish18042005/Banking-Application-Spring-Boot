package practice.BankApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import practice.BankApplication.dto.AccountDto;
import practice.BankApplication.service.AccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")


public class AccountController {
    
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        AccountDto savedAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
       List<AccountDto> accounts = accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id,@RequestBody AccountDto savedAccount){
        AccountDto account = accountService.updateAccount(id, savedAccount);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("id") Long id){
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account deleted successfully");
    }


    @DeleteMapping
    public ResponseEntity<String> deleteAllAccounts(){
        accountService.deleteAllAccounts();
        return ResponseEntity.ok("All Accounts deleted successfully");
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id,@RequestBody Map<String, Double> request){
        AccountDto account = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id,@RequestBody Map<String, Double> request){
        AccountDto account = accountService.withdraw(id, request.get("amount"));
        return ResponseEntity.ok(account);
    }
}
