/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pollutrack;

import static com.mycompany.pollutrack.TrackPencemaran.capitalizeFirstLetter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ahmad
 */
public class Kota extends javax.swing.JFrame {
    private DefaultListModel<String> listModel;
    static String pencemaranApa="";
    static String kotaApa="";
    /**
     * Creates new form Kota
     */
     
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
    public void Tracking() {
        if(kotaApa.equals(""))
          JOptionPane.showMessageDialog(this,"Pilih Kota Terlebih Dahulu");
        else{
        
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
        int kotaCemar = 0;
        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             Statement statement = connection.createStatement();) {
            String query = String.format("SELECT * From Lokasi WHERE Nama_Lokasi='%s'", Kota.kotaApa);
            ResultSet resultSet = statement.executeQuery(query);
       
            while (resultSet.next()) {
               kotaCemar= resultSet.getInt("ID_LOKASI");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    
       
        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             Statement statement = connection.createStatement();) {
            String query = String.format("SELECT * FROM Pencemaran_%s WHERE ID_LOKASI=%d", Kota.pencemaranApa,kotaCemar);
            ResultSet resultSet = statement.executeQuery(query);
       
            while (resultSet.next()) {
                tampilin();
                return; 
            }
            JOptionPane.showMessageDialog(this,"Database Kosong");
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
        }
    }
    public Kota() {
        kotaApa="";
        initComponents();
        setLocationRelativeTo(null);
        Search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterList();
                kotaApa="";
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
                kotaApa="";
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterList();
                kotaApa="";
            }
        });
        listModel = new DefaultListModel<>();
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             Statement statement = connection.createStatement();) {
            String query = "SELECT * FROM Lokasi WHERE ID_LOKASI>3";
            ResultSet resultSet = statement.executeQuery(query);
       
            while (resultSet.next()) {
                listModel.addElement(resultSet.getString("Nama_lokasi"));
            }

             
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
        Listuser.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listuser = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        udara = new javax.swing.JButton();
        tanah = new javax.swing.JButton();
        air = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jScrollPane2.setViewportView(jScrollPane1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("POLLUTRACK PENCEMARAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("PILIH KOTA");

        udara.setText("Track Pencemaran Udara");
        udara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                udaraActionPerformed(evt);
            }
        });

        tanah.setText("Track Pencemaran Tanah");
        tanah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanahActionPerformed(evt);
            }
        });

        air.setText("Track Pencemaran Air");
        air.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airActionPerformed(evt);
            }
        });

        jButton5.setText("Back To Main Menu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(air, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tanah, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(udara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(udara)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tanah)
                .addGap(8, 8, 8)
                .addComponent(air)
                .addGap(8, 8, 8)
                .addComponent(jButton5)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        MainMenu.b=new MainMenu();
        MainMenu.b.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tanahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanahActionPerformed
        pencemaranApa="tanah";
        Tracking();
    }//GEN-LAST:event_tanahActionPerformed

    private void udaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_udaraActionPerformed
        pencemaranApa="udara";
        Tracking();
       
    }//GEN-LAST:event_udaraActionPerformed

    private void airActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_airActionPerformed
        
        pencemaranApa="air";
        Tracking();
    }//GEN-LAST:event_airActionPerformed

    private void ListuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListuserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ListuserMouseClicked

    private void ListuserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListuserValueChanged
        kotaApa=(Listuser.getSelectedValue());
    }//GEN-LAST:event_ListuserValueChanged
    void tampilin(){
            new TrackPencemaran().setVisible(true); 
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Listuser;
    private javax.swing.JTextField Search;
    private javax.swing.JButton air;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton tanah;
    private javax.swing.JButton udara;
    // End of variables declaration//GEN-END:variables
}
