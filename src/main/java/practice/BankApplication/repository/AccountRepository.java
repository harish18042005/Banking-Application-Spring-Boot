package practice.BankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.BankApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
