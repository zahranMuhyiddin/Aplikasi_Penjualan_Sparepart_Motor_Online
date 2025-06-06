/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectakhir;

import base.Flogin;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myConnection.DBO;
import myEntity.ProdukBaru;
import myEntity.Transaksi;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author LENOVO
 */
public class Menukasir extends javax.swing.JFrame {

    DBO db = new DBO();
    Connection cnn = db.getConnection();
    Properties props = new Properties();
    DefaultListModel<String> modelPesan;

    /**
     * Creates new form Register
     */
    public Menukasir() {
        initComponents();
        table_update();
        dataPenjualan.setVisible(false);
        framePenjualan.setVisible(true);

        new Thread(this::consumekafka).start();
    }

    private void table_update() {
        int cc;

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbproduk.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Id_Produk"));
                    v2.add(rs.getString("Nama_Produk"));
                    v2.add(rs.getString("harga_satuan"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbdetailbeli.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Id_Produk"));
                    v2.add(rs.getString("Nama_Produk"));
                    v2.add(rs.getString("Stok"));
                    v2.add(rs.getString("harga_satuan"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addtable(String Id_Produk, String Nama_Produk, int Jumlah, int Total) {

        DefaultTableModel dt = (DefaultTableModel) tbbeli.getModel();

        dt.setRowCount(0);
        // Create an ArrayList to store the row data
        List<Object> row = new ArrayList<>();
        row.add(Id_Produk);
        row.add(Nama_Produk);
        row.add(Jumlah);
        row.add(Total);

        // Add the row to the table model
        dt.addRow(row.toArray());
    }

    private void addtables(String id, String nama_produk, int jumlah, int harga) {
        if (id == null || nama_produk == null) {
            System.out.println("Nilai id atau nama_produk adalah null.");
            return;
        }

        DefaultTableModel dfTujuan = (DefaultTableModel) tbbeli.getModel();
        boolean found = false;

        for (int i = 0; i < dfTujuan.getRowCount(); i++) {
            Object idTable = dfTujuan.getValueAt(i, 0);
            Object jumlahTable = dfTujuan.getValueAt(i, 2);

            if (idTable != null && idTable.toString().equals(id)) {
                int jumlahBaru = (jumlahTable != null ? Integer.parseInt(jumlahTable.toString()) : 0) + jumlah;
                int totalHarga = jumlahBaru * harga;
                dfTujuan.setValueAt(jumlahBaru, i, 2);
                dfTujuan.setValueAt(totalHarga, i, 3);
                found = true;
                break;
            }
        }

        if (!found) {
            dfTujuan.setRowCount(0);
            dfTujuan.addRow(new Object[]{id, nama_produk, jumlah, harga * jumlah});
        }

        cal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btlogout = new javax.swing.JButton();
        bgmenu = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        framePenjualan = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        del = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbeli = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        bck = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproduk = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        pay = new javax.swing.JTextField();
        bal = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        b = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        LBEXIT = new javax.swing.JLabel();
        dataPenjualan = new javax.swing.JInternalFrame();
        pndp3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbdetailbeli = new javax.swing.JTable();
        pndp1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pndp2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        modelPesan = new DefaultListModel<>();
        listname = new javax.swing.JList<>(modelPesan);
        txtidk = new javax.swing.JTextField();
        txtidp = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        btdel = new javax.swing.JButton();
        txtidsupplier = new javax.swing.JTextField();
        pndp = new javax.swing.JPanel();
        btsearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtcari2 = new javax.swing.JTextField();
        LBEXIT2 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(85, 85, 85));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("SPAREPART MOTOR");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 250, 40));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MARKET");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 250, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KASIR");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 60, 30));

        btlogout.setBackground(new java.awt.Color(204, 204, 204));
        btlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        btlogout.setText("LOG OUT");
        btlogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btlogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btlogout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 100, 30));

        bgmenu.setBackground(new java.awt.Color(0, 0, 0));
        bgmenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        bgmenu.setForeground(new java.awt.Color(255, 255, 255));
        bgmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sparepart.jpg"))); // NOI18N
        bgmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgmenuMouseClicked(evt);
            }
        });
        jPanel2.add(bgmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 340, 190));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Penjualan");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Daftar Produk");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 200, 250, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 570));

        framePenjualan.setVisible(true);
        framePenjualan.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });
        jPanel1.add(del, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 100, -1));

        tbbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Id_Produk", "Nama_Produk", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tbbeli);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 320, 320));

        txtcari.setText("Cari Produk anda");
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));

        bck.setText("cari");
        bck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bckActionPerformed(evt);
            }
        });
        jPanel1.add(bck, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, -1));

        tbproduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id_Produk", "Nama_Produk", "harga_satuan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbprodukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbproduk);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 320, 320));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Keranjang ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total      :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 100, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Cash      :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 90, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Balance :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, 20));

        total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        total.setText("0");
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, 30));

        pay.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        pay.setText("0");
        pay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payKeyReleased(evt);
            }
        });
        jPanel1.add(pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 100, 30));

        bal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        bal.setText("0");
        jPanel1.add(bal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, 20));

        jButton12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButton12.setText("Pay & Print");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 160, 40));

        b.setColumns(20);
        b.setRows(5);
        jScrollPane3.setViewportView(b);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 380, 310, 150));

        jButton3.setText("back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 70, -1));

        framePenjualan.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 680, 540));

        jPanel3.setBackground(new java.awt.Color(85, 85, 85));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBEXIT.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        LBEXIT.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT.setText("X");
        LBEXIT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXITMouseClicked(evt);
            }
        });
        jPanel3.add(LBEXIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, -1, 30));

        framePenjualan.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 30));

        getContentPane().add(framePenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 690, 600));

        dataPenjualan.setVisible(true);
        dataPenjualan.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pndp3.setBackground(new java.awt.Color(85, 85, 85));
        pndp3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DAFTAR PRODUK");
        pndp3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 170, 40));

        tbdetailbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_produk", "nama_produk", "stok", "harga"
            }
        ));
        jScrollPane7.setViewportView(tbdetailbeli);

        pndp3.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 630, 330));

        dataPenjualan.getContentPane().add(pndp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 670, 370));

        pndp1.setBackground(new java.awt.Color(85, 85, 85));
        pndp1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Notifikasi Kafka");
        pndp1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 130, 30));

        dataPenjualan.getContentPane().add(pndp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 680, 30));

        pndp2.setBackground(new java.awt.Color(153, 153, 153));
        pndp2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listname.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listnameValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(listname);

        pndp2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 80));
        pndp2.add(txtidk, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));
        pndp2.add(txtidp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 110, -1));
        pndp2.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 180, -1));
        pndp2.add(txtjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 180, -1));

        btdel.setText("DEL");
        btdel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btdelMouseClicked(evt);
            }
        });
        btdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdelActionPerformed(evt);
            }
        });
        pndp2.add(btdel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 60, 80));
        pndp2.add(txtidsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 110, -1));

        dataPenjualan.getContentPane().add(pndp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 680, 100));

        pndp.setBackground(new java.awt.Color(153, 153, 153));
        pndp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp.setForeground(new java.awt.Color(255, 255, 255));
        pndp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btsearch.setText("Search");
        btsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsearchActionPerformed(evt);
            }
        });
        pndp.add(btsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 80, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        pndp.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));
        pndp.add(txtcari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 500, 30));

        LBEXIT2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBEXIT2.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT2.setText("X");
        LBEXIT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXIT2MouseClicked(evt);
            }
        });
        pndp.add(LBEXIT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 20));

        dataPenjualan.getContentPane().add(pndp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 80));

        getContentPane().add(dataPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 680, -1));

        setSize(new java.awt.Dimension(949, 578));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlogoutActionPerformed
        Flogin fl = new Flogin();
        fl.setVisible(true);
        fl.setLocationRelativeTo(null);
        fl.setDefaultCloseOperation(menuAdminHome.EXIT_ON_CLOSE);
        setVisible(false);
    }//GEN-LAST:event_btlogoutActionPerformed

    private void bgmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgmenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bgmenuMouseClicked

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        DefaultTableModel dfTujuan = (DefaultTableModel) tbbeli.getModel();
        dfTujuan.setRowCount(0);
    }//GEN-LAST:event_delActionPerformed

    private void bckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bckActionPerformed

        int cc;
        String idproduk;
        idproduk = txtcari.getText();
        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk where Id_Produk =?");
            pst.setString(1, idproduk);
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbproduk.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Id_Produk"));
                    v2.add(rs.getString("Id_Supplier"));
                    v2.add(rs.getString("Nama_Produk"));
                    v2.add(rs.getString("Stok"));
                    v2.add(rs.getString("harga_satuan"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bckActionPerformed

    private void payKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payKeyReleased
        pay();
    }//GEN-LAST:event_payKeyReleased


    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        DefaultTableModel model = (DefaultTableModel) tbbeli.getModel();
        int rowCount = model.getRowCount();

        // Iterasi melalui setiap baris di tabel tbbeli
        for (int i = 0; i < rowCount; i++) {
            String id_produk = (String) model.getValueAt(i, 0);
            String nama_produk = (String) model.getValueAt(i, 1);
            int jumlah = (int) model.getValueAt(i, 2);
            int total = (int) model.getValueAt(i, 3);
            
            String idkasir = "k1";
            
            Transaksi tr = new Transaksi();
            tr.setId_kasir(idkasir);
            tr.setId_produk(id_produk);
            tr.setJumlah(String.valueOf(jumlah));
            tr.setTotal(String.valueOf(total));
            try (Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props)) {
                producer.send(new ProducerRecord<>("detailpembelian", "", tr.toString())
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal mengirim data: " + e.getMessage());
            }
        }

        try {

            pay();
            b.print(); //print
            DefaultTableModel dt = (DefaultTableModel) tbbeli.getModel();
            dt.setRowCount(0);
            b.setText("");

        } catch (PrinterException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tbprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodukMouseClicked

        DefaultTableModel dfProduk = (DefaultTableModel) tbproduk.getModel();
        int selectedIndex = tbproduk.getSelectedRow();

        if (selectedIndex != -1) {
            Object idObj = dfProduk.getValueAt(selectedIndex, 0);
            Object namaProdukObj = dfProduk.getValueAt(selectedIndex, 1);
            Object hargaObj = dfProduk.getValueAt(selectedIndex, 2);

            if (idObj != null && namaProdukObj != null && hargaObj != null) {
                String id = idObj.toString();
                String nama_produk = namaProdukObj.toString();
                int harga = Integer.parseInt(hargaObj.toString());

                addtables(id, nama_produk, 1, harga);
            } else {
                // Tampilkan pesan kesalahan atau log jika diperlukan
                System.out.println("Ada nilai null pada baris yang dipilih.");
            }
        }

    }//GEN-LAST:event_tbprodukMouseClicked

    private void LBEXITMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXITMouseClicked
        System.exit(0);
    }//GEN-LAST:event_LBEXITMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        table_update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        props.put("bootstrap.servers", "192.168.100.7:9092, 192.168.100.164:9093");
        props.put("linger.ms", 2);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }//GEN-LAST:event_formWindowOpened

    private void listnameValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listnameValueChanged

    }//GEN-LAST:event_listnameValueChanged

    private void btdelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btdelMouseClicked

    private void btdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdelActionPerformed
        kosongkan();
    }//GEN-LAST:event_btdelActionPerformed

    public void kosongkan() {
        txtidsupplier.setText("");
        txtidk.setText("");
        txtidp.setText("");
        txtjumlah.setText("");
        txttotal.setText("");
        modelPesan.clear();
    }


    private void btsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsearchActionPerformed
        int cc;
        String idproduk;
        idproduk = txtcari.getText();
        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk where Id_Produk =?");
            pst.setString(1, idproduk);
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbdetailbeli.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Id_Produk"));
                    v2.add(rs.getString("Nama_Produk"));
                    v2.add(rs.getString("Stok"));
                    v2.add(rs.getString("harga_satuan"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btsearchActionPerformed

    private void LBEXIT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXIT2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_LBEXIT2MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        dataPenjualan.setVisible(true);
        framePenjualan.setVisible(false);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        dataPenjualan.setVisible(false);
        framePenjualan.setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    public void pay() {
        // pat btn action

        String totalText = total.getText();
        String payText = pay.getText();

        // Check if the total or pay fields are empty or null
        if (payText == null || payText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter valid values Pay", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int tot = Integer.valueOf(totalText);
            int paid = Integer.valueOf(payText);
            int balance = paid - tot;

            bal.setText(String.valueOf(balance));

            Bill(); // Call your Bill method

        } catch (NumberFormatException e) {
            // Handle invalid number format
            JOptionPane.showMessageDialog(this, "Please enter valid values Pay", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cal() {
        // Menghitung total nilai dalam tabel

        int numOfRow = tbbeli.getRowCount();
        int tot = 0;

        for (int i = 0; i < numOfRow; i++) {
            Object valueObj = tbbeli.getValueAt(i, 3);

            if (valueObj != null) {
                try {
                    int value = Integer.parseInt(valueObj.toString());
                    tot += value;
                } catch (NumberFormatException e) {
                    System.out.println("Kesalahan format angka di baris " + i);
                }
            } else {
                System.out.println("Nilai null ditemukan di baris " + i);
            }
        }

        total.setText(String.valueOf(tot));

        pay();
    }

    public void Bill() {
        // bill print

        try {

            b.setText("                         Dapp Code FKD \n");
            b.setText(b.getText() + "                         589/ King Road, \n");
            b.setText(b.getText() + "                         Colombo, Sri lanka, \n");
            b.setText(b.getText() + "                         +9411 123456789, \n");
            b.setText(b.getText() + "-------------------------------------------------------------------------\n");
            b.setText(b.getText() + "  Item \t\tQty \tPrice" + "\n");
            b.setText(b.getText() + "-------------------------------------------------------------------------\n");

            DefaultTableModel df = (DefaultTableModel) tbbeli.getModel();

            // get table Product details
            for (int i = 0; i < tbbeli.getRowCount(); i++) {

                String Name = df.getValueAt(i, 1).toString();
                String Qty = df.getValueAt(i, 2).toString();
                String Price = df.getValueAt(i, 3).toString();

                b.setText(b.getText() + "  " + Name + "\t\t" + Qty + "\t" + Price + "\n");
            }

            b.setText(b.getText() + "-------------------------------------------------------------------------\n");
            b.setText(b.getText() + "Sub Total : " + total.getText() + "\n");
            b.setText(b.getText() + "Cash      : " + pay.getText() + "\n");
            b.setText(b.getText() + "Balance   : " + bal.getText() + "\n");
            b.setText(b.getText() + "-------------------------------------------------------------------------\n");
            b.setText(b.getText() + "                     Thanks For Your Business...!" + "\n");
            b.setText(b.getText() + "-------------------------------------------------------------------------\n");
            b.setText(b.getText() + "                     Software by Youtube.com/c/Dappcode" + "\n");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void consumekafka() {

        ProdukBaru pb = new ProdukBaru();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.100.7:9092, 192.168.100.164:9093");
        props.setProperty("group.id", "register-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("dataproduk"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        if (pb == null) {
                            pb = new ProdukBaru(); // Inisialisasi tlog jika belum ada
                        }

                        pb.toObject(record.value());
                        System.out.println("Pesan diterima: " + record.value());
                        

                        txtidk.setText(pb.getIdproduk());
                        txtidsupplier.setText(pb.getIdsupplier());
                        txtidp.setText(pb.getNamaproduk());
                        txtjumlah.setText(pb.getStok());
                        txttotal.setText(pb.getHarga());
                        modelPesan.addElement(record.value());

                        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("INSERT INTO produk (Id_Produk, Id_Supplier, Nama_Produk, Stok, harga_satuan) VALUES (?, ?, ?, ?, ?)")) {
                            // Set parameter query
                            pst.setString(1, pb.getIdproduk());
                            pst.setString(2, pb.getIdsupplier());
                            pst.setString(3, pb.getNamaproduk());
                            pst.setString(4, pb.getStok());
                            pst.setString(5, pb.getHarga());
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
                            System.err.println("simpan gagal " + e.getMessage());
                        }

                        if ("kosongkan".equalsIgnoreCase(record.value())) {
                            modelPesan.clear();
                        }
                        
                        table_update();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menukasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menukasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menukasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menukasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menukasir().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBEXIT;
    private javax.swing.JLabel LBEXIT2;
    private javax.swing.JTextArea b;
    private javax.swing.JLabel bal;
    private javax.swing.JButton bck;
    private javax.swing.JLabel bgmenu;
    private javax.swing.JButton btdel;
    private javax.swing.JButton btlogout;
    private javax.swing.JButton btsearch;
    private javax.swing.JInternalFrame dataPenjualan;
    private javax.swing.JButton del;
    private javax.swing.JInternalFrame framePenjualan;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> listname;
    private javax.swing.JTextField pay;
    private javax.swing.JPanel pndp;
    private javax.swing.JPanel pndp1;
    private javax.swing.JPanel pndp2;
    private javax.swing.JPanel pndp3;
    private javax.swing.JTable tbbeli;
    private javax.swing.JTable tbdetailbeli;
    private javax.swing.JTable tbproduk;
    private javax.swing.JLabel total;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari2;
    private javax.swing.JTextField txtidk;
    private javax.swing.JTextField txtidp;
    private javax.swing.JTextField txtidsupplier;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
