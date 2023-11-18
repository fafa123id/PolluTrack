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
    private JButton button1, button2, button3;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public DatabaseApp() {
        super("Database App");
        setTitle("Tempekkan Data");
        setLayout(new BorderLayout());

        // Tombol 1
        button1 = new JButton("Pencemaran Air");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDataFromDatabase("SELECT * FROM PENCEMARAN_AIR ");
            }
        });

        // Tombol 2
        button2 = new JButton("Pencemaran Tanah");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDataFromDatabase("SELECT * FROM PENCEMARAN_TANAH");
            }
        });

        // Tombol 3
        button3 = new JButton("Pencemaran Udara");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDataFromDatabase("SELECT * FROM PENCEMARAN_UDARA");
            }
        });

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        // Inisialisasi model tabel
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);

        // Panel utama
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayDataFromDatabase(String query) {
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "senopati123";

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatabaseApp();
            }
        });
    }
}

