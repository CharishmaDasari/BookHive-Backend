package com.cgi.UserAccountService.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.UserAccountService.exceptions.UserWithTheIDAlreadyPresentException;
import com.example.UserAccountService.exceptions.UserWithTheIDNotPresentException;
import com.example.UserAccountService.model.UserAccount;
import com.example.UserAccountService.repository.UserAccountRepository;
import com.example.UserAccountService.service.UserAccountServiceImpl;

public class UserServiceTest {

	@Mock
	private UserAccountRepository accountRepository;
	
	private UserAccount userAccount;
	
	@InjectMocks
	private UserAccountServiceImpl service;
	
	private Optional<UserAccount> options;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		userAccount=new UserAccount(1, "user1", "123456", "user1@gmail.com", "user");
		options=Optional.of(userAccount);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserWithTheIDAlreadyPresentException
	{
		when(accountRepository.save(userAccount)).thenReturn(userAccount);
		UserAccount userAccount1=service.addNewUserAccount(userAccount);
		String s1=String.valueOf(true);
		String s2=String.valueOf(userAccount1);
		assertEquals(s1, s2,"Cannot Register User");
		verify(accountRepository,times(1)).save(userAccount);
	}
	
	@Test
	public void testValidateSuccess() throws UserWithTheIDNotPresentException
	{
		when(accountRepository.findById(userAccount.getUserId()));
		UserAccount user=service.getUserAccountById(userAccount.getUserId());
		assertNotNull(user);
		assertEquals(userAccount.getUserId(), user.getUserId());
		verify(accountRepository,times(1)).findById(userAccount.getUserId());
	}
}
