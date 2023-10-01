package com.bitcoin.autotrading.account.Repository;

import com.bitcoin.autotrading.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAllByCurrency(String currency);
}
