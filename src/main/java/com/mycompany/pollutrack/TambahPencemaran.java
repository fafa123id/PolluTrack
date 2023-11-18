/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pollutrack;

import static com.mycompany.pollutrack.ModifUser.fetchtable;
import static com.mycompany.pollutrack.ModifUser.tabel;
import static com.mycompany.pollutrack.ModifUser.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmad
 */
public class TambahPencemaran extends javax.swing.JFrame {
    public static DefaultListModel<String> listModel;
     public static String user="";
     public static String pencemaran="";
    /**
     * Creates new form ModifUser
     */
    static void fetching(){
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
        try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB)) {
            // Step 1: Create a statement
            Statement statement = connection.createStatement();

            // Step 2: Execute a query to fetch data from the database
            String selectDataSQL = "SELECT * FROM Lokasi WHERE ID_LOKASI>3";
            ResultSet resultSet = statement.executeQuery(selectDataSQL);

            // Step 5: Add rows to the model
            while (resultSet.next()) {
               listModel.addElement(resultSet.getString("Nama_Lokasi"));
            }
        Listuser.setModel(listModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fetchtable(String a){
        fetchuser.setVisible(false);
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
         try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB)) {
            // Step 1: Create a statement
            Statement statement = connection.createStatement();

            // Step 2: Execute a query to fetch data from the database
            String selectDataSQL =String.format("SELECT b.Nama_Lokasi,a.* FROM Pencemaran_%s a JOIN LOKASI b ON a.ID_LOKASI=b.ID_LOKASI WHERE Nama_LOKASI='%s'", a,user);
            ResultSet resultSet = statement.executeQuery(selectDataSQL);

            // Step 3: Create a DefaultTableModel
            DefaultTableModel tableModel = new DefaultTableModel();

            // Step 4: Add columns to the model
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));
            }

            // Step 5: Add rows to the model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
                tabel.setModel(tableModel);
                fetchuser.setVisible(true);
                Scrolltabel.setVisible(true);
                tmbh.setVisible(false);
                modif.setVisible(true);
                return;
                
            }modif.setVisible(false);
            tmbh.setVisible(true);
            Scrolltabel.setVisible(false);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    /**
     * Creates new form TambahPencemaran
     */
    public TambahPencemaran() {
        listModel = new DefaultListModel<>();
        initComponents();
        modif.setVisible(false);
        tmbh.setVisible(false);
        setLocationRelativeTo(null);
        fetching();
        fetchuser.setVisible(false);
         Search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterList();
                fetchuser.setVisible(false);
                user="";
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
                fetchuser.setVisible(false);
                user="";
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterList();
                fetchuser.setVisible(false);
                user="";
            }
        });    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Listuser = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        tmbh = new javax.swing.JPanel();
        tambah = new javax.swing.JButton();
        udara = new javax.swing.JButton();
        air = new javax.swing.JButton();
        fetchuser = new javax.swing.JPanel();
        Scrolltabel = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tanah = new javax.swing.JButton();
        keadmin = new javax.swing.JButton();
        modif = new javax.swing.JPanel();
        edit1 = new javax.swing.JButton();
        tambah1 = new javax.swing.JButton();
        air1 = new javax.swing.JButton();
        air2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Listuser.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "ab", "bc" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Listuser.setAutoscrolls(false);
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

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setText("PILIH LOKASI");

        tambah.setText("Tambah Data");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tmbhLayout = new javax.swing.GroupLayout(tmbh);
        tmbh.setLayout(tmbhLayout);
        tmbhLayout.setHorizontalGroup(
            tmbhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmbhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tambah))
        );
        tmbhLayout.setVerticalGroup(
            tmbhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmbhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tambah)
                .addGap(30, 30, 30))
        );

        udara.setText("Lihat Data Pencemaran Udara");
        udara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                udaraActionPerformed(evt);
            }
        });

        air.setText("Lihat Data Pencemaran Air");
        air.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.setEnabled(false);
        Scrolltabel.setViewportView(tabel);

        javax.swing.GroupLayout fetchuserLayout = new javax.swing.GroupLayout(fetchuser);
        fetchuser.setLayout(fetchuserLayout);
        fetchuserLayout.setHorizontalGroup(
            fetchuserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fetchuserLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(Scrolltabel, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fetchuserLayout.setVerticalGroup(
            fetchuserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fetchuserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Scrolltabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tanah.setText("Lihat Data Pencemaran Tanah");
        tanah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanahActionPerformed(evt);
            }
        });

        keadmin.setText("Back To Admin Menu");
        keadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keadminActionPerformed(evt);
            }
        });

        edit1.setText("Edit Data");
        edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit1ActionPerformed(evt);
            }
        });

        tambah1.setText("Hapus Data");
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modifLayout = new javax.swing.GroupLayout(modif);
        modif.setLayout(modifLayout);
        modifLayout.setHorizontalGroup(
            modifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edit1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tambah1))
        );
        modifLayout.setVerticalGroup(
            modifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tambah1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit1))
        );

        air1.setText("Tambah Lokasi");
        air1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                air1ActionPerformed(evt);
            }
        });

        air2.setText("Hapus Lokasi");
        air2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                air2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(air, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(udara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tanah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keadmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(air1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(air2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(tmbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(modif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fetchuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(5, 5, 5)
                .addComponent(fetchuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tmbh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(udara)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tanah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(air)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(air1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(air2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keadmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void udaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_udaraActionPerformed
        if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah Lokasii");
            return;
        }
        pencemaran="udara";
        fetchtable(pencemaran);
        fetchuser.setVisible(true);
       
        
    }//GEN-LAST:event_udaraActionPerformed

    private void airActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_airActionPerformed
        if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah Lokasi");
            return;
        }
        pencemaran="air";
        fetchtable(pencemaran);
        fetchuser.setVisible(true);
        
    }//GEN-LAST:event_airActionPerformed

    private void tanahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanahActionPerformed
        if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah Lokasi");
            return;
        }
        pencemaran="tanah";
        fetchtable(pencemaran);
        fetchuser.setVisible(true);
    }//GEN-LAST:event_tanahActionPerformed

    private void keadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keadminActionPerformed
        this.dispose();
        new MenuAdmin().setVisible(true);
    }//GEN-LAST:event_keadminActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        new AddData().setVisible(true);
    }//GEN-LAST:event_tambahActionPerformed

    private void edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit1ActionPerformed
            
        new EditingData().setVisible(true);
    }//GEN-LAST:event_edit1ActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        hapus();
        fetchtable(pencemaran);
        JOptionPane.showMessageDialog(this, "Hapus Data Berhasil");
    }//GEN-LAST:event_tambah1ActionPerformed

    private void air1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_air1ActionPerformed
        new tambahLokasi().setVisible(true);
        tmbh.setVisible(false);
        modif.setVisible(false);
        fetchuser.setVisible(false);
                
    }//GEN-LAST:event_air1ActionPerformed

    private void air2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_air2ActionPerformed
         if(user.equals("")){
            JOptionPane.showMessageDialog(this, "Pilih sebuah lokasi");
            return;
        }

        delete();
        listModel= new DefaultListModel<>();
        fetching();
        fetchuser.setVisible(false);
        tmbh.setVisible(false);
        modif.setVisible(false);
        user="";
        pencemaran="";
        
    }//GEN-LAST:event_air2ActionPerformed

    private void ListuserValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListuserValueChanged
        user=Listuser.getSelectedValue();
        fetchuser.setVisible(false);
        modif.setVisible(false);
        tmbh.setVisible(false);

    }//GEN-LAST:event_ListuserValueChanged

    private void ListuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListuserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ListuserMouseClicked
    void delete(){
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
         
         try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             PreparedStatement statement = connection.prepareStatement("Delete LOKASI Where NAMA_LOKASI ='"+user+"'")){
          
           statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Lokasi dihapus");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }
    void hapus(){
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=BDKEL6;encrypt=true;trustServerCertificate=true;";
        String usernameDB = "sa";
        String passwordDB = "fafa12345";
         
         try (Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);
             PreparedStatement statement = connection.prepareStatement(String.format("DELETE Pencemaran_%s WHERE ID_LOKASI=(SELECT ID_LOKASI FROM LOKASI WHERE NAMA_LOKASI='%s')",TambahPencemaran.pencemaran,TambahPencemaran.user))){
          
           statement.executeUpdate();
           
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JList<String> Listuser;
    public static javax.swing.JScrollPane Scrolltabel;
    private javax.swing.JTextField Search;
    private javax.swing.JButton air;
    private javax.swing.JButton air1;
    private javax.swing.JButton air2;
    private javax.swing.JButton edit1;
    public static javax.swing.JPanel fetchuser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keadmin;
    public static javax.swing.JPanel modif;
    public static javax.swing.JTable tabel;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambah1;
    private javax.swing.JButton tanah;
    public static javax.swing.JPanel tmbh;
    private javax.swing.JButton udara;
    // End of variables declaration//GEN-END:variables
}
