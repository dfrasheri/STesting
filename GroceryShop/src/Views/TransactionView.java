package Views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import Models.Client;
import Models.Products;
import Models.Transaction;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TransactionView {
	
   
    
   // Initialize the groceryProducts list


    private TextField productNameField;
    private TextField quantityField;
    private TableView<Products> table;
    ArrayList<Products> products = Products.getProducts();

    public Tab getTransactionView() {
        productNameField = new TextField();
        quantityField = new TextField();
        table = createTable();
        //groceryProducts = new ArrayList<>();
        //initializeGroceryProducts(); // Initialize the groceryProducts list

        Button addProductButton = new Button("Add Product");
        addProductButton.setOnAction(e -> addProductHandler());

        Button clearFieldsButton = new Button("Clear Fields");
        clearFieldsButton.setOnAction(e -> clearFieldsHandler());

        Button cancelTransactionButton = new Button("Cancel Transaction");
        cancelTransactionButton.setOnAction(e -> cancelTransactionHandler());

        Button completeTransactionButton = new Button("Complete Transaction");
        completeTransactionButton.setOnAction(e -> completeTransactionHandler());

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.addRow(0, new Label("Product Name:"), productNameField);
        grid.addRow(1, new Label("Quantity:"), quantityField);
        grid.addRow(2, addProductButton, clearFieldsButton);
        grid.addRow(3, cancelTransactionButton, completeTransactionButton);

        VBox vbox = new VBox(grid, new Label("Your basket:"), table);
        Tab transactions = new Tab("Transactions");
        transactions.setContent(vbox);
        transactions.setClosable(false);
        return transactions;
    }


        private TableView<Products> createTable() {
        TableView<Products> table = new TableView<>();
        TableColumn<Products, String> productNameCol = new TableColumn<>("Product Name");
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

        TableColumn<Products, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty().asObject());

        TableColumn<Products, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        table.getColumns().addAll(productNameCol, quantityCol, priceCol);

        return table;
    }

       private void addProductHandler() {
        try {
            String productName = productNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            Optional<Products> existingProduct = products.stream().filter(product -> product.getProductName().equals(productName)).findFirst();

            if (existingProduct.isPresent()) {
                if (existingProduct.get().getQuantity() >= quantity) {
                    Products newProduct = new Products(productName, existingProduct.get().getPrice()*quantity, quantity);
                    table.getItems().add(newProduct);
                    newProduct.getNameProperty().set(productName);
                    newProduct.getPriceProperty().set(existingProduct.get().getPrice()*quantity);
                    newProduct.getQuantityProperty().set(quantity);
                   

                    clearFieldsHandler(); 

                   
                } else {
                    showAlert("Insufficient quantity. Quantity on stack: " + existingProduct.get().getQuantity());
                }
            } else {
                showAlert("Product not found: " + productName);
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid quantity. Please enter a valid number.");
        }
    }

    private void clearFieldsHandler() {
        productNameField.clear();
        quantityField.clear();
    }

    private void cancelTransactionHandler() {
        table.getItems().clear();
        clearFieldsHandler();
    }

    public static void display(Client client, Transaction transaction) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.APPLICATION_MODAL);
        detailsStage.setTitle("Transaction Details");

        VBox detailsLayout = new VBox(10);

        Label clientNameLabel = new Label("Client Name: " + client.getName());

        // Apply styles based on client points
        if (client.getPoints() >= 1000) {
            clientNameLabel.setTextFill(Color.BLUE); // Blue color for Regular Client
            clientNameLabel.setFont(Font.font("Times New Roman", 12));
            detailsLayout.getChildren().add(new Label("Regular Client"));

            detailsLayout.getChildren().add(new Label("5% discount Applied"));
        } else if (client.getPoints() > 0) {
            clientNameLabel.setTextFill(Color.PURPLE); // Purple color for Loyal Client
            clientNameLabel.setFont(Font.font("Times New Roman", 12));
            detailsLayout.getChildren().add(new Label("Loyal Client"));
        } else {
            clientNameLabel.setTextFill(Color.PINK); // Pink color for New Client
            clientNameLabel.setFont(Font.font("Times New Roman", 12));
            detailsLayout.getChildren().add(new Label("New Client"));
        }

        Label clientPointsLabel = new Label("Client Points: " + client.getPoints());
        Label totalPriceLabel = new Label("Total Price: " + transaction.getCost());
        Label dateLabel = new Label("Date: " + transaction.getDate());

        detailsLayout.getChildren().addAll(clientNameLabel, clientPointsLabel, totalPriceLabel, dateLabel);

        Scene scene = new Scene(detailsLayout, 300, 200);
        detailsStage.setScene(scene);

        detailsStage.showAndWait();
    }
   
    private void completeTransactionHandler1() {
        if (table.getItems().isEmpty()) {
            showAlert("Cannot complete transaction. There are no products added to the basket.");
            return;
        }

        TextInputDialog clientContactDialog = new TextInputDialog();
        clientContactDialog.setTitle("Complete transaction");
        clientContactDialog.setHeaderText("Enter the contact of the client:");
        clientContactDialog.setContentText("Client contact number:");

        clientContactDialog.showAndWait().ifPresent(clientContact -> {
            Optional<Client> existingClient = Client.getListOfClients().stream()
                    .filter(client -> client.getContact().equals(clientContact))
                    .findFirst();

            if (existingClient.isPresent()) {
                double totalPrice = 0.0;
                for (Products product : table.getItems()) {
                    totalPrice += product.getPrice();
                    Products groceryProduct = findProductInList(product.getProductName());
                    if (groceryProduct != null) {
                        groceryProduct.setQuantity(groceryProduct.getQuantity() - product.getQuantity());
                        System.out.println(groceryProduct.getQuantity());
                    }
                }

                existingClient.get().setPoints(existingClient.get().getPoints() + (totalPrice * 0.01));

                if (existingClient.get().getPoints() >= 1000) {
                    totalPrice = totalPrice - 0.05 * totalPrice;
                }

                ArrayList<Products> transactionProducts = new ArrayList<>(table.getItems());
                Transaction newTransaction = new Transaction(LocalDate.now(), transactionProducts, totalPrice);
                existingClient.get().addTransaction(newTransaction);
                Transaction.addTransaction(newTransaction);
                display(existingClient.get(), newTransaction);

            } else {
                showAlert("Client does not exist for contact: " + clientContact);
            }
        });
    }

    private Products findProductInList1(String productName) {
        return private TableView<Products> table;
    private ArrayList<Products> groceryProducts;
                .stream()
                .filter(product -> product.getProductName().equals(productName))
                .findFirst()
                .orElse(null);
    } private void completeTransactionHandler() {
        if (table.getItems().isEmpty()) {
            showAlert("Cannot complete transaction. There are no products added to the basket.");
            return;
        }

        TextInputDialog clientContactDialog = new TextInputDialog();
        clientContactDialog.setTitle("Complete transaction");
        clientContactDialog.setHeaderText("Enter the contact of the client:");
        clientContactDialog.setContentText("Client contact number:");

        clientContactDialog.showAndWait().ifPresent(clientContact -> {
            Optional<Client> existingClient = Client.getListOfClients().stream()
                    .filter(client -> client.getContact().equals(clientContact))
                    .findFirst();

            if (existingClient.isPresent()) {
                double totalPrice = 0.0;
                for (Products product : table.getItems()) {
                    totalPrice += product.getPrice();
                    Products groceryProduct = findProductInList(product.getProductName());
                    if (groceryProduct != null) {
                        groceryProduct.setQuantity(groceryProduct.getQuantity() - product.getQuantity());
                        System.out.println(groceryProduct.getQuantity());
                    }
                }

                existingClient.get().setPoints(existingClient.get().getPoints() + (totalPrice * 0.01));

                if (existingClient.get().getPoints() >= 1000) {
                    totalPrice = totalPrice - 0.05 * totalPrice;
                }

                ArrayList<Products> transactionProducts = new ArrayList<>(table.getItems());
                Transaction newTransaction = new Transaction(LocalDate.now(), transactionProducts, totalPrice);
                existingClient.get().addTransaction(newTransaction);
                Transaction.addTransaction(newTransaction);
                display(existingClient.get(), newTransaction);

            } else {
                showAlert("Client does not exist for contact: " + clientContact);
            }
        });
    }

    private Products findProductInList(String productName) {
        return private TableView<Products> table;
    private ArrayList<Products> groceryProducts;
.stream()
                .filter(product -> product.getProductName().equals(productName))
                .findFirst()
                .orElse(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
