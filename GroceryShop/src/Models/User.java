package Models;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends Person {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5876483092997270634L;
	private static ArrayList<User> users = new ArrayList<>();

	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public User() {
	}
	
	public User(String name, String surname, String username, String password, String contact) {
		super(name, surname, username, password, contact);
	}
	
	public void addToFile(User user) throws IOException{
		try {
		User.getUsers().add(user);
		FileOutputStream file = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\project draft 2"), true);
		ObjectOutputStream output = new ObjectOutputStream((file));
		output.writeObject(User.getUsers());
	    }
		catch(IOException e) {
			e.printStackTrace();
		}
    }
		
	public void deleteFromFile(User user) throws IOException {		
		try {
	    User.getUsers().remove(user);
	    FileOutputStream file = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\project draft 2"));
		ObjectOutputStream output = new ObjectOutputStream((file));
		output.writeObject(User.getUsers());		    
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public StringProperty getUserNameProperty() {
		return new SimpleStringProperty(getName());
	}

	public StringProperty getUserSurnameProperty() {
		return new SimpleStringProperty(getSurname());
	}

	public StringProperty getUserPasswordProperty() {
		return new SimpleStringProperty(getPassword());
	}

	public StringProperty getUserEmailProperty() {
		return new SimpleStringProperty(getContact());
	}

	public StringProperty getUserUsernameProperty() {
		return new SimpleStringProperty(getUsername());
	}

	public static void setUsers(ArrayList<User> users) {
		User.users = users;
	}
	
	public void editUser(User editedUser) {
    	this.setName(editedUser.getName());
    	this.setSurname(editedUser.getSurname());
    	this.setUsername(editedUser.getUsername());
    	this.setPassword(editedUser.getPassword());
    	this.setContact(editedUser.getContact());
    }
}