package com.example.UserAccountService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAccountService.exceptions.UserWithTheIDAlreadyPresentException;
import com.example.UserAccountService.exceptions.UserWithTheIDNotPresentException;
import com.example.UserAccountService.model.LoginUser;
import com.example.UserAccountService.model.UserAccount;
import com.example.UserAccountService.service.UserAccountService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//It can be considered as a combination of @Controller and @ResponseBody annotations

@RestController
//The “@RequestMapping” Annotation is used to map HTTP requests to REST and MVC controller methods 
// mapping can be done using /api/v1
@RequestMapping("/api/v1")
public class UserAccountController {
	//The “@Autowired” annotation can auto wire bean on a constructor,  setter method, property, or methods with multiple parameters

    @Autowired
    // UserAccountService class is imported

    private UserAccountService userAccountService;
    //HTTP GET requests on the specific handler method /api/v1/accounts
    @GetMapping(value ="/accounts")
    public ResponseEntity<List<UserAccount>> getAllUserAccounts(){
        ResponseEntity<List<UserAccount>> responseEntity;
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        responseEntity = new ResponseEntity<List<UserAccount>>(userAccounts, HttpStatus.OK);
        return responseEntity;
    	// return response will be ok if user is registered;

    }
    //HTTP GET requests on the specific handler method
    //we get the details based on userid 
    @GetMapping("/accounts/{userId}")
    public ResponseEntity<?> getUserByIdHandler(@PathVariable("userId") int id) throws UserWithTheIDNotPresentException {
        ResponseEntity<?> responseEntity;
        try {
            UserAccount userAccount = userAccountService.getUserAccountById(id);
            responseEntity = new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
        	// the response will be ok if user is registered with the same Id;

        }catch (UserWithTheIDNotPresentException e){
            responseEntity = new ResponseEntity<String>("User with ID not found", HttpStatus.NOT_FOUND);
        	// the response will be Not Found if user is not registered
        }
        return responseEntity;
        // return response will be based on responseEntity

    }
	//used to HTTP POST request for singup to the account to that database

    @PostMapping("/accounts/signup")
    public ResponseEntity<?> addUserAccountHandler(@RequestBody UserAccount userAccount){
        ResponseEntity<?> responseEntity;
        try {
            UserAccount newUser = userAccountService.addNewUserAccount(userAccount);
            responseEntity = new ResponseEntity<UserAccount>(newUser, HttpStatus.CREATED);
            // The status will be created if the user is new
        } catch (UserWithTheIDAlreadyPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to store the user account: Duplicate Resource", HttpStatus.CONFLICT);
            // The status will be Failed to store the user account if the user is already registered

        }
        return responseEntity;
        // return response will be based on responseEntity
    }
	//used to HTTP POST request for login to the account to that database

    
    @PostMapping("/accounts/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginUser loginUser ){

    	ResponseEntity<?> responseEntity;

    	Map<String, String> tokenMap = new HashMap<>();

    	try {
        	UserAccount userAccount = userAccountService.verifyUser(loginUser.getUsername(),loginUser.getPassword());

            
        	// 
        	String token = userAccountService.generateToken(userAccount);
        	tokenMap.put("token", token);
        	responseEntity = new ResponseEntity<Map<String, String>>(tokenMap,HttpStatus.OK);
        	// If the details matches with the credentials of the token in database message will be ok
    	} catch(UserWithTheIDNotPresentException e) {
    	tokenMap.clear();
    	tokenMap.put("token", null);
    	tokenMap.put("message", "Invalid User Credentials");
    	responseEntity = new ResponseEntity<Map<String,String>>(tokenMap,HttpStatus.FORBIDDEN);
    	// If the details doesn't matches with the credentials of the token in database message will be Invalid User Credentials

    	}

    	return responseEntity;
    	// return forbidden response;

    	}
    // Doing the authentication
    @PostMapping("/accounts/isAuthenticated")
	public ResponseEntity<Map<String,Object>> verifyToken(@RequestHeader("Authorization") String authHeader){
		System.out.println("Request received");
		
		ResponseEntity<Map<String, Object>> responseEntity;
		HashMap<String, Object> map = new HashMap<>();
		map.clear();
		System.out.println(authHeader);
		String token = authHeader.split(" ")[1];
		try {
			Claims claims =  Jwts.parser()
			.setSigningKey("stackroute")
			.parseClaimsJws(token)
			.getBody();
			map.put("isAuthenticated", true);
			map.put("userId", claims.getSubject());
			responseEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			// If authentication is true the message will be ok
			
		}catch(Exception e) {
			map.put("isAuthenticated", false);
			responseEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.FORBIDDEN);
			// If authentication is false the message will be forbidden

		}
		
		return responseEntity;
        // return response will be based on responseEntity

		
	}

    // Deleting the user based on userId

  /*  @DeleteMapping("/accounts/{userId}")
    public ResponseEntity<String> deleteUserAccountHandler(@PathVariable("userId") int id )throws UserWithTheIDNotPresentException{
        ResponseEntity<String> responseEntity;
        try {
            userAccountService.deleteUserAccount(id);
            responseEntity = new ResponseEntity<String>("User Account Deleted", HttpStatus.NO_CONTENT);
            // The status will be No_CONTENT if the user account is deleted

        }catch (UserWithTheIDNotPresentException e){
            responseEntity = new ResponseEntity<String>("User Account with ID not found", HttpStatus.NOT_FOUND);
            // The status will be Not_Found if the user account with ID not found 

        }
        return responseEntity;
        // return response will be based on responseEntity

    }*/
    //updating the details using  userId
    @PutMapping("/accounts/{userId}")
    public ResponseEntity<?> updateUserHandler(@PathVariable("userId") int id) throws UserWithTheIDNotPresentException{
        ResponseEntity<?> responseEntity;
        try {
            UserAccount userAccount = userAccountService.updateUserAccount(userAccountService.getUserAccountById(id));
            responseEntity = new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
            // if the details is upadated successfully the status will be ok
        }catch (UserWithTheIDNotPresentException e){
            responseEntity = new ResponseEntity<String>("User Account with ID not found", HttpStatus.NOT_FOUND);
            // if the details is not upadated successfully the status will be Not found

        }
        return responseEntity;
    }
//    Endpoints
//
///api/v1/account/{userId} - GET –- get user account
///api/v1/account - POST –- create new account
///api/v1/account/{userId} - DELETE –- delete account
///api/v1/account/{userId} - PUT –- edit user account
}
