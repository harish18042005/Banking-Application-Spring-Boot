package practice.BankApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
