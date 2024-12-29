package Views;

import Models.Products;
import Models.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UserView {

	private ObservableList<Products> productList;
    
	
    public Stage getUserView() {
        
        BorderPane MainPane = new BorderPane();
        
        Tab PointsTab = new Tab("Points");    
        
        MainPane.setMinSize(500, 400);
        MainPane.setPadding(new Insets(20));
        
        PointsTab.setClosable(false); 

        GridPane pointsNodePane = new GridPane();
        
        Text greeting = new Text("Hello, user!");
        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 70));

        TabPane SecondaryPane = new TabPane();

        //InventoryTab
        InventoryView inventoryView = new InventoryView();
        SecondaryPane.getTabs().add(inventoryView.getUsersInventoryTab());
        
        //TransactionsTab
        TransactionView t = new TransactionView();
        SecondaryPane.getTabs().add(t.getTransactionView());
        
        //SaleReportTab
        SaleReportView s = new SaleReportView();
        SecondaryPane.getTabs().add(s.getSalesReportTab());
        
        MainPane.setTop(greeting);
        MainPane.setCenter(SecondaryPane);
        
        Stage UserStage = new Stage();
        UserStage.setTitle("Admin Page");
        UserStage.setScene(new Scene(MainPane, 800, 600));
        return UserStage;
    }

}