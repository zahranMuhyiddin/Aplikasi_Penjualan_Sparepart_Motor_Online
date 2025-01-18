/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectakhir;

import base.Flogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import myConnection.DBO;
import myEntity.ProdukBaru;
import myEntity.Riwayat;
import myEntity.TbLogin;
import myEntity.Transaksi;
import myFunction.SysLogin;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author LENOVO
 */
public class menuAdminHome extends javax.swing.JFrame {

    static DBO db = new DBO();
    Connection cnn = db.getConnection();
    Properties props = new Properties();
    int cc;
    private JFrame frame;

    private DefaultListModel<String> modelPesan, modelPesan1;

    /**
     * Creates new form Register
     */
    public menuAdminHome() {
        initComponents();
        table_updatep();
        table_updatedp();
        table_updateakun();
        daftarproduk.setVisible(true);
        Laporanbeli.setVisible(false);
        detailpm.setVisible(false);
        account.setVisible(false);

        new Thread(this::consumekafkaLP).start();
        new Thread(this::consumekafkaLPM).start();

    }

    private void table_updatedp() {
        int cc;

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM detail_pembelian");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbdetailbeli.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("id_detail_beli"));
                    v2.add(rs.getString("id_kasir"));
                    v2.add(rs.getString("id_produk"));
                    v2.add(rs.getString("jumlah"));
                    v2.add(rs.getString("subtotal"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM detail_supply");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tabelriwayat.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Id"));
                    v2.add(rs.getString("Id_Produk"));
                    v2.add(rs.getString("Id_Supplier"));
                    v2.add(rs.getString("Nama_Produk"));
                    v2.add(rs.getString("Stok"));
                    v2.add(rs.getString("Tanggal"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void table_updateakun() {
        int cc;

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM tblogin");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tabelakun.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("Name"));
                    v2.add(rs.getString("Username"));
                    v2.add(rs.getString("Password"));
                    v2.add(rs.getString("status"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void table_updatep() {

        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tabelproduk.getModel();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Laporanbeli = new javax.swing.JInternalFrame();
        pndp3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdetailbeli = new javax.swing.JTable();
        pndp1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pndp2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        modelPesan = new DefaultListModel<>();
        listname = new javax.swing.JList<>(modelPesan);
        txtidk = new javax.swing.JTextField();
        txtidp = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        btdel = new javax.swing.JButton();
        pndp = new javax.swing.JPanel();
        btsearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        LBEXIT2 = new javax.swing.JLabel();
        detailpm = new javax.swing.JInternalFrame();
        pndp4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelriwayat = new javax.swing.JTable();
        pndp5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        pndp6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        modelPesan1 = new DefaultListModel<>();
        listname1 = new javax.swing.JList<>(modelPesan1);
        txtidk1 = new javax.swing.JTextField();
        txtidp1 = new javax.swing.JTextField();
        txttotal1 = new javax.swing.JTextField();
        txtjumlah1 = new javax.swing.JTextField();
        btdel1 = new javax.swing.JButton();
        pndp7 = new javax.swing.JPanel();
        btsearch1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtcari1 = new javax.swing.JTextField();
        LBEXIT4 = new javax.swing.JLabel();
        daftarproduk = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        txtidproduk = new javax.swing.JTextField();
        txtsupplier = new javax.swing.JTextField();
        txtstok = new javax.swing.JTextField();
        txtnamaproduk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bttambah = new javax.swing.JButton();
        btubah = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();
        btbersih = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txthrg = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        LBMIN = new javax.swing.JLabel();
        LBEXIT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bttambah1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelproduk = new javax.swing.JTable();
        account = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        txtnamaakun = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txtpassakun = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        bttambahakun = new javax.swing.JButton();
        btubahakun = new javax.swing.JButton();
        bthapusakun = new javax.swing.JButton();
        btbersihakun = new javax.swing.JButton();
        pilihstatus = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        LBMIN3 = new javax.swing.JLabel();
        LBEXIT3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelakun = new javax.swing.JTable();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        jLabel2.setText("ADMIN");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 70, 30));

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

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Akun");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 250, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Daftar Produk");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 200, 250, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Laporan Produk Masuk");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Laporan Pembelian");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 570));

        Laporanbeli.setVisible(true);
        Laporanbeli.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pndp3.setBackground(new java.awt.Color(85, 85, 85));
        pndp3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DETAIL PEMBELIAN");
        pndp3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 190, 40));

        tbdetailbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_detail_beli", "id_kasir", "id_produk", "jumlah", "subtotal"
            }
        ));
        jScrollPane1.setViewportView(tbdetailbeli);

        pndp3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 330));

        Laporanbeli.getContentPane().add(pndp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 640, 370));

        pndp1.setBackground(new java.awt.Color(85, 85, 85));
        pndp1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Notifikasi Kafka");
        pndp1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 130, 30));

        Laporanbeli.getContentPane().add(pndp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 650, 30));

        pndp2.setBackground(new java.awt.Color(153, 153, 153));
        pndp2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listname.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listnameValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listname);

        pndp2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 80));
        pndp2.add(txtidk, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));
        pndp2.add(txtidp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 110, -1));
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
        pndp2.add(btdel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 60, 80));

        Laporanbeli.getContentPane().add(pndp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 650, 100));

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
        pndp.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 500, 30));

        LBEXIT2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBEXIT2.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT2.setText("X");
        LBEXIT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXIT2MouseClicked(evt);
            }
        });
        pndp.add(LBEXIT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 20));

        Laporanbeli.getContentPane().add(pndp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 80));

        getContentPane().add(Laporanbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 650, -1));

        detailpm.setVisible(true);
        detailpm.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pndp4.setBackground(new java.awt.Color(85, 85, 85));
        pndp4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("DETAIL PEMBELIAN");
        pndp4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 190, 40));

        tabelriwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Id Produk", "Id Supplier", "Nama Produk", "Stok", "Tanggal"
            }
        ));
        jScrollPane4.setViewportView(tabelriwayat);

        pndp4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 320));

        detailpm.getContentPane().add(pndp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 640, 370));

        pndp5.setBackground(new java.awt.Color(85, 85, 85));
        pndp5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Notifikasi Kafka");
        pndp5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 130, 30));

        detailpm.getContentPane().add(pndp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 650, 30));

        pndp6.setBackground(new java.awt.Color(153, 153, 153));
        pndp6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listname1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listname1ValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(listname1);

        pndp6.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 80));
        pndp6.add(txtidk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 110, -1));
        pndp6.add(txtidp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 110, -1));
        pndp6.add(txttotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 180, -1));
        pndp6.add(txtjumlah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 180, -1));

        btdel1.setText("DEL");
        btdel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btdel1MouseClicked(evt);
            }
        });
        btdel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdel1ActionPerformed(evt);
            }
        });
        pndp6.add(btdel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 60, 80));

        detailpm.getContentPane().add(pndp6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 650, 100));

        pndp7.setBackground(new java.awt.Color(153, 153, 153));
        pndp7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pndp7.setForeground(new java.awt.Color(255, 255, 255));
        pndp7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btsearch1.setText("Search");
        btsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsearch1ActionPerformed(evt);
            }
        });
        pndp7.add(btsearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 80, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        pndp7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));
        pndp7.add(txtcari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 500, 30));

        LBEXIT4.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBEXIT4.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT4.setText("X");
        LBEXIT4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXIT4MouseClicked(evt);
            }
        });
        pndp7.add(LBEXIT4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 20));

        detailpm.getContentPane().add(pndp7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 80));

        getContentPane().add(detailpm, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 650, 630));

        daftarproduk.setVisible(true);
        daftarproduk.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtidproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 200, -1));
        jPanel1.add(txtsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 200, -1));

        txtstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokActionPerformed(evt);
            }
        });
        jPanel1.add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 200, -1));
        jPanel1.add(txtnamaproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 200, -1));

        jLabel1.setText("ID Produk");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel6.setText("ID Supplier");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel7.setText("Nama Produk");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel8.setText("Stok Produk");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        bttambah.setText("Tambah");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });
        jPanel1.add(bttambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 100, 30));

        btubah.setText("Ubah");
        btubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btubahActionPerformed(evt);
            }
        });
        jPanel1.add(btubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 90, 30));

        bthapus.setText("Hapus");
        bthapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthapusMouseClicked(evt);
            }
        });
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        jPanel1.add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 100, 30));

        btbersih.setText("Bersihkan");
        btbersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbersihActionPerformed(evt);
            }
        });
        jPanel1.add(btbersih, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 90, 30));

        jLabel27.setText("Harga_satuan");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        txthrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthrgActionPerformed(evt);
            }
        });
        jPanel1.add(txthrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 200, -1));

        daftarproduk.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 650, 300));

        jPanel3.setBackground(new java.awt.Color(85, 85, 85));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBMIN.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBMIN.setForeground(new java.awt.Color(255, 255, 255));
        LBMIN.setText("_");
        jPanel3.add(LBMIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, -10, 10, 40));

        LBEXIT.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBEXIT.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT.setText("X");
        LBEXIT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXITMouseClicked(evt);
            }
        });
        jPanel3.add(LBEXIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TAMBAH PRODUK");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        daftarproduk.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 40));

        jPanel4.setBackground(new java.awt.Color(85, 85, 85));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DAFTAR PRODUK");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        bttambah1.setText("refresh");
        bttambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambah1ActionPerformed(evt);
            }
        });
        jPanel4.add(bttambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 100, -1));

        daftarproduk.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 650, 50));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelproduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id_Produk", "Id_Supplier", "Nama_Produk", "Stok", "harga_satuan"
            }
        ));
        tabelproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelprodukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelproduk);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 630, 160));

        daftarproduk.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 650, 170));

        getContentPane().add(daftarproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 670, 600));

        account.setVisible(true);
        account.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(txtnamaakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 200, -1));
        jPanel6.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 200, -1));

        txtpassakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassakunActionPerformed(evt);
            }
        });
        jPanel6.add(txtpassakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 200, -1));

        jLabel21.setText("Nama");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel22.setText("Username");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        jLabel23.setText("Status");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 30));

        jLabel24.setText("Password");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 30));

        bttambahakun.setText("Tambah");
        bttambahakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahakunActionPerformed(evt);
            }
        });
        jPanel6.add(bttambahakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 100, 30));

        btubahakun.setText("Ubah");
        btubahakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btubahakunActionPerformed(evt);
            }
        });
        jPanel6.add(btubahakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 90, 30));

        bthapusakun.setText("Hapus");
        bthapusakun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthapusakunMouseClicked(evt);
            }
        });
        bthapusakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusakunActionPerformed(evt);
            }
        });
        jPanel6.add(bthapusakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 100, 30));

        btbersihakun.setText("Bersihkan");
        btbersihakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbersihakunActionPerformed(evt);
            }
        });
        jPanel6.add(btbersihakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 90, 30));

        pilihstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kasir", "Gudang", "Admin", " " }));
        jPanel6.add(pilihstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 200, -1));

        account.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 650, 300));

        jPanel7.setBackground(new java.awt.Color(85, 85, 85));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBMIN3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBMIN3.setForeground(new java.awt.Color(255, 255, 255));
        LBMIN3.setText("_");
        jPanel7.add(LBMIN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, -10, 10, 40));

        LBEXIT3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        LBEXIT3.setForeground(new java.awt.Color(255, 255, 255));
        LBEXIT3.setText("X");
        LBEXIT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBEXIT3MouseClicked(evt);
            }
        });
        jPanel7.add(LBEXIT3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("AKUN");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        account.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 40));

        jPanel8.setBackground(new java.awt.Color(85, 85, 85));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("DAFTAR AKUN");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        account.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 650, 50));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelakun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama", "Username", "Password", "Status"
            }
        ));
        tabelakun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelakunMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelakun);

        jPanel9.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 160));

        account.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 650, 170));

        getContentPane().add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 650, 600));

        setSize(new java.awt.Dimension(900, 570));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bgmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgmenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bgmenuMouseClicked

    private void btlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlogoutActionPerformed
        Flogin fl = new Flogin();
        fl.setVisible(true);
        fl.setLocationRelativeTo(null);
        fl.setDefaultCloseOperation(menuAdminHome.EXIT_ON_CLOSE);
        setVisible(false);
    }//GEN-LAST:event_btlogoutActionPerformed

    private void txtstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokActionPerformed

    private void btubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btubahActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelproduk.getModel();
        int selectedIndex = tabelproduk.getSelectedRow();

        String id = String.valueOf(model.getValueAt(selectedIndex, 0));

        String idproduk, idsupplier, namaproduk, stok, harga;

        idproduk = txtidproduk.getText();
        idsupplier = txtsupplier.getText();
        namaproduk = txtnamaproduk.getText();
        stok = txtstok.getText();
        harga = txthrg.getText();

        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("UPDATE produk SET Id_Produk=?,Id_Supplier=?,Nama_Produk=?,Stok=?, harga_satuan=? WHERE Id_Produk=?")) {
            // Set parameter query
            pst.setString(1, idproduk);
            pst.setString(2, idsupplier);
            pst.setString(3, namaproduk);
            pst.setString(4, stok);
            pst.setString(5, harga);
            pst.setString(6, id);

            ProdukBaru pb = new ProdukBaru();
            pb.setIdproduk(idproduk);
            pb.setNamaproduk(namaproduk);
            pb.setStok(stok);
            pb.setHarga(harga);
            try (Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props)) {
                producer.send(new ProducerRecord<>("dataproduk", "", pb.toString())
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal mengirim data: " + e.getMessage());
            }

            // Eksekusi query
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
            table_updatep();
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
            System.err.println("simpan gagal " + e.getMessage());
        }

    }//GEN-LAST:event_btubahActionPerformed

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        String idproduk, idsupplier, namaproduk, stok, harga;

        idproduk = txtidproduk.getText();
        idsupplier = txtsupplier.getText();
        namaproduk = txtnamaproduk.getText();
        stok = txtstok.getText();
        harga = txthrg.getText();

        ProdukBaru pb = new ProdukBaru();
        pb.setIdproduk(idproduk);
        pb.setIdsupplier(idsupplier);
        pb.setNamaproduk(namaproduk);
        pb.setStok(stok);
        pb.setHarga(harga);
        try (Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<>("dataproduk", "", pb.toString())
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal mengirim data: " + e.getMessage());
        }
        table_updatep();
        kosongkan();


    }//GEN-LAST:event_bttambahActionPerformed

    private void LBEXITMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXITMouseClicked
        System.exit(0);
    }//GEN-LAST:event_LBEXITMouseClicked

    private void tabelprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelprodukMouseClicked

        DefaultTableModel model = (DefaultTableModel) tabelproduk.getModel();
        int selectedIndex = tabelproduk.getSelectedRow();

        txtidproduk.setText(model.getValueAt(selectedIndex, 0).toString());
        txtsupplier.setText(model.getValueAt(selectedIndex, 1).toString());
        txtnamaproduk.setText(model.getValueAt(selectedIndex, 2).toString());
        txtstok.setText(model.getValueAt(selectedIndex, 3).toString());
        txthrg.setText(model.getValueAt(selectedIndex, 4).toString());

    }//GEN-LAST:event_tabelprodukMouseClicked

    private void bthapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthapusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bthapusMouseClicked

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed

        DefaultTableModel model = (DefaultTableModel) tabelproduk.getModel();
        int selectedIndex = tabelproduk.getSelectedRow();

        String id = String.valueOf(model.getValueAt(selectedIndex, 0));

        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("DELETE FROM produk WHERE Id_Produk=?")) {
            // Set parameter query
            pst.setString(1, id);

            // Eksekusi query
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
            table_updatep();
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
            System.err.println("simpan gagal " + e.getMessage());
        }

    }//GEN-LAST:event_bthapusActionPerformed

    private void btbersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbersihActionPerformed
        kosongkan();
    }//GEN-LAST:event_btbersihActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        daftarproduk.setVisible(false);
        detailpm.setVisible(false);
        Laporanbeli.setVisible(true);
        account.setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void btdelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btdelMouseClicked

    private void btdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdelActionPerformed
        
        txtidk.setText("");
        txtidp.setText("");
        txtjumlah.setText("");
        txttotal.setText("");
        modelPesan.clear();
    }//GEN-LAST:event_btdelActionPerformed

    private void txtpassakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassakunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassakunActionPerformed

    private void bttambahakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahakunActionPerformed

        String nama = txtnamaakun.getText().trim();
        String username = txtusername.getText().trim();
        String password = txtpassakun.getText().trim();
        String status = String.valueOf(pilihstatus.getSelectedItem());

        // Validasi input
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Semua field harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Simpan ke database
        try {
            TbLogin tlog = new TbLogin(nama, username, password, status); // Pastikan TbLogin memiliki constructor ini
            SysLogin sp = new SysLogin();
            sp.Regis(tlog);
            JOptionPane.showMessageDialog(frame,
                    "Data berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);

            // Kosongkan field setelah data tersimpan
            txtnamaakun.setText("");
            txtusername.setText("");
            txtpassakun.setText("");
            table_updateakun();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Terjadi kesalahan: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_bttambahakunActionPerformed

    private void btubahakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btubahakunActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelakun.getModel();
        int selectedIndex = tabelakun.getSelectedRow();

        String id = String.valueOf(model.getValueAt(selectedIndex, 1));

        String nama, username, password, status;

        nama = txtnamaakun.getText();
        username = txtusername.getText();
        password = txtpassakun.getText();
        status = String.valueOf(pilihstatus.getSelectedItem());

        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("UPDATE tblogin SET Name=?, Username=?, Password=?, status=? WHERE Username=?")) {
            // Set parameter query
            pst.setString(1, nama);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, status);
            pst.setString(5, id);

            // Eksekusi query
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
            table_updateakun();
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
            System.err.println("simpan gagal " + e.getMessage());
        }
    }//GEN-LAST:event_btubahakunActionPerformed

    private void bthapusakunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthapusakunMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bthapusakunMouseClicked

    private void bthapusakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusakunActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelakun.getModel();
        int selectedIndex = tabelakun.getSelectedRow();

        String id = String.valueOf(model.getValueAt(selectedIndex, 1));

        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("DELETE FROM tblogin WHERE Name=?")) {
            // Set parameter query
            pst.setString(1, id);

            // Eksekusi query
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
            txtnamaakun.setText("");
            txtusername.setText("");
            txtpassakun.setText("");
            table_updateakun();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
            System.err.println("simpan gagal " + e.getMessage());
        }
    }//GEN-LAST:event_bthapusakunActionPerformed

    private void btbersihakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbersihakunActionPerformed
        txtnamaakun.setText("");
        txtusername.setText("");
        txtpassakun.setText("");
    }//GEN-LAST:event_btbersihakunActionPerformed

    private void LBEXIT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXIT3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LBEXIT3MouseClicked

    private void tabelakunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelakunMouseClicked
        DefaultTableModel model = (DefaultTableModel) tabelakun.getModel();
        int selectedIndex = tabelakun.getSelectedRow();

        txtnamaakun.setText(model.getValueAt(selectedIndex, 0).toString());
        txtusername.setText(model.getValueAt(selectedIndex, 1).toString());
        txtpassakun.setText(model.getValueAt(selectedIndex, 2).toString());
    }//GEN-LAST:event_tabelakunMouseClicked

    private void txthrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthrgActionPerformed

    private void btsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsearchActionPerformed
        int cc;
        String idproduk;
        idproduk = txtcari.getText();
        try {
            PreparedStatement pst = cnn.prepareStatement("SELECT * FROM produk where id_detail_beli =?");
            pst.setString(1, idproduk);
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData RSMD = rs.getMetaData();
            cc = RSMD.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbdetailbeli.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= cc; ii++) {
                    v2.add(rs.getString("id_detail_beli"));
                    v2.add(rs.getString("id_kasir"));
                    v2.add(rs.getString("id_produk"));
                    v2.add(rs.getString("jumlah"));
                    v2.add(rs.getString("subtotal"));
                }
                model.addRow(v2);

            }

        } catch (SQLException ex) {
            Logger.getLogger(menuAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btsearchActionPerformed

    private void listnameValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listnameValueChanged

    }//GEN-LAST:event_listnameValueChanged

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        daftarproduk.setVisible(true);
        Laporanbeli.setVisible(false);
        detailpm.setVisible(false);
        account.setVisible(false);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        daftarproduk.setVisible(false);
        Laporanbeli.setVisible(false);
        detailpm.setVisible(true);
        account.setVisible(false);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        daftarproduk.setVisible(false);
        Laporanbeli.setVisible(false);
        detailpm.setVisible(false);
        account.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        props.put("bootstrap.servers", "192.168.100.7:9092, 192.168.100.164:9093");
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }//GEN-LAST:event_formWindowOpened

    private void LBEXIT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXIT2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_LBEXIT2MouseClicked

    private void listname1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listname1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listname1ValueChanged

    private void btdel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btdel1MouseClicked

    private void btdel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdel1ActionPerformed
        txtidk1.setText("");
        txtidp1.setText("");
        txtjumlah1.setText("");
        txttotal1.setText("");
        modelPesan1.clear();
    }//GEN-LAST:event_btdel1ActionPerformed

    private void btsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btsearch1ActionPerformed

    private void LBEXIT4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBEXIT4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LBEXIT4MouseClicked

    private void bttambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambah1ActionPerformed
        table_updatep();
    }//GEN-LAST:event_bttambah1ActionPerformed

    public void kosongkan() {
        txtidproduk.setText("");
        txtsupplier.setText("");
        txtnamaproduk.setText("");
        txtstok.setText("");
        txthrg.setText("");
        modelPesan.clear();
    }

    public void consumekafkaLP() {

        Transaksi tr = new Transaksi();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.100.7:9092, 192.168.100.164:9093");
        props.setProperty("group.id", "detailbeli-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("detailpembelian"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        if (tr == null) {
                            tr = new Transaksi(); // Inisialisasi tlog jika belum ada
                        }

                        tr.toObject(record.value());
                        System.out.println("Pesan diterima: " + record.value());

                        txtidk.setText(tr.getId_kasir());
                        txtidp.setText(tr.getId_produk());
                        txtjumlah.setText(tr.getJumlah());
                        txttotal.setText(tr.getTotal());
                        modelPesan.addElement(record.value());
                        simpanTransaksi(tr.getId_kasir(), tr.getId_produk(), Integer.parseInt(tr.getJumlah()), Integer.parseInt(tr.getTotal()));
                        table_updatedp();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simpanTransaksi(String idkasir, String id_produk, int jumlah, int total) {

        try {
            // Buat koneksi ke database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sparepart", "root", "");

            // Buat pernyataan SQL untuk menyimpan data
            String sql = "INSERT INTO detail_pembelian (id_kasir, id_produk, jumlah, subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idkasir);
            statement.setString(2, id_produk);
            statement.setInt(3, jumlah);
            statement.setInt(4, total);

            // Eksekusi pernyataan SQL
            statement.executeUpdate();

            // Tutup koneksi
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consumekafkaLPM() {

        Riwayat tr = new Riwayat();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.100.7:9092, 192.168.100.164:9093");
        props.setProperty("group.id", "detailbeli-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("detaillpm"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        if (tr == null) {
                            tr = new Riwayat(); // Inisialisasi tlog jika belum ada
                        }

                        tr.toObject(record.value());
                        System.out.println("Pesan diterima: " + record.value());

                        txtidk1.setText(tr.getIdsupplier());
                        txtidp1.setText(tr.getIdproduk());
                        txtjumlah1.setText(tr.getNamaproduk());
                        txttotal1.setText(tr.getStok());
                        modelPesan1.addElement(record.value());

                        String idproduk, idsupplier, namaproduk, stok;

                        idproduk = tr.getIdsupplier();
                        idsupplier = tr.getIdproduk();
                        namaproduk = tr.getNamaproduk();
                        stok = tr.getStok();

                        try (Connection cnn = db.getConnection(); PreparedStatement pst = cnn.prepareStatement("INSERT INTO detail_supply (Id_Produk, Id_Supplier, Nama_Produk, Stok) VALUES (?, ?, ?, ?)")) {
                            // Set parameter query
                            pst.setString(1, idproduk);
                            pst.setString(2, idsupplier);
                            pst.setString(3, namaproduk);
                            pst.setString(4, stok);
// Eksekusi query
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
                            table_updatep();
                            kosongkan();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Gagal Menyimpan");
                            System.err.println("simpan gagal " + e.getMessage());
                        }

                        table_updatedp();

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
            java.util.logging.Logger.getLogger(menuAdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuAdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuAdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuAdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new menuAdminHome().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBEXIT;
    private javax.swing.JLabel LBEXIT2;
    private javax.swing.JLabel LBEXIT3;
    private javax.swing.JLabel LBEXIT4;
    private javax.swing.JLabel LBMIN;
    private javax.swing.JLabel LBMIN3;
    private javax.swing.JInternalFrame Laporanbeli;
    private javax.swing.JInternalFrame account;
    private javax.swing.JLabel bgmenu;
    private javax.swing.JButton btbersih;
    private javax.swing.JButton btbersihakun;
    private javax.swing.JButton btdel;
    private javax.swing.JButton btdel1;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton bthapusakun;
    private javax.swing.JButton btlogout;
    private javax.swing.JButton btsearch;
    private javax.swing.JButton btsearch1;
    private javax.swing.JButton bttambah;
    private javax.swing.JButton bttambah1;
    private javax.swing.JButton bttambahakun;
    private javax.swing.JButton btubah;
    private javax.swing.JButton btubahakun;
    private javax.swing.JInternalFrame daftarproduk;
    private javax.swing.JInternalFrame detailpm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> listname;
    private javax.swing.JList<String> listname1;
    private javax.swing.JComboBox<String> pilihstatus;
    private javax.swing.JPanel pndp;
    private javax.swing.JPanel pndp1;
    private javax.swing.JPanel pndp2;
    private javax.swing.JPanel pndp3;
    private javax.swing.JPanel pndp4;
    private javax.swing.JPanel pndp5;
    private javax.swing.JPanel pndp6;
    private javax.swing.JPanel pndp7;
    private javax.swing.JTable tabelakun;
    private javax.swing.JTable tabelproduk;
    private javax.swing.JTable tabelriwayat;
    private javax.swing.JTable tbdetailbeli;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JTextField txthrg;
    private javax.swing.JTextField txtidk;
    private javax.swing.JTextField txtidk1;
    private javax.swing.JTextField txtidp;
    private javax.swing.JTextField txtidp1;
    private javax.swing.JTextField txtidproduk;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtjumlah1;
    private javax.swing.JTextField txtnamaakun;
    private javax.swing.JTextField txtnamaproduk;
    private javax.swing.JTextField txtpassakun;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txtsupplier;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotal1;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
