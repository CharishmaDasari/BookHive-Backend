package com.cgi.UserAccountService.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.UserAccountService.model.UserAccount;
import com.example.UserAccountService.repository.UserAccountRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private transient UserAccountRepository accountRepository;
	
	private UserAccount account;
	
	public void setUserAccountRepository(UserAccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	
	@BeforeEach
	public void  setUp() throws Exception 
	{
		account=new UserAccount(1, "user1", "123456", "user1@gmail.com","user");
	}
	
	@Test
	public void testRegisterUserSuccess()
	{
		accountRepository.save(account);
		Optional<UserAccount> object=accountRepository.findById(account.getUserId());
		assertThat(object.get().equals(account));
	}

}
