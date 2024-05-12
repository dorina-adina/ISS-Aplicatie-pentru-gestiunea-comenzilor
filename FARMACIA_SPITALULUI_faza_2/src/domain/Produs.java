package domain;

public class Produs {
    protected int id_produs;
    public String nume;
    public String prospect;
    public Double pret;
    public int cantitate;

    public Produs(int id_produs, String nume, String prospect, Double pret, int cantitate) {
        this.id_produs = id_produs;
        this.nume = nume;
        this.prospect = prospect;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getProspect() {
        return prospect;
    }

    public void setProspect(String prospect) {
        this.prospect = prospect;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }


    @Override
    public String toString() {
        return "Produs{" +
                "id_produs=" + id_produs +
                ", nume='" + nume + '\'' +
                ", prospect='" + prospect + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                '}';
    }
}
