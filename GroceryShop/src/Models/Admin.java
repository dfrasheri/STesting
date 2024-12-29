package Models;
import java.io.*;
import java.util.ArrayList;

public class Admin extends Person {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465971386978193789L;
	private static ArrayList<Admin> admins = new ArrayList<>();
	

	public static ArrayList<Admin> getAdmins() {
		return admins;
	}
	public static void setAdmins(ArrayList<Admin> admins) {
		Admin.admins = admins;
	}
	
	public Admin() {
	}
	
	public Admin(String name, String surname, String username, String password, String contact) {
		super(name, surname, username, password, contact);
	}
	
	 public void addAdmin(Admin admin) throws IOException {
	        Admin.getAdmins().add(admin);
	        saveAdminsToFile();
	 }
    
    public static void addUser(User user) throws IOException {
        User.getUsers().add(user);
        saveUsersToFile();
    }

    public void removeUser(User user) throws IOException {
        User.getUsers().remove(user);
        saveUsersToFile();
    }

    public void editUser(User oldUser, User newUser) throws IOException {
        int index = User.getUsers().indexOf(oldUser);
        if (index != -1) {
            User.getUsers().set(index, newUser);
            saveUsersToFile();
        }
    }

    public static void addProduct(Products product) throws IOException {
        Products.getProducts().add(product);
        saveProductsToFile();
    }

    public void removeProduct(Products product) throws IOException {
    	Products.getProducts().remove(product);
        saveProductsToFile();
    }

    public static void addClients(Client client) throws IOException {
        Client.getListOfClients().add(client);
        saveClientsToFile();
    }
    
    public static void removeClients(Client client) throws IOException {
        Client.getListOfClients().remove(client);
        saveClientsToFile();
    }
    
    public static void saveAdminsToFile() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//admins.dat"))) {
            output.writeObject(Admin.getAdmins());
        }
        catch (IOException e) {
            System.err.println("Error saving admins to file: " + e.getMessage());
        }
    }
    
    public static void saveUsersToFile() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//users.dat"))) {
            output.writeObject(User.getUsers());
        }
        catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }
    }

    public static void saveProductsToFile() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//products.dat"))) {
            output.writeObject(Products.getProducts());
        }
        catch (IOException e) {
        System.err.println("Error saving products to file: " + e.getMessage());
    }
    }
    
    public static void saveClientsToFile() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//clients.dat"))) {
            output.writeObject(Client.getListOfClients());
        }
        catch (IOException e) {
            System.err.println("Error saving clients to file: " + e.getMessage());
        }
    }
    
    public static void importAdminsToFile() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//admins.dat"))) {
            Admin.setAdmins((ArrayList<Admin>) input.readObject());
        }
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void importUsersToFile() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//users.dat"))) {
            User.setUsers((ArrayList<User>) input.readObject());
        }
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void importProductsToFile() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//products.dat"))) {
        	Products.setProducts((ArrayList<Products>) input.readObject());
        }
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    }
    
    public static void importClientsToFile() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C://Users//erget//Eclipse-workspace//project draft 2//Data//clients.dat"))) {
            Client.setListOfClients((ArrayList<Client>) input.readObject());
        }
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
