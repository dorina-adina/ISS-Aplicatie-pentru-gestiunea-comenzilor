package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import domain.Produs;
import repository.SQLRepositoryProdus;

import java.util.List;

public class ShowProductsController {

    @FXML
    private ListView<String> productsListView;

    @FXML
    void initialize() {
        SQLRepositoryProdus produsRepository = new SQLRepositoryProdus();
        List<Produs> produse = produsRepository.getAll();

        for (Produs produs : produse) {
            int id = produs.getId_produs();
            String numeProdus = produs.getNume();
            String detaliiProdus = "Prospect: " + produs.getProspect() + ", Preț: " + produs.getPret() + ", Cantitate: " + produs.getCantitate();
            productsListView.getItems().add(id + " - " + numeProdus + " - " + detaliiProdus);
        }
    }
}
