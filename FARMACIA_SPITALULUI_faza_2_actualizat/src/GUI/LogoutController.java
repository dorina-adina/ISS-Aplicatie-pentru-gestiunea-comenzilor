package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LogoutController {

    @FXML
    void initialize() {
        logout();
    }

    private void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmare logout");
        alert.setHeaderText("Confirmare logout");
        alert.setContentText("Sunteti sigur ca doriti sa va deconectati?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = null;
            Scene scene = alert.getDialogPane().getScene();
            if (scene != null) {
                stage = (Stage) scene.getWindow();
            }
            if (stage != null) {
                stage.close();
            }

            showLoginScreen();
        }
    }

    private void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Nu s-a putut incarca ecranul de login.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
