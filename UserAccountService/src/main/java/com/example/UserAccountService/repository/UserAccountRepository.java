package com.example.UserAccountService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UserAccountService.model.UserAccount;

import java.util.Optional;

import javax.transaction.Transactional;

// The repository is a DAOs (Data Access Object) that access the database directly. The repository does all the operations related to the database.
@Repository
//that specifies that an interface, class, or method must have transactional semantics
@Transactional
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	//optional method for useraccount consists of username,password
	Optional<UserAccount> findByUsernameAndPassword(String username, String password);
}
