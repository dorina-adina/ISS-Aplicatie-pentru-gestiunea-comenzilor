package domain;

public class Sectie {
    protected String id_sectie;
    public String nume;

    public Sectie(String id_sectie, String nume) {
        this.id_sectie = id_sectie;
        this.nume = nume;
    }

    public String getId_sectie() {
        return id_sectie;
    }

    public void setId_sectie(String id_sectie) {
        this.id_sectie = id_sectie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Sectie{" +
                "id_sectie='" + id_sectie + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
