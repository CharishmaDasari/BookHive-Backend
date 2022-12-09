package com.example.UserAccountService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserAccountService.exceptions.UserWithTheIDAlreadyPresentException;
import com.example.UserAccountService.exceptions.UserWithTheIDNotPresentException;
import com.example.UserAccountService.model.UserAccount;
import com.example.UserAccountService.repository.UserAccountRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//The @Service is a class-level annotation used to indicate that the class is a service class that defines the business logic.

@Service
public class UserAccountServiceImpl implements UserAccountService{
	//connects the userAccountRepository to service class
    @Autowired
    private UserAccountRepository userAccountRepository;
    //Indicates that a method declaration is intended to override amethod declaration in a supertype

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
        
    }
    //Indicates that a method declaration is intended to override amethod declaration in a supertype

    @Override
    public UserAccount getUserAccountById(int id) throws UserWithTheIDNotPresentException {
        Optional<UserAccount> optional = userAccountRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
            //returns the userwithid
        }
        throw new UserWithTheIDNotPresentException();
    }

    @Override
    public UserAccount addNewUserAccount(UserAccount userAccount) throws UserWithTheIDAlreadyPresentException {
        Optional<UserAccount> optional = userAccountRepository.findById(userAccount.getUserId());
        Optional<UserAccount> optional2 = userAccountRepository.findByUsernameAndPassword(userAccount.getUsername(), userAccount.getPassword());

        if(optional2.isEmpty()){
            userAccountRepository.save(userAccount);
            return userAccount;
            //returns the account saved
        } else {
        throw new UserWithTheIDAlreadyPresentException();  }
        }

    @Override
    public void deleteUserAccount(int id) throws UserWithTheIDNotPresentException {
        Optional<UserAccount> optional = userAccountRepository.findById(id);
        if (optional.isPresent()){
            optional.get();
        }
        throw new UserWithTheIDNotPresentException();
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) throws UserWithTheIDNotPresentException {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(userAccount.getUserId());
        if (userAccountOptional.isPresent()){
            userAccountOptional.get().setUserId(userAccount.getUserId());
            userAccountOptional.get().setUsername(userAccount.getUsername());
            userAccountOptional.get().setPassword(userAccount.getPassword());
            userAccountOptional.get().setEmail(userAccount.getEmail());
            userAccountOptional.get().setRole(userAccount.getRole());
            userAccountRepository.save(userAccountOptional.get());
            return userAccountOptional.get();
            // returns the useraccount details with the useraccount is present
        }
        throw new UserWithTheIDNotPresentException();
    }
    
    public UserAccount verifyUser(String username, String password)  throws UserWithTheIDNotPresentException{
    	// TODO Auto-generated method stub
    	Optional<UserAccount> userOptional= userAccountRepository.findByUsernameAndPassword(username, password);
    	if(userOptional.isPresent()) {
    		return userOptional.get();
    	}
    	throw new UserWithTheIDNotPresentException();
    	}

    	public String generateToken(UserAccount user) {
    	// TODO Auto-generated method stub
    	String jwtToken;
    	jwtToken = Jwts.builder()
    	.setSubject(Integer.toString(user.getUserId()))
//    	.setClaims()
    	.setIssuedAt(new Date())
    	.setExpiration(new Date(System.currentTimeMillis() + 5000000))
    	.signWith(SignatureAlgorithm.HS256, "stackroute")
    	.compact();

    	return jwtToken;
    	//returns the jwttoken that contains useraccount details saved in database along with time
    	}

}
