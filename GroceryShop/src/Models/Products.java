package Models;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.*;

public class Products implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7372306799758866533L;
	private String productName;
	private double price;
	private String supplier;
	private int quantity;
	private String category;

    private static ArrayList<Products> products = new ArrayList<>();
    
    public Products() {
    }
	
    public Products(String productName, double price, String category, String supplier, int quantity) {
	
    	this.productName = productName;
    	this.price = price;
    	this.category = category;
    	this.supplier = supplier;
	    this.quantity = quantity;
    }
    
	public static ArrayList<Products> getProducts() {
		return products;
	}
	
	public static void setProducts(ArrayList<Products> newProducts) {
        Products.products = newProducts;
    }
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getSupplier() {
		return supplier;
	}
	
	public void setSupplier(String furnitor) {
		this.supplier = furnitor;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public SimpleStringProperty getNameProperty()
	{
		return new SimpleStringProperty(productName);
	}
	
	public SimpleStringProperty getSupplierProperty()
	{
		return new SimpleStringProperty(supplier);
	}
	
	public SimpleDoubleProperty getPriceProperty()
	{
		return new SimpleDoubleProperty(price);
	}
	
	public SimpleIntegerProperty getQuantityProperty()
	{
		return new SimpleIntegerProperty(quantity);
	}
	
	public SimpleStringProperty getCategoryProperty()
	{
		return new SimpleStringProperty(category);
	}


    
    public String toString()
    {
    	return getProductName() + getPrice() + getCategory() + getSupplier() + getQuantity();
    }
    
    public Products(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public void editProduct(Products editedProduct) {
    	this.setProductName(editedProduct.getProductName());
    	this.setPrice(editedProduct.getPrice());
    	this.setCategory(editedProduct.getCategory());
    	this.setSupplier(editedProduct.getSupplier());
    	this.setQuantity(editedProduct.getQuantity());
    }
    
}