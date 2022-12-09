package com.example.UserAccountService.service;

import java.util.List;

import com.example.UserAccountService.exceptions.UserWithTheIDAlreadyPresentException;
import com.example.UserAccountService.exceptions.UserWithTheIDNotPresentException;
import com.example.UserAccountService.model.UserAccount;

public interface UserAccountService {
	//methods for performing CRUD operations on user account
    List<UserAccount> getAllUserAccounts();
    UserAccount getUserAccountById(int id) throws UserWithTheIDNotPresentException;
    UserAccount addNewUserAccount(UserAccount userAccount) throws UserWithTheIDAlreadyPresentException;
    void deleteUserAccount(int id) throws UserWithTheIDNotPresentException;
    UserAccount updateUserAccount(UserAccount userAccount) throws UserWithTheIDNotPresentException;
    UserAccount verifyUser(String username, String password) throws UserWithTheIDNotPresentException;
    String generateToken(UserAccount user);

    //    Endpoints
//
///api/v1/account/{userId} - GET –- get user account
///api/v1/account - POST –- create new account
///api/v1/account/{userId} - DELETE –- delete account
///api/v1/account/{userId} - PUT –- edit user account
}
