package Views;
import java.io.IOException;

import Models.Admin;
import Models.Client;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminView {

    public Stage getAdminView() {
       
        BorderPane MainPane = new BorderPane();
        TabPane SecondaryPane = new TabPane();
       
        MainPane.setMinSize(500, 400);
        MainPane.setPadding(new Insets(20));
      
        GridPane pointsNodePane = new GridPane();
    
        Text greeting = new Text("Hello, admin!");
        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 70));
   
        // Create a new Tab for InventoryView
        InventoryView inventoryView = new InventoryView(); 
        Tab inventoryTab = inventoryView.createInventoryView();
        SecondaryPane.getTabs().add(inventoryTab);
        
        // Create a new Tab for EmployeeView
        EmployeeView employeeView = new EmployeeView();
        Tab employeeTab = employeeView.createEmployeeTab();
        SecondaryPane.getTabs().add(employeeTab);
        
        // Create a new Tab for ClientView
        ClientView clientView = new ClientView();
        Tab clientTab = clientView.createClientView();
        SecondaryPane.getTabs().add(clientTab);
        
        //Create a new tab for transactions:
        TransactionView t = new TransactionView();
        Tab transactionsTab = t.getTransactionView();
        SecondaryPane.getTabs().add(transactionsTab);

        //Sales tab
        SaleReportView s = new SaleReportView();
        SecondaryPane.getTabs().add(s.getSalesReportTab());
        
        MainPane.setTop(greeting);
        MainPane.setCenter(SecondaryPane);
        
        Stage AdminStage = new Stage();
        AdminStage.setTitle("Admin Page");
        AdminStage.setScene(new Scene(MainPane, 800, 600));
        return AdminStage;
    }
}