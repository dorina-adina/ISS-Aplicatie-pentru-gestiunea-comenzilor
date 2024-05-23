package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    void openNewWindow(String fxmlFileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void handleAddCommand(ActionEvent event) throws IOException {
        openNewWindow("/AddCommand.fxml");
    }

    @FXML
    void handleUpdateCommand(ActionEvent event) throws IOException {
        openNewWindow("/UpdateCommand.fxml");
    }

    @FXML
    void handleShowCommands(ActionEvent event) throws IOException {
        openNewWindow("/ShowCommands.fxml");
    }

    @FXML
    void handleShowProducts(ActionEvent event) throws IOException {
        openNewWindow("/ShowProducts.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        openNewWindow("/Logout.fxml");
    }
}
