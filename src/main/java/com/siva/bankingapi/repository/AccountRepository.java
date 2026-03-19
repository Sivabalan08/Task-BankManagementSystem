package com.siva.bankingapi.repository;

import com.siva.bankingapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}