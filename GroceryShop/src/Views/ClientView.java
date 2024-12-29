package Views;

import java.io.IOException;

import Models.Admin;
import Models.Client;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

public class ClientView {
	private TableView<Client> tblClient;
    private ObservableList<Client> clientList;
	
    public Tab createClientView() {
    	 clientList = FXCollections.observableArrayList(Client.getListOfClients());
    	 tblClient = new TableView<>();
         setupTable();
         
         Label lName_CLIENTS = new Label("Name:");
         Label lSurname_CLIENTSS = new Label("Surname:");
         Label lEmail_CLIENTS = new Label("Contact Number:");
         Label lClient_ADDED = new Label();
         
         TextField txfName_CLIENTS = new TextField();
         TextField txfSurname_CLIENTS = new TextField();
         TextField txfEmail_CLIENTS = new TextField();
         //Button:
         Button btAdd_CLIENTS = new Button("Add");
         Button btRemove_CLIENTS = new Button("Remove");
         Button btEdit_CLIENTS = new Button("Edit");
         
         Tab ClientTab = new Tab("Clients");
         ClientTab.setClosable(false);
         
         GridPane clientsNodePane = new GridPane();
         clientsNodePane.setAlignment(Pos.CENTER);
         clientsNodePane.setHgap(10);
         clientsNodePane.setVgap(10);
         clientsNodePane.setPadding(new Insets(20));
         
         clientsNodePane.add(lName_CLIENTS, 0, 0);
         clientsNodePane.add(lSurname_CLIENTSS, 0, 1);
         clientsNodePane.add(lEmail_CLIENTS, 0, 2);
         clientsNodePane.add(txfName_CLIENTS, 1, 0);
         clientsNodePane.add(txfSurname_CLIENTS, 1, 1);
         clientsNodePane.add(txfEmail_CLIENTS, 1, 2);
         clientsNodePane.add(btAdd_CLIENTS, 1, 3);
         clientsNodePane.add(btRemove_CLIENTS, 1, 4);
         clientsNodePane.add(btEdit_CLIENTS, 1, 5);
         clientsNodePane.add(lClient_ADDED, 0, 6);
         
         btAdd_CLIENTS.setOnAction(e -> {
             // Perform actions when the "Add" button is clicked
             String name = txfName_CLIENTS.getText();
             String surname = txfSurname_CLIENTS.getText();
             String email = txfEmail_CLIENTS.getText();
             
             for(int i = 0; i < Client.getListOfClients().size(); i++)
             {
             	if(Client.getListOfClients().get(i).getContact().equals(email))
             	{
             		txfName_CLIENTS.clear();
                     txfSurname_CLIENTS.clear();
                     txfEmail_CLIENTS.clear();
         		    return;
         		}
             }

             Client newClient = new Client(name, surname, email);
             try {
             	Admin.addClients(newClient);
             	clientList.setAll(Client.getListOfClients());
             }
             catch(IOException e1) {
             	e1.printStackTrace();
             }

             // Clear text fields after adding a new user
             txfName_CLIENTS.clear();
             txfSurname_CLIENTS.clear();
             txfEmail_CLIENTS.clear();
         });
         
         btRemove_CLIENTS.setOnAction(e -> {
             Client selectedClient = tblClient.getSelectionModel().getSelectedItem();
             if (selectedClient != null) {
                 Client.getListOfClients().remove(selectedClient);
                 try {
                     Admin.saveClientsToFile();
                 } catch (IOException e1) {
                     e1.printStackTrace();
                 }
                 
                 clientList.setAll(Client.getListOfClients());
                 lClient_ADDED.setText("Client was removed successfully!");
             } else {
                 lClient_ADDED.setText("Please select a client to remove.");
             }
         });
         
         btEdit_CLIENTS.setOnAction(e -> {
         	Client selectedClient = tblClient.getSelectionModel().getSelectedItem();
         	Client newClient = new Client();
         	
             if (selectedClient != null) {
                 if (txfName_CLIENTS.getText().isEmpty() || txfSurname_CLIENTS.getText().isEmpty()
                         || txfEmail_CLIENTS.getText().isEmpty()){
                     lClient_ADDED.setText("Please fill in all fields.");
                     return;
                 }
                 
                 // Update product with edited values
                 newClient.setName(txfName_CLIENTS.getText());
                 newClient.setSurname(txfSurname_CLIENTS.getText());
                 newClient.setContact(txfEmail_CLIENTS.getText());
                
                 try {
                     selectedClient.editClient(newClient);
                     Admin.saveClientsToFile();
                     clientList.setAll(Client.getListOfClients());
                 } 
                 catch (IOException ex) {
                     ex.printStackTrace();
                 }
             } 
             else {
                 lClient_ADDED.setText("Please select a client to edit.");
             }
             
             txfName_CLIENTS.clear();
             txfSurname_CLIENTS.clear();
             txfEmail_CLIENTS.clear();
             
         });
         
         HBox tableViewPane = new HBox(tblClient);
         tableViewPane.setAlignment(Pos.CENTER);
         
         BorderPane mainPane = new BorderPane();
         mainPane.setTop(clientsNodePane);
         mainPane.setBottom(tableViewPane);
         mainPane.setCenter(tblClient);
         ClientTab.setContent(mainPane);
         
         return ClientTab;
    }

	private void setupTable() {
		 // tblClient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
	    	
		 TableColumn<Client, String> clientNameCol = new TableColumn<>("Name");
		 clientNameCol.setCellValueFactory(cellData -> cellData.getValue().getClientNameProperty());

		 TableColumn<Client, String> clientSurnameCol = new TableColumn<>("Surname");
		 clientSurnameCol.setCellValueFactory(cellData -> cellData.getValue().getClientSurnameProperty());

		 TableColumn<Client, String> clientContactCol = new TableColumn<>("Contact");
		 clientContactCol.setCellValueFactory(cellData -> cellData.getValue().getClientContactProperty());


	        tblClient.getColumns().addAll(clientNameCol, clientSurnameCol, clientContactCol);

	        tblClient.setItems(clientList);
	}
	
	public Tab getUsersInventoryTab() {
        clientList = FXCollections.observableArrayList(Client.getListOfClients());
       
        Tab ClientTab = new Tab("Employee");
        tblClient = new TableView<>();
        setupTable();
        
        HBox tableViewPane = new HBox(tblClient);
        tableViewPane.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(tableViewPane);
        mainPane.setCenter(tblClient);
        ClientTab.setContent(mainPane);
        ClientTab.setClosable(false);
        return ClientTab;
    }
		 
}
