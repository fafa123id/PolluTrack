package com.mycompany.pollutrack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseApp extends JFrame {
    private JButton button2;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public DatabaseApp() {
        super("Database App");
        setTitle("Tempekkan Data");
        setLayout(new BorderLayout());


        // Tombol 2
        button2 = new JButton("Back");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.c.dispose();
                new MainMenu().setVisible(true);
            }
        });

        

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(button2);
        

        // Inisialisasi model tabel
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);

        // Panel utama
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);
        displayDataFromDatabase("SELECT Nama_Lokasi, b.Nilai_IP AS 'Indeks Pencemaran Air', c.ISPU AS 'ISPU', d.Tingkat_pencemaran_tanah AS 'Indeks Pencemaran Tanah',\n" +
"(0.376*b.Nilai_IP) + (0.405*c.ISPU) + (0.219*d.Tingkat_pencemaran_tanah) AS IKLH,\n" +
"CASE\n" +
"        WHEN (0.376*b.Nilai_IP) + (0.405*c.ISPU) + (0.219*d.Tingkat_pencemaran_tanah)<= 25 THEN 'Sangat Sehat'\n" +
"        WHEN (0.376*b.Nilai_IP) + (0.405*c.ISPU) + (0.219*d.Tingkat_pencemaran_tanah) BETWEEN 25 AND 50 THEN 'Sehat'\n" +
"        WHEN (0.376*b.Nilai_IP) + (0.405*c.ISPU) + (0.219*d.Tingkat_pencemaran_tanah) BETWEEN 50 AND 75 THEN 'Kurang Sehat'\n" +
"        WHEN (0.376*b.Nilai_IP) + (0.405*c.ISPU) + (0.219*d.Tingkat_pencemaran_tanah) BETWEEN 75 AND 100 THEN 'Tidak Sehat'\n" +
"        ELSE 'Berbahaya'\n" +
"END\n" +
"AS 'Status Kesehatan Lingkungan'\n" +
"From Lokasi a \n" +
"Join Pencemaran_air b on a.ID_LOKASI=b.ID_LOKASI\n" +
"Join Pencemaran_udara c on a.ID_LOKASI=c.ID_LOKASI\n" +
"Join Pencemaran_tanah d on a.ID_LOKASI=d.ID_LOKASI\n" +
"ORDER BY \n" +
"    (0.376 * b.Nilai_IP) + (0.405 * c.ISPU) + (0.219 * d.Tingkat_pencemaran_tanah) ASC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayDataFromDatabase(String query) {
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";

        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Menghapus semua baris yang ada di tabel
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            // Mendapatkan metadata dari ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Menambahkan nama kolom ke model tabel
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Menampilkan hasil ke tabel
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

