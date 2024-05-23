package domain;

public class Farmacist {
    private String id_farmacist;
    public String nume;

    public Farmacist(String id_farmacist, String nume) {
        this.id_farmacist = id_farmacist;
        this.nume = nume;
    }

    public String getId_farmacist() {
        return id_farmacist;
    }

    public void setId_farmacist(String id_farmacist) {
        this.id_farmacist = id_farmacist;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Farmacist{" +
                "id_farmacist=" + id_farmacist +
                ", nume='" + nume + '\'' +
                '}';
    }
}
