package repository;


import domain.Farmacist;
import domain.Sectie;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRepositoryFarmacist extends repo {
    private static final String JDBC_URL =
            "jdbc:sqlite:farmacist.db";

    private Connection conn = null;

    public SQLRepositoryFarmacist() {
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS farmacisti(id_farmacist varchar(10), nume varchar(40));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }


    void initTables() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO farmacisti VALUES (?, ?)")) {

                String id = "1234";
                String nume = "Poptean Tudor";
                statement.setString(1, id);
                statement.setString(2, nume);

                statement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adaugaFarmacist(Farmacist p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sectii VALUES (?, ?)")) {
                statement.setString(1, p.getId_farmacist());
                statement.setString(2, p.getNume());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Farmacist find(String id) {
        String sql = "SELECT * FROM farmacisti WHERE id_farmacist = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id_farmacist = resultSet.getString("id_farmacist");
                    String nume = resultSet.getString("nume");


                    return new Farmacist(id_farmacist, nume);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Farmacist> getAllFarmacisti() {
        ArrayList<Farmacist> farmacisti = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from farmacisti"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Farmacist p = new Farmacist(rs.getString("id_farmacist"), rs.getString("nume"));
                    farmacisti.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return farmacisti;

    }


    public static void main(String[] args) {
        SQLRepositoryFarmacist db_example = new SQLRepositoryFarmacist();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        ArrayList<Farmacist> sectiiList = (ArrayList<Farmacist>) db_example.getAllFarmacisti();
        for (Farmacist sectie : sectiiList)
            System.out.println(sectie);




        db_example.closeConnection();
    }

}

