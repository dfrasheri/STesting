package Views;

import Models.Admin;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView {
    
    Stage LoginViewStage = new Stage();

    //Organization    
    private VBox MainPane = new VBox(10);
    private GridPane NodePane = new GridPane();

    //Nodes
    Text mainText = new Text("Login");
    Label message = new Label("");
    Label LoginUsername = new Label("Username:");
    Label LoginPassword = new Label("Password:");
    TextField LoginUsernametxf = new TextField();
    TextField LoginPasswordtxf = new TextField();
    Button Loginbt = new Button("Login");

    public Stage getLoginView() {
        
        NodePane.setHgap(5);
        NodePane.setVgap(5);
        NodePane.add(LoginUsername, 0, 0);
        NodePane.add(LoginPassword, 0, 1);
        NodePane.add(LoginUsernametxf, 1, 0);
        NodePane.add(LoginPasswordtxf, 1, 1);
        message.setAlignment(Pos.CENTER);

        mainText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 70));;
        StackPane a = new StackPane(mainText);
        StackPane b = new StackPane(Loginbt);

        MainPane.setPadding(new Insets(30));
        MainPane.getChildren().addAll(a, NodePane, b, message);

        //Handler
        Loginbt.setOnAction(e -> {
            LoginView Log = new LoginView();
            String role = Log.Role(LoginUsernametxf.getText(), LoginPasswordtxf.getText());
            if(role.equals("User")){
                LoginViewStage.close();
                new UserView().getUserView().show();
           }
           else if(role.equals("Admin")){
                LoginViewStage.close();
                new AdminView().getAdminView().show();
            }
            else{
                message.setText("Username or password are incorrect.");
            }

        });

        LoginViewStage.setTitle("Login Page");
        LoginViewStage.show();
        LoginViewStage.setScene(new Scene(MainPane, 300, 400));
        
        return LoginViewStage;
    }

    public String Role(String username, String password) {

    	for (int i = 0; i < User.getUsers().size(); i++) {
            if (User.getUsers().get(i).getUsername().equals(username) &&
                User.getUsers().get(i).getPassword().equals(password)) {
                return "User";
            }
        }

        for (int i = 0; i < Admin.getAdmins().size(); i++) {
            if (Admin.getAdmins().get(i).getUsername().equals(username) &&
                Admin.getAdmins().get(i).getPassword().equals(password)) {
                return "Admin";
            }
        }

        return "invalid input";
    }
}