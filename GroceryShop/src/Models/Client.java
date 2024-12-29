package Models;

import java.io.Serializable;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Client extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1651209219955149076L;
	private double points;
	public static ArrayList<Client> getListOfClients() {
		return listOfClients;
	}


	public static void setListOfClients(ArrayList<Client> listOfClients) {
		Client.listOfClients = listOfClients;
	}


	private static ArrayList<Client> listOfClients= new ArrayList<>();
	

	private ArrayList<Transaction> transactions;

	public Client(String name, String surname, String contact, ArrayList<Transaction> transaction ) {
		super(name, surname, contact);
		this.points=0.0;
		transactions= new ArrayList<>();
	}
	
	public Client(String name, String surname, String contact) {
		super(name, surname, contact);
		this.points=0.0;
	}
	
	public Client() {
		
	}

	public double getPoints() {
	
		return points;
    }


	public void setPoints(double points) {
	
		this.points = points;
    }


	public void addTransaction(Transaction transaction) {
    
		if (transactions == null) {
        transactions = new ArrayList<>();
    }
		transactions.add(transaction);
    }
	
	public static void addClient(Client client) {
        if (listOfClients == null) {
            listOfClients = new ArrayList<>();
        }
        listOfClients.add(client);
    }
	
	public StringProperty getClientNameProperty() {
		return new SimpleStringProperty(getName());
	}
	
	public StringProperty getClientSurnameProperty() {
		return new SimpleStringProperty(getSurname());
	}
	
	public StringProperty getClientContactProperty() {
		return new SimpleStringProperty(getContact());
	}
	
	public void editClient(Client editedClient) {
    	this.setName(editedClient.getName());
    	this.setSurname(editedClient.getSurname());
    	this.setContact(editedClient.getContact());
    }
}

