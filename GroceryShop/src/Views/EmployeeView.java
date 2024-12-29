package Views;
import java.io.IOException;

import Models.Admin;
import Models.Products;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EmployeeView {

	private TableView<User> tblUser;
	private static ObservableList<User> userList;
	
	public Tab createEmployeeTab() {
		userList = FXCollections.observableArrayList(User.getUsers());
		tblUser = new TableView<>();
	    setupTable();
	    
	    Label lName_EMPLOYEES = new Label("Name:");
	    Label lSurname_EMPLOYEES = new Label("Surname:");
	    Label lUsername_EMPLOYEES = new Label("Username:");
	    Label lPassword_EMPLOYEES = new Label("Password:");
	    Label lEmail_EMPLOYEES = new Label("Email:");
	    Label lEmployee_ADDED = new Label();
	    //Employee Tab Textfields:
	    TextField txfName_EMPLOYEES = new TextField();
	    TextField txfSurname_EMPLOYEES = new TextField();
	    TextField txfUsername_EMPLOYEES = new TextField();
	    TextField txfPassword_EMPLOYEES = new TextField();
	    TextField txfEmail_EMPLOYEES = new TextField();
	    //Button:
	    Button btAdd_EMPLOYEES = new Button("Add");
	    Button btRemove_EMPLOYEES = new Button("Remove");
	    Button btEdit_EMPLOYEES = new Button("Edit");
	    
	    GridPane employeeNodePane = new GridPane();
	    Tab EmployeeTab = new Tab("Employee"); 
	    EmployeeTab.setClosable(false);
	    //Organization: Employees tab
        //labels
        employeeNodePane.add(lName_EMPLOYEES, 0, 0);
        employeeNodePane.add(lSurname_EMPLOYEES, 0, 1);
        employeeNodePane.add(lUsername_EMPLOYEES, 0, 2);
        employeeNodePane.add(lPassword_EMPLOYEES, 0, 3);
        employeeNodePane.add(lEmail_EMPLOYEES, 0, 4);
        //text-fields
        employeeNodePane.add(txfName_EMPLOYEES, 1, 0);
        employeeNodePane.add(txfSurname_EMPLOYEES, 1, 1);
        employeeNodePane.add(txfUsername_EMPLOYEES, 1, 2);
        employeeNodePane.add(txfPassword_EMPLOYEES, 1, 3);
        employeeNodePane.add(txfEmail_EMPLOYEES, 1, 4);
        employeeNodePane.add(btAdd_EMPLOYEES, 1, 5);
        employeeNodePane.add(btRemove_EMPLOYEES, 1, 6);
        employeeNodePane.add(btEdit_EMPLOYEES, 1, 7);
        employeeNodePane.add(lEmployee_ADDED, 0, 8);
	   
	    
	    btAdd_EMPLOYEES.setOnAction(e -> {
        	if (txfName_EMPLOYEES.getText().isEmpty() || txfSurname_EMPLOYEES.getText().isEmpty()
                    || txfUsername_EMPLOYEES.getText().isEmpty() || txfPassword_EMPLOYEES.getText().isEmpty() || txfEmail_EMPLOYEES.getText().isEmpty()) {
                lEmployee_ADDED.setText("Please fill in all fields.");
                return;
            }
        	
            String name = txfName_EMPLOYEES.getText();
            String surname = txfSurname_EMPLOYEES.getText();
            String username = txfUsername_EMPLOYEES.getText();
            String password = txfPassword_EMPLOYEES.getText();
            String email = txfEmail_EMPLOYEES.getText();

            User newUser = new User(name, surname, username, password, email);
            try {
            	Admin.addUser(newUser);
            }
             catch (IOException e1) {
			
            	 e1.printStackTrace();
			
		    }
            
            userList.setAll(User.getUsers());
            txfName_EMPLOYEES.clear();
            txfSurname_EMPLOYEES.clear();
            txfUsername_EMPLOYEES.clear();
            txfPassword_EMPLOYEES.clear();
            txfEmail_EMPLOYEES.clear();
        });
        
	    btRemove_EMPLOYEES.setOnAction(e -> {
            User selectedEmployee = tblUser.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                User.getUsers().remove(selectedEmployee);
                try {
                    Admin.saveUsersToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                userList.setAll(User.getUsers());
                lEmployee_ADDED.setText("Employee was removed successfully!");
            } else {
                lEmployee_ADDED.setText("Please select an employee to remove.");
            }
        });
	    
	    btEdit_EMPLOYEES.setOnAction(e -> {
        	User selectedUser = tblUser.getSelectionModel().getSelectedItem();
        	User newUser = new User();
        	
            if (selectedUser != null) {
                if (txfName_EMPLOYEES.getText().isEmpty() || txfSurname_EMPLOYEES.getText().isEmpty()
                        || txfUsername_EMPLOYEES.getText().isEmpty() || txfPassword_EMPLOYEES.getText().isEmpty() || txfEmail_EMPLOYEES.getText().isEmpty()){
                    lEmployee_ADDED.setText("Please fill in all fields.");
                    return;
                }
                
                // Update product with edited values
                newUser.setName(txfName_EMPLOYEES.getText());
                newUser.setSurname(txfSurname_EMPLOYEES.getText());
                newUser.setUsername(txfUsername_EMPLOYEES.getText());
                newUser.setPassword(txfPassword_EMPLOYEES.getText());
                newUser.setContact(txfEmail_EMPLOYEES.getText());

                try {
                    selectedUser.editUser(newUser);
                    Admin.saveProductsToFile();
                    userList.setAll(User.getUsers());
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 
            else {
                lEmployee_ADDED.setText("Please select an employee to edit.");
            }
            
            txfName_EMPLOYEES.clear();
            txfSurname_EMPLOYEES.clear();
            txfUsername_EMPLOYEES.clear();
            txfPassword_EMPLOYEES.clear();
            txfEmail_EMPLOYEES.clear();
        });
	    
	    HBox tableViewPane = new HBox(tblUser);
        tableViewPane.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(employeeNodePane);
        mainPane.setBottom(tableViewPane);
        mainPane.setCenter(tblUser);
        EmployeeTab.setContent(mainPane);
        return EmployeeTab;
	}
	
	private void setupTable() {
		 tblUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    	
	        TableColumn<User, String> userNameCol = new TableColumn<>("Name");
	        userNameCol.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());

	        TableColumn<User, String> userSurnameCol = new TableColumn<>("Surname");
	        userSurnameCol.setCellValueFactory(cellData -> cellData.getValue().getUserSurnameProperty());
	        
	        TableColumn<User, String> userUsernameCol = new TableColumn<>("Username");
	        userUsernameCol.setCellValueFactory(cellData -> cellData.getValue().getUserUsernameProperty());

	        TableColumn<User, String> userPasswordCol = new TableColumn<>("Password");
	        userPasswordCol.setCellValueFactory(cellData -> cellData.getValue().getUserPasswordProperty());
	        
	        TableColumn<User, String> userContactCol = new TableColumn<>("Contact");
	        userContactCol.setCellValueFactory(cellData -> cellData.getValue().getUserEmailProperty());

	        tblUser.getColumns().addAll(userNameCol, userSurnameCol, userUsernameCol,  userPasswordCol, userContactCol);

	        tblUser.setItems(userList);
	}
	
	public Tab getUsersInventoryTab() {
        userList = FXCollections.observableArrayList(User.getUsers());
       
        Tab EmployeeTab = new Tab("Employee");
        tblUser = new TableView<>();
        setupTable();
        
        HBox tableViewPane = new HBox(tblUser);
        tableViewPane.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(tableViewPane);
        mainPane.setCenter(tblUser);
        EmployeeTab.setContent(mainPane);
        EmployeeTab.setClosable(false);
        return EmployeeTab;
    }
   
}
