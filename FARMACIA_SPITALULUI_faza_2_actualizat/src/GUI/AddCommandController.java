package GUI;

import domain.Comanda;
import domain.Produs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repository.SQLRepositoryComanda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCommandController {

    @FXML
    private TextField idComandaField;

    @FXML
    private TextField sectieField;

    @FXML
    private TextField dataComandaField;

    @FXML
    private TextField produseField;

    @FXML
    private TextField cantitatiField;

    @FXML
    private TextField statusField;

    @FXML
    private Button addButton;

    private SQLRepositoryComanda repositoryComanda;

    @FXML
    public void initialize() {
        repositoryComanda = new SQLRepositoryComanda();
    }

    @FXML
    void handleAddCommand(ActionEvent event) {
        try {
            int idComanda = Integer.parseInt(idComandaField.getText());
            String sectie = sectieField.getText();
            String dataComanda = dataComandaField.getText();
            String produseText = produseField.getText();
            String cantitatiText = cantitatiField.getText();
            String status = statusField.getText();

            List<String> produseList = Arrays.asList(produseText.split(","));
            List<String> cantitatiList = Arrays.asList(cantitatiText.split(","));

            if (produseList.size() != cantitatiList.size()) {
                showAlert(AlertType.ERROR, "Input Error", "Numarul de produse si cantitati trebuie sa fie egal.");
                return;
            }

            ArrayList<Produs> produse = new ArrayList<>();
            ArrayList<Integer> cantitati = new ArrayList<>();

            for (int i = 0; i < produseList.size(); i++) {
                String produsName = produseList.get(i);
                int cantitate = Integer.parseInt(cantitatiList.get(i));
                Produs produs = new Produs(i + 1, produsName, "default description", 0.0, cantitate);
                produse.add(produs);
                cantitati.add(cantitate);
            }

            Comanda comanda = new Comanda(idComanda, sectie, dataComanda, produse, cantitati, status);
            repositoryComanda.adaugaComandaProdus(comanda);
            showAlert(AlertType.INFORMATION, "Success", "Comanda a fost adaugata cu succes!");

            clearFields();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "ID-ul comenzii si cantitatile trebuie sa fie numere intregi.");
        } catch (Exception e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                showAlert(AlertType.ERROR, "Database Error", "ID-ul comenzii exista deja.");
            } else {
                showAlert(AlertType.ERROR, "Database Error", "A aparut o eroare la adaugarea comenzii: " + e.getMessage());
            }
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        idComandaField.clear();
        sectieField.clear();
        dataComandaField.clear();
        produseField.clear();
        cantitatiField.clear();
        statusField.clear();
    }
}
