
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class DBConnection {
    public static final String DB_URL = "jdbc:sqlite:inventarisBarang.db";

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
        System.out.println("Connected to database.");
        String createTable = "CREATE TABLE IF NOT EXISTS inventarisBarang (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "nama TEXT NOT NULL, " +
                             "kategori TEXT NOT NULL, " +
                             "jumlah TEXT NOT NULL, " +
                             "tahun TEXT NOT NULL, " +
                             "keterangan TEXT NOT NULL)" +
                            "lokasi TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
            System.out.println("Table checked/created successfully.");
        }
    } catch (SQLException e) {
        System.err.println("Error connecting to database or creating table.");
        e.printStackTrace();
    }
    }
}
