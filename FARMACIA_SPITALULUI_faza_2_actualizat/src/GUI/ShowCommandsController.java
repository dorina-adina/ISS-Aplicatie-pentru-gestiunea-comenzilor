package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import repository.SQLRepositoryComanda;
import domain.Comanda;
import domain.Produs;

import java.util.List;

public class ShowCommandsController {

    @FXML
    private ListView<String> commandsListView;

    @FXML
    void initialize() {
        SQLRepositoryComanda comandaRepository = new SQLRepositoryComanda();
        List<Comanda> comenzi = comandaRepository.getComenzi();

        for (Comanda comanda : comenzi) {
            StringBuilder commandDetails = new StringBuilder();
            commandDetails.append("ID: ").append(comanda.getId_comanda())
                    .append(", Sectie: ").append(comanda.getSectie())
                    .append(", Data: ").append(comanda.getData())
                    .append(", Status: ").append(comanda.getStatus())
                    .append("\nProduse:\n");

            List<Produs> produse = comanda.getProduse();
            List<Integer> cantitati = comanda.getCantitati();
            for (int i = 0; i < produse.size(); i++) {
                commandDetails.append("- ").append(produse.get(i).getNume())
                        .append(": ").append(cantitati.get(i));
            }

            commandsListView.getItems().add(commandDetails.toString());
        }
    }
}
