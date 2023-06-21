package com.demo.repository;

import com.demo.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Page<Account> findAll(Pageable pageable);

    Page<Account> findByCustomerIdOrAccountNumberOrDescription(String customerId, String accountNumber, String description, Pageable pageable);
}
