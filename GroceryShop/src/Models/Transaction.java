package Models;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7005810215686682845L;

	public static ArrayList<Transaction> getListOfTransactions() {
	
		return listOfTransactions;
	}

	
	public static void setListOfTransactions(ArrayList<Transaction> listOfTransactions) {
		Transaction.listOfTransactions = listOfTransactions;
	}


	private static ArrayList<Transaction> listOfTransactions= new ArrayList<>();
	
	private java.time.LocalDate date;
	
	public java.time.LocalDate getDate() {
		return date;
	}
	public void setDate(java.time.LocalDate date) {
		this.date = date;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	private ArrayList<Products> products;
	private double cost;

	public ArrayList<Products> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Products> products) {
		this.products = products;
	}
	public double getCost() {
		return cost;
	}
	public void setCost() {
		double cost=0;
		for(int i=0; i<products.size()-1; i++) {
			cost+=products.get(i).getPrice();
		}
		this.cost=cost;
	}
	public Transaction(LocalDate date, ArrayList<Products> products, double cost) {
		this.date = date;
		this.products = new ArrayList<>(products);
		this.cost = cost;
	}
	public static void addTransaction(Transaction transaction) {
	    
		if (listOfTransactions == null) {
			listOfTransactions = new ArrayList<>();
    }
		listOfTransactions.add(transaction);
    }

    public SimpleObjectProperty<LocalDate> getDateProperty() {
        SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this.date);
        return date;
    }
    public SimpleDoubleProperty getCostProperty() {
        return new SimpleDoubleProperty(cost);
    }
}
