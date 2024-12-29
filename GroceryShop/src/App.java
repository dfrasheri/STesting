import java.io.File;

import Models.Admin;
import Models.Client;
import Models.Products;
import Models.User;
import Views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
    	/*Admin baseAdmin = new Admin("jane", "doe", "admin", "admin", "janedoe@email.com");
    	User baseUser = new User("john", "doe", "cashier", "cashier", "johndoe@email.com");
    	Client baseClient = new Client("n", "s", "12345");
    	Products baseProduct = new Products("n", 1, "a", "s", 3);
    	baseAdmin.addAdmin(baseAdmin);
    	baseAdmin.addUser(baseUser);
    	Admin.addClients(baseClient);
    	Admin.addProduct(baseProduct);*/

    	Admin.importAdminsToFile();
    	Admin.importUsersToFile();
    	Admin.importProductsToFile();
    	Admin.importClientsToFile();
    	
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView login = new LoginView();
        primaryStage = login.getLoginView();
        primaryStage.show();
    }
}
