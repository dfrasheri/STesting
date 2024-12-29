package Models;

import java.io.Serializable;

public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2998951401027327985L;
	private String name;
    private String surname; 
    private String username;
    private String password;
    private String contact;


    public String getContact() {
	
    	return contact;
    }
    
    public void setContact(String contact) {
	
    	this.contact = contact;
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
    
    public Person() {
    	
    }
    
    public Person(String name, String surname, String username, String password, String contact) {
	 
    	this.name= name;
	    this.surname=surname;
	    this.username= username;
	    this.password=password;
	    this.contact=contact;
    }
    
    public Person(String name, String surname, String contact) {
	    this.name= name;
	    this.surname=surname;
	    this.contact=contact;
    }
    
    public String getName() {
	
    	return name;
    }
    
    public void setName(String name) {
	 
    	this.name = name;
    }

    public String getSurname() {
	
    	return surname;
    }

    public void setSurname(String surname) {
	 
    	this.surname = surname;
    }

}