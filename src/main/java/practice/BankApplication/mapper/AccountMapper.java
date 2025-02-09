package practice.BankApplication.mapper;

import practice.BankApplication.dto.AccountDto;
import practice.BankApplication.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
            
        );
        
    }


    public static AccountDto mapTAccountDto(Account account){
        return new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
    }
}
