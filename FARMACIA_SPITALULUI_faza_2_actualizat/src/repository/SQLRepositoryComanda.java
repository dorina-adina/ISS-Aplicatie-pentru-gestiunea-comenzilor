package repository;

import domain.Comanda;
import domain.Produs;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SQLRepositoryComanda extends repo {
    private static final String JDBC_URL =
            "jdbc:sqlite:comanda.db";

    private Connection conn = null;

    public SQLRepositoryComanda() {
        openConnection();
        createSchema();
    }



    protected void openConnection() {
        try {

            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    void createSchema() {
//        try {
//            try (final Statement stmt = conn.createStatement()) {
//                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comenzi(id_comanda int, sectie varchar(40), data_comanda varchar(20), produse ArrayList<Produs>, cantitati ArrayList<Integer>, status varchar(20));");
//            }
//        } catch (SQLException e) {
//            System.err.println("[ERROR] createSchema : " + e.getMessage());
//        }
//    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS comenzi(id_comanda INTEGER PRIMARY KEY, sectie TEXT, data_comanda TEXT, produse TEXT, cantitati TEXT, status TEXT);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }



    void initTables() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO comenzi VALUES (?, ?, ?, ?, ?, ?)")) {
                int id = 1;
                String sectie = "orto";
                String data = "22.05.2024";
                String status = "in asteptare";
                ArrayList<Produs> produse = new ArrayList<>();
                Produs p = new Produs(123, "Aspenter", "prospect pentru Aspenter", 10.00, 12);
                produse.add(p);
                ArrayList<Integer> cantitati = new ArrayList<>();
                cantitati.add(12);
                statement.setInt(1, id);
                statement.setString(2, sectie);
                statement.setString(3, data);
                statement.setString(4, produseToString(produse));
                statement.setString(5, intListToString(cantitati));
                statement.setString(6, status);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



//    public void adaugaComandaProdus(Comanda p) {
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO comenzi VALUES (?, ?, ?, ?, ?, ?)")) {
//                statement.setInt(1, p.getId_comanda());
//                statement.setString(2, p.getSectie());
//                statement.setString(3, p.getData());
//                statement.setArray(4, (Array) p.getProduse());
//                statement.setArray(5, (Array) p.getCantitati());
//                statement.setString(6, p.getStatus());
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void remove(int id) {
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM comenzi WHERE id_comanda=?")) {
//                statement.setInt(1, id);
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateComandaProdus(Comanda pacient) {
//        try (PreparedStatement stmt = conn.prepareStatement("UPDATE comenzi SET sectie = ?, data = ?, produse = ?, cantitati = ?, status = ? WHERE id_comanda = ?;")) {
//            stmt.setString(1, pacient.getSectie());
//            stmt.setString(2, pacient.getData());
//            stmt.setArray(3, (Array) pacient.getProduse());
//            stmt.setArray(4, (Array) pacient.getCantitati());
//            stmt.setString(5, pacient.getStatus());
//            stmt.setInt(6, pacient.getId_comanda());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Comanda findComanda(int id) {
//        String sql = "SELECT * FROM comenzi WHERE id_comanda = ?";
//
//        try (
//                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    int id_comanda = resultSet.getInt("id_comanda");
//                    String sectie = resultSet.getString("sectie");
//                    String data = resultSet.getString("data");
//                    ArrayList<Produs> produse = (ArrayList<Produs>) resultSet.getArray("produse");
//                    ArrayList<Integer> cantitati = (ArrayList<Integer>) resultSet.getArray("cantitati");
//                    String status = resultSet.getString("status");
//
//                    return new Comanda(id_comanda, sectie, data, produse, cantitati, status);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public List<Comanda> getComenzi() {
//        ArrayList<Comanda> pacienti = new ArrayList<>();
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("SELECT * from comenzi"); ResultSet rs = statement.executeQuery();) {
//                while (rs.next()) {
//                    Comanda p = new Comanda(rs.getInt("id_comanda"), rs.getString("sectie"), rs.getString("data"),
//                            (ArrayList<Produs>) rs.getArray("produse"), (ArrayList<Integer>) rs.getArray("cantitati"),rs.getString("status"));
//                    pacienti.add(p);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return pacienti;
//
//    }

    public void adaugaComandaProdus(Comanda p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO comenzi VALUES (?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, p.getId_comanda());
                statement.setString(2, p.getSectie());
                statement.setString(3, p.getData());
                statement.setString(4, produseToString(p.getProduse()));
                statement.setString(5, intListToString(p.getCantitati()));
                statement.setString(6, p.getStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateComandaProdus(Comanda pacient) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE comenzi SET sectie = ?, data_comanda = ?, produse = ?, cantitati = ?, status = ? WHERE id_comanda = ?;")) {
            stmt.setString(1, pacient.getSectie());
            stmt.setString(2, pacient.getData());
            stmt.setString(3, produseToString(pacient.getProduse()));
            stmt.setString(4, intListToString(pacient.getCantitati()));
            stmt.setString(5, pacient.getStatus());
            stmt.setInt(6, pacient.getId_comanda());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comanda findComanda(int id) {
        String sql = "SELECT * FROM comenzi WHERE id_comanda = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id_comanda = resultSet.getInt("id_comanda");
                    String sectie = resultSet.getString("sectie");
                    String data = resultSet.getString("data_comanda");
                    List<Produs> produse = stringToProduse(resultSet.getString("produse"));
                    List<Integer> cantitati = stringToIntList(resultSet.getString("cantitati"));
                    String status = resultSet.getString("status");

                    return new Comanda(id_comanda, sectie, data, (ArrayList<Produs>) produse, (ArrayList<Integer>) cantitati, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Comanda> getComenzi() {
        List<Comanda> comenzi = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from comenzi"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Comanda comanda = new Comanda(
                            rs.getInt("id_comanda"),
                            rs.getString("sectie"),
                            rs.getString("data_comanda"),
                            (ArrayList<Produs>) stringToProduse(rs.getString("produse")),
                            (ArrayList<Integer>) stringToIntList(rs.getString("cantitati")),
                            rs.getString("status")
                    );
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return comenzi;
    }




    public static void main(String[] args) {
        SQLRepositoryComanda db_example = new SQLRepositoryComanda();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        ArrayList<Comanda> pacientiList = (ArrayList<Comanda>) db_example.getComenzi();
        for (Comanda pacient : pacientiList)
            System.out.println(pacient);



        db_example.closeConnection();
    }



    // Converteste o lista de produse intr-un string delimitat
    private String produseToString(List<Produs> produse) {
        StringBuilder sb = new StringBuilder();
        for (Produs produs : produse) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(produs.getId_produs()).append(":").append(produs.getNume()).append(":")
                    .append(produs.getProspect()).append(":").append(produs.getPret()).append(":")
                    .append(produs.getCantitate());
        }
        return sb.toString();
    }

    // Converteste un string delimitat intr-o lista de produse
    private List<Produs> stringToProduse(String str) {
        List<Produs> produse = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return produse;
        }
        String[] items = str.split(",");
        for (String item : items) {
            String[] fields = item.split(":");
            Produs produs = new Produs(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    fields[2],
                    Double.parseDouble(fields[3]),
                    Integer.parseInt(fields[4])
            );
            produse.add(produs);
        }
        return produse;
    }

    // Converteste o lista de intregi intr-un string delimitat
    private String intListToString(List<Integer> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    // Converteste un string delimitat intr-o lista de intregi
    private List<Integer> stringToIntList(String str) {
        List<Integer> list = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return list;
        }
        String[] items = str.split(",");
        for (String item : items) {
            list.add(Integer.parseInt(item));
        }
        return list;
    }


}

