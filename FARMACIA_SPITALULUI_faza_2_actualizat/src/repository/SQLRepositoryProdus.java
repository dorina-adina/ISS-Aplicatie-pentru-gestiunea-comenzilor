package repository;

import domain.Produs;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLRepositoryProdus extends repo {
    private static final String JDBC_URL =
            "jdbc:sqlite:produse.db";

    private Connection conn = null;

    public SQLRepositoryProdus() {
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


    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS produse(id_produs int, nume varchar(40), prospect varchar(200), pret double, cantitate int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }


    void initTables() throws SQLException {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO produse VALUES (?, ?, ?, ?, ?)")) {
                    int id = 123;
                    String nume = "Aspenter";
                    String prospect = "prospect pentru Aspenter";
                    double pret = 10;
                    int cantitate = 12;
                    statement.setInt(1, id);
                    statement.setString(2, nume);
                    statement.setString(3, prospect);
                    statement.setDouble(4, pret);
                    statement.setInt(5, cantitate);
                    statement.executeUpdate();
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adaugaProdus(Produs p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO produse VALUES (?, ?, ?, ?, ?)")) {
                statement.setInt(1, p.getId_produs());
                statement.setString(2, p.getNume());
                statement.setString(3, p.getProspect());
                statement.setDouble(4, p.getPret());
                statement.setInt(5, p.getCantitate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Produs findProdus(int id) {
        String sql = "SELECT * FROM produse WHERE id_produs = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id_produs = resultSet.getInt("id_produs");
                    String nume = resultSet.getString("nume");
                    String prospect = resultSet.getString("prospect");
                    Double pret = resultSet.getDouble("pret");
                    int cantitate = resultSet.getInt("cantitate");

                    return new Produs(id_produs, nume, prospect, pret, cantitate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Produs> getAll() {
        ArrayList<Produs> produse = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from produse"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Produs p = new Produs(rs.getInt("id_produs"), rs.getString("nume"), rs.getString("prospect"),
                            rs.getDouble("pret"), rs.getInt("cantitate"));
                    produse.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produse;

    }



    public static void main(String[] args) throws SQLException {
        SQLRepositoryProdus db_example = new SQLRepositoryProdus();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        ArrayList<Produs> produseList = (ArrayList<Produs>) db_example.getAll();
        for (Produs produs : produseList)
            System.out.println(produs);



        db_example.closeConnection();
    }


}

