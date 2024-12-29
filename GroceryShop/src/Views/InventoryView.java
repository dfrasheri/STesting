package Views;

import java.io.IOException;

import Models.Admin;
import Models.Products;
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
import javafx.scene.text.FontWeight;

public class InventoryView {
    
    private TableView<Products> tblInventory;
    private static ObservableList<Products> productsList = FXCollections.observableArrayList(Products.getProducts());

    public Tab createInventoryView() {
        productsList = FXCollections.observableArrayList(Products.getProducts());
        tblInventory = new TableView<>();
        setupTable();
        
        Label lName_PRODUCTS = new Label("Name:");
        Label lPrice_PRODUCTS = new Label("Price:");
        Label lCategory_PRODUCTS = new Label("Category:");
        Label lSupplier_PRODUCTS = new Label("Supplier:");
        Label lAmount_PRODUCTS = new Label("Amount:");
        Label lProduct_ADDED = new Label();
        
        TextField txfName_PRODUCTS = new TextField();
        TextField txfPrice_PRODUCTS = new TextField();
        TextField txfCategory_PRODUCTS = new TextField();
        TextField txfSupplier_PRODUCTS = new TextField();
        TextField txfAmount_PRODUCTS = new TextField();

        Button btAdd_PRODUCTS = new Button("Add");
        Button btRemove_PRODUCTS = new Button("Delete");
        Button btEdit_PRODUCTS = new Button("Edit");
        
        GridPane productsNodePane = new GridPane();
        productsNodePane.setAlignment(Pos.CENTER);
        productsNodePane.setHgap(10);
        productsNodePane.setVgap(10);
        productsNodePane.setPadding(new Insets(20));

        Tab ProductsTab = new Tab("Products");
        ProductsTab.setClosable(false);

        productsNodePane.add(lName_PRODUCTS, 0, 0);
        productsNodePane.add(lPrice_PRODUCTS, 0, 1);
        productsNodePane.add(lCategory_PRODUCTS, 0, 2);
        productsNodePane.add(lSupplier_PRODUCTS, 0, 3);
        productsNodePane.add(lAmount_PRODUCTS, 0, 4);
        productsNodePane.add(txfName_PRODUCTS, 1, 0);
        productsNodePane.add(txfPrice_PRODUCTS, 1, 1);
        productsNodePane.add(txfCategory_PRODUCTS, 1, 2);
        productsNodePane.add(txfSupplier_PRODUCTS, 1, 3);
        productsNodePane.add(txfAmount_PRODUCTS, 1, 4);
        productsNodePane.add(btAdd_PRODUCTS, 2, 2);
        productsNodePane.add(btRemove_PRODUCTS, 2, 3);
        productsNodePane.add(btEdit_PRODUCTS, 2, 4);
        productsNodePane.add(lProduct_ADDED, 0, 5);

        productsNodePane.setStyle("-fx-background-color: #A5BFCB;");
        ProductsTab.setStyle("-fx-background-color: #658098;");
        
        Font labelFont = Font.font("Book Antiqua", FontWeight.BOLD, 14);
        lName_PRODUCTS.setFont(labelFont);
        lPrice_PRODUCTS.setFont(labelFont);
        lCategory_PRODUCTS.setFont(labelFont);
        lSupplier_PRODUCTS.setFont(labelFont);
        lAmount_PRODUCTS.setFont(labelFont);
        lProduct_ADDED.setFont(labelFont);
       
        btAdd_PRODUCTS.setStyle("-fx-background-color: #658098; -fx-text-fill: white;");
        btRemove_PRODUCTS.setStyle("-fx-background-color: #658098; -fx-text-fill: white;");
        btEdit_PRODUCTS.setStyle("-fx-background-color: #658098; -fx-text-fill: white;");

        btAdd_PRODUCTS.setOnAction(e -> {
            if (txfName_PRODUCTS.getText().isEmpty() || txfPrice_PRODUCTS.getText().isEmpty()
                    || txfCategory_PRODUCTS.getText().isEmpty() || txfAmount_PRODUCTS.getText().isEmpty()) {
                lProduct_ADDED.setText("Please fill in all fields.");
                return;
            }
            
               String productName = txfName_PRODUCTS.getText();
               double productPrice = Double.parseDouble(txfPrice_PRODUCTS.getText());
               String productCategory = txfCategory_PRODUCTS.getText();
               String productSupplier = txfSupplier_PRODUCTS.getText();
               int productAmount = Integer.parseInt(txfAmount_PRODUCTS.getText());
               
               
            for( int i = 0; i < Products.getProducts().size(); i++)
            {
            	if(Products.getProducts().get(i).getProductName().equals(productName)) {
            		{
            			txfName_PRODUCTS.clear();
            		    txfPrice_PRODUCTS.clear();
                        txfSupplier_PRODUCTS.clear();
                        txfCategory_PRODUCTS.clear();
                        txfAmount_PRODUCTS.clear();
            		    return;
            		}
                   
            	}
            }
            
            Products newProduct = new Products(productName, productPrice, productCategory, productSupplier, productAmount);

            //Products.getProducts().add(newProduct);
            try {
				//Admin.saveProductsToFile();
            	Admin.addProduct(newProduct);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            productsList.setAll(Products.getProducts());
            newProduct.getNameProperty().set(productName);
            newProduct.getPriceProperty().set(productPrice);
            newProduct.getCategoryProperty().set(productCategory);
            newProduct.getSupplierProperty().set(productSupplier);
            newProduct.getQuantityProperty().set(productAmount);
           
            try {
                Admin.saveProductsToFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            txfName_PRODUCTS.clear();
            txfPrice_PRODUCTS.clear();
            txfSupplier_PRODUCTS.clear();
            txfCategory_PRODUCTS.clear();
            txfAmount_PRODUCTS.clear();

            lProduct_ADDED.setText("Product was added successfully!");

        });


        btRemove_PRODUCTS.setOnAction(e -> {
            Products selectedProduct = tblInventory.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                Products.getProducts().remove(selectedProduct);
                try {
                    Admin.saveProductsToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                productsList.setAll(Products.getProducts());
                lProduct_ADDED.setText("Product was removed successfully!");
            } else {
                lProduct_ADDED.setText("Please select a product to remove.");
            }
        });
        
        btEdit_PRODUCTS.setOnAction(e -> {
        	Products selectedProduct = tblInventory.getSelectionModel().getSelectedItem();
        	Products newProduct = new Products();
        	
            if (selectedProduct != null) {
                if (txfName_PRODUCTS.getText().isEmpty() || txfPrice_PRODUCTS.getText().isEmpty()
                        || txfCategory_PRODUCTS.getText().isEmpty() || txfAmount_PRODUCTS.getText().isEmpty()) {
                    lProduct_ADDED.setText("Please fill in all fields.");
                    return;
                }
                
                // Update product with edited values
                newProduct.setProductName(txfName_PRODUCTS.getText());
                newProduct.setPrice(Double.parseDouble(txfPrice_PRODUCTS.getText()));
                newProduct.setCategory(txfCategory_PRODUCTS.getText());
                newProduct.setSupplier(txfSupplier_PRODUCTS.getText());
                newProduct.setQuantity(Integer.parseInt(txfAmount_PRODUCTS.getText()));

                try {
                    selectedProduct.editProduct(newProduct);
                    Admin.saveProductsToFile();
                    productsList.setAll(Products.getProducts());
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 
            else {
                lProduct_ADDED.setText("Please select a product to edit.");
            }
            
            txfName_PRODUCTS.clear();
            txfPrice_PRODUCTS.clear();
            txfSupplier_PRODUCTS.clear();
            txfCategory_PRODUCTS.clear();
            txfAmount_PRODUCTS.clear();
        });

        
        HBox tableViewPane = new HBox(tblInventory);
        tableViewPane.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(productsNodePane);
        mainPane.setBottom(tableViewPane);
        mainPane.setCenter(tblInventory);
        ProductsTab.setContent(mainPane);
        return ProductsTab;
    }

    private void setupTable() {
    	//tblInventory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
        TableColumn<Products, String> productNameCol = new TableColumn<>("Product Name");
        productNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

        TableColumn<Products, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        
        TableColumn<Products, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        TableColumn<Products, String> furnitorCol = new TableColumn<>("Supplier");
        furnitorCol.setCellValueFactory(cellData -> cellData.getValue().getSupplierProperty());
        
        TableColumn<Products, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty().asObject());

        tblInventory.getColumns().addAll(productNameCol, categoryCol, priceCol, furnitorCol, quantityCol);

        tblInventory.setItems(productsList);
    }
    
    public static ObservableList<Products> getProductsList() {
        return productsList;
    }

    public Tab getUsersInventoryTab() {
        productsList = FXCollections.observableArrayList(Products.getProducts());
       
        Tab ProductsTab = new Tab("Inventory");
        tblInventory = new TableView<>();
        setupTable();
        
        HBox tableViewPane = new HBox(tblInventory);
        tableViewPane.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(tableViewPane);
        mainPane.setCenter(tblInventory);
        ProductsTab.setContent(mainPane);
        ProductsTab.setClosable(false);
        return ProductsTab;
    }
}
