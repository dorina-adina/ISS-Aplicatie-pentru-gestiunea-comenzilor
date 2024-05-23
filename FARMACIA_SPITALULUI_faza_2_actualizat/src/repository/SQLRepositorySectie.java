package repository;


import domain.Sectie;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRepositorySectie extends repo {
    private static final String JDBC_URL =
            "jdbc:sqlite:sectie.db";

    private Connection conn = null;

    public SQLRepositorySectie() {
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sectii(id_sectie varchar(10), nume varchar(40));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }


    void initTables() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sectii VALUES (?, ?)")) {

                    String id = "orto";
                    String nume = "Ortopedie";
                    statement.setString(1, id);
                    statement.setString(2, nume);

                    statement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adaugaSectie(Sectie p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sectii VALUES (?, ?)")) {
                statement.setString(1, p.getId_sectie());
                statement.setString(2, p.getNume());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Sectie find(String id) {
        String sql = "SELECT * FROM sectii WHERE id_sectie = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id_sectie = resultSet.getString("id_sectie");
                    String nume = resultSet.getString("nume");


                    return new Sectie(id_sectie, nume);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Sectie> getAll() {
        ArrayList<Sectie> sectii = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from sectii"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Sectie p = new Sectie(rs.getString("id_sectie"), rs.getString("nume"));
                    sectii.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sectii;

    }


    public static void main(String[] args) {
        SQLRepositorySectie db_example = new SQLRepositorySectie();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        ArrayList<Sectie> sectiiList = (ArrayList<Sectie>) db_example.getAll();
        for (Sectie sectie : sectiiList)
            System.out.println(sectie);




        db_example.closeConnection();
    }

}

