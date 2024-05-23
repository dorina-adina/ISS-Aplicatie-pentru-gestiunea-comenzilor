package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.repoUser;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private repoUser userRepository = new repoUser();

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }


    private void handleLogin() {
        String id = idField.getText();
        String password = passwordField.getText();
        if (userRepository.verificaParola(id, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Bine ati venit!");
            // Open the main menu
            openMainMenu();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Username sau parola incorecte.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openMainMenu() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
