package com.cgi.UserAccountService.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.UserAccountService.controller.UserAccountController;
import com.example.UserAccountService.model.UserAccount;
import com.example.UserAccountService.service.UserAccountServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserAccountController.class)
public class UserControllerTest {

	@Autowired
	private transient MockMvc mockMvc;
	
	@MockBean
	private transient UserAccountServiceImpl accountServiceImpl;
	
	private transient UserAccount account;
	
	@InjectMocks
	private UserAccountController accountController;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() 
	{
		MockitoAnnotations.initMocks(this);
		account=new UserAccount(3, "user3", "123456", "user3@gmail.com", "user");
	}
	
	@Test
	public void testRegisterUser() throws Exception
	{
		when(accountServiceImpl.addNewUserAccount(account)).thenReturn(account);
		mockMvc.perform(post("/api/v1/accounts/signup").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(account))).andExpect(status()
						.isCreated()).andDo(print());
		verify(accountServiceImpl,times(1)).addNewUserAccount(Mockito.any(UserAccount.class));
		verifyNoMoreInteractions(accountServiceImpl);
	}
	
	@Test
	public void testLoginUser() throws Exception
	{
		when(accountServiceImpl.getUserAccountById(account.getUserId())).thenReturn(account);
		mockMvc.perform(post("/api/v1/accounts/{userId}").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(account))).andExpect(status().isOk());
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result=mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
