package GUI;

import domain.Comanda;
import domain.Produs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import repository.SQLRepositoryComanda;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommandController {

    @FXML
    private TextField commandId;
    @FXML
    private TextField commandSection;
    @FXML
    private TextField commandDate;
    @FXML
    private TextField commandProducts;
    @FXML
    private TextField commandQuantities;
    @FXML
    private TextField commandStatus;

    private SQLRepositoryComanda repository = new SQLRepositoryComanda();

    @FXML
    void handleUpdateCommand(ActionEvent event) {
        try {
            if (commandId.getText() != null && !commandId.getText().isEmpty()) {
                int idComanda = Integer.parseInt(commandId.getText());
                String section = commandSection.getText();
                String date = commandDate.getText();
                String products = commandProducts.getText();
                String quantities = commandQuantities.getText();
                String status = commandStatus.getText();

                List<Produs> productList = productsToList(products);
                List<Integer> quantityList = quantitiesToList(quantities);

                System.out.println("ID Comanda: " + idComanda);
                System.out.println("Section: " + section);
                System.out.println("Date: " + date);
                System.out.println("Products: " + productList);
                System.out.println("Quantities: " + quantityList);
                System.out.println("Status: " + status);

                Comanda updatedCommand = new Comanda(idComanda, section, date, (ArrayList<Produs>) productList, (ArrayList<Integer>) quantityList, status);

                repository.updateComandaProdus(updatedCommand);

                showAlert(AlertType.INFORMATION, "Comanda actualizata", "Comanda a fost actualizata cu succes!");
            } else {
                showAlert(AlertType.ERROR, "Input Error", "ID-ul comenzii nu poate fi gol.");
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "ID-ul comenzii trebuie să fie un numar intreg.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "A aparut o eroare la actualizarea comenzii: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private List<Produs> productsToList(String products) {
        List<Produs> productList = new ArrayList<>();
        if (products != null && !products.isEmpty()) {
            String[] productStrings = products.split(",");
            for (String productString : productStrings) {
                System.out.println("Processing product string: " + productString); // Debug message
                String[] fields = productString.split(":");
                try {
                    Produs produs;
                    if (fields.length == 5) {
                        produs = new Produs(
                                Integer.parseInt(fields[0]),
                                fields[1],
                                fields[2],
                                Double.parseDouble(fields[3]),
                                Integer.parseInt(fields[4])
                        );
                    } else if (fields.length == 1) {
                        produs = new Produs(0, fields[0], "default prospect", 0.0, 0); // Default values
                    } else {
                        showAlert(AlertType.ERROR, "Input Error", "Formatul produsului nu este valid: " + productString);
                        continue;
                    }
                    productList.add(produs);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Input Error", "Datele produsului nu sunt valide: " + productString);
                }
            }
        }
        return productList;
    }

    private List<Integer> quantitiesToList(String quantities) {
        List<Integer> quantityList = new ArrayList<>();
        if (quantities != null && !quantities.isEmpty()) {
            String[] quantityStrings = quantities.split(",");
            for (String quantityString : quantityStrings) {
                try {
                    quantityList.add(Integer.parseInt(quantityString));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Input Error", "Cantitatea nu este valida: " + quantityString);
                }
            }
        }
        return quantityList;
    }
}
