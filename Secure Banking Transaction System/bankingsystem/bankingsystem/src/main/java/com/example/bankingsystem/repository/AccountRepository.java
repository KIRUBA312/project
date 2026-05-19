package com.example.bankingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingsystem.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByAccountNumber(String accountNumber);

}
