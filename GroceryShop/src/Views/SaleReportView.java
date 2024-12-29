package Views;

import java.time.LocalDate;

import Models.Products;
import Models.Transaction;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SaleReportView {
        
    public Tab getSalesReportTab() {
        Tab main = new Tab("Sale Report");
        TableView<Transaction> SalesTable = getTable();
        main.setClosable(false);
        main.setContent(SalesTable);
        return main;
    }
    
    private TableView<Transaction> getTable() {

        TableView<Transaction> table = new TableView<>();
        TableColumn<Transaction, String> transactionDateCol = new TableColumn<>("Date");
        transactionDateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty().asString());

        TableColumn<Transaction, Double> transactionCostCol = new TableColumn<>("Cost");
        transactionCostCol.setCellValueFactory(cellData -> cellData.getValue().getCostProperty().asObject());

        table.getColumns().addAll(transactionDateCol, transactionCostCol);

        return table;
    }
}
