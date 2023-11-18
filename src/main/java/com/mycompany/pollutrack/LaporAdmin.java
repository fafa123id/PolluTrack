/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pollutrack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ahmad
 */
public class LaporAdmin extends javax.swing.JFrame {
    public static String user="";
    private DefaultListModel<String> listModel;

    /**
     * Creates new form LaporAdmin
     */
    public LaporAdmin() {
        initComponents();
        user="";
        listModel = new DefaultListModel<>();
        setLocationRelativeTo(null);
        fetching();
        
         Search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterList();
         
                user="";
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
            
                user="";
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterList();
              
                user="";
            }
        });
    }
    private void filterList() {
        String searchTerm = Search.getText().toLowerCase();
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        
        // Memfilter elemen-elemen berdasarkan pencarian
        for (int i = 0; i < listModel.getSize(); i++) {
            String item = listModel.getElementAt(i).toLowerCase();
            if (item.contains(searchTerm)) {
                filteredModel.addElement(listModel.getElementAt(i));
            }
        }
        
        // Mengatur model baru untuk JList
        Listuser.setModel(filteredModel);
        
    }
     void fetching(){
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB)) {
            // Step 1: Create a statement
            Statement statement = connection.createStatement();

            // Step 2: Execute a query to fetch data from the database
            String selectDataSQL = "SELECT a.* FROM Pengguna a JOIN Laporan b ON a.Kode_Pengguna=b.Kode_Pengguna";
            ResultSet resultSet = statement.executeQuery(selectDataSQL);

            // Step 5: Add rows to the model
            while (resultSet.next()) {
               listModel.addElement(resultSet.getString("Username"));
            }
        Listuser.setModel(listModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tanah = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listuser = new javax.swing.JList<>();
        jButton5 = new javax.swing.JButton();
        air = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("PILIH USER");

        tanah.setText("Tampilkan Laporan");
        tanah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanahActionPerformed(evt);
            }
        });

        Listuser.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "ab", "bc" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Listuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListuserMouseClicked(evt);
            }
        });
        Listuser.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListuserValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Listuser);

        jButton5.setText("Back To Admin Menu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        air.setText("Hapus Laporan");
        air.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(air, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tanah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Search)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tanah)
                .addGap(6, 6, 6)
                .addComponent(air)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tanahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanahActionPerformed
        if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah user");
            return;
        }
        new TampilLapor().setVisible(true);
    }//GEN-LAST:event_tanahActionPerformed

    private void ListuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListuserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ListuserMouseClicked

    private void ListuserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListuserValueChanged
        user=Listuser.getSelectedValue();

    }//GEN-LAST:event_ListuserValueChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new MenuAdmin().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void airActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_airActionPerformed
        if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah user");
            return;
        }
        delete();
        listModel = new DefaultListModel<>();
        user="";
        fetching();
    }//GEN-LAST:event_airActionPerformed

    /**
     * @param args the command line arguments
     */
    void delete(){
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
         
         try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             PreparedStatement statement = connection.prepareStatement(String.format("DELETE Laporan WHERE Kode_Pengguna=(SELECT Kode_Pengguna FROM Pengguna WHERE Username='%s')", user))){
          
           statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Laporan dihapus");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Listuser;
    private javax.swing.JTextField Search;
    private javax.swing.JButton air;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton tanah;
    // End of variables declaration//GEN-END:variables
}