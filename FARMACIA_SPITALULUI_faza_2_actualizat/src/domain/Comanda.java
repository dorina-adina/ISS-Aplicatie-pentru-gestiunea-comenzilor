package domain;

import java.util.ArrayList;

public class Comanda {
    protected int id_comanda;
    protected String sectie;
    public String data;
    public ArrayList<Produs> produse = new ArrayList<>();
    public ArrayList<Integer> cantitati = new ArrayList<>();

    public String status;

    public Comanda(int id_comanda, String sectie, String data, ArrayList<Produs> produse, ArrayList<Integer> cantitati, String status) {
        this.id_comanda = id_comanda;
        this.sectie = sectie;
        this.data = data;
        this.produse = produse;
        this.cantitati = cantitati;
        this.status = status;
    }

    public void adaugaProdus(Produs produs, int cantitate) {
        produse.add(produs);
        cantitati.add(cantitate);
    }

    public void setProduse(ArrayList<Produs> produse) {
        this.produse = produse;
    }

    public void setCantitati(ArrayList<Integer> cantitati) {
        this.cantitati = cantitati;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public String getSectie() {
        return sectie;
    }

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Produs> getProduse() {
        return produse;
    }

    public ArrayList<Integer> getCantitati() {
        return cantitati;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id_comanda=" + id_comanda +
                ", sectie='" + sectie + '\'' +
                ", data='" + data + '\'' +
                ", produse=" + produse +
                ", cantitati=" + cantitati +
                ", status='" + status + '\'' +
                '}';
    }
}
