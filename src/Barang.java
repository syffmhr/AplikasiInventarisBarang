
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Barang {
    private String kode;
    private String nama;
    private String kategori;
    private String jumlah;
    private String tahun;
    private String keterangan;
    private String lokasi;

    public Barang(String kode, String nama, String kategori, String jumlah, String tahun, String keterangan, String lokasi) {
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.tahun = tahun;
        this.keterangan = keterangan;
        this.lokasi = lokasi;
    }
    
    public String getKode() {
        return kode;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getKategori() {
        return kategori;
    }

    public String getJumlah() {
        return jumlah;
    }
    
    public String getTahun() {
        return tahun;
    }
    
    public String getKeterangan() {
        return keterangan;
    }

    public String getLokasi() {
        return lokasi;
    }
    
    public static void tambahBarang(Barang barang) {
        try (Connection conn = DriverManager.getConnection(DBConnection.DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO inventarisBarang (kode, nama, kategori, jumlah, tahun, keterangan, lokasi) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, barang.getKode());
            pstmt.setString(2, barang.getNama());
            pstmt.setString(3, barang.getKategori());
            pstmt.setString(4, barang.getJumlah());
            pstmt.setString(5, barang.getTahun());
            pstmt.setString(6, barang.getKeterangan());
            pstmt.setString(7, barang.getLokasi());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ubahBarang(int id, Barang barang) {
        try (Connection conn = DriverManager.getConnection(DBConnection.DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE inventarisBarang SET kode = ?, nama = ?, kategori = ?, jumlah = ?, tahun = ?, keterangan = ?, lokasi = ? WHERE id = ?")) {
            pstmt.setString(1, barang.getKode());
            pstmt.setString(2, barang.getNama());
            pstmt.setString(3, barang.getKategori());
            pstmt.setString(4, barang.getJumlah());
            pstmt.setString(5, barang.getTahun());
            pstmt.setString(6, barang.getKeterangan());
            pstmt.setString(7, barang.getLokasi());
            pstmt.setInt(8, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void hapusBarang(int id) {
        try (Connection conn = DriverManager.getConnection(DBConnection.DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM inventarisBarang WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getBarang() {
        try {
            Connection conn = DriverManager.getConnection(DBConnection.DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT * FROM inventarisBarang");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ResultSet cariBarang(String query) {
        try {
            Connection conn = DriverManager.getConnection(DBConnection.DB_URL);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM inventarisBarang WHERE kode LIKE ? OR nama LIKE ?");
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ekspor() {
        
    }

    public static void impor() {
        
    }
}
