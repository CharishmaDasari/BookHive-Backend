package com.example.UserAccountService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity specifies that the class is an entity and is mapped to a database table   

@Entity
public class UserAccount {
	// @Id defines the primary key of an entity
	//@GeneratedValue name of the generator, the name, and schema of the database sequence and the allocation size of the sequence
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//parameters
    private int userId;
	private String username;
    private String password;
    private String email;
    private String role;
    
	// Parameterized constructor

    public UserAccount(int userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
	// Default constructor

    public UserAccount() {
    }
	// getters and setters methods 

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
    //    userId - 1: integer (Primary key)
//    username - 2: string (required, max=25)
//    password - 3: string (required, max=35)
//    email - 4: string (required, max=35)
//    role - 5: string (admin, user)


}
