/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.time.Duration;
import java.util.*;
import myEntity.TbLogin;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaKonsumer implements Runnable {

    JFrame f;
    Thread t = null;
    JButton b;
    DefaultListModel modelPesan = new DefaultListModel();
    JList listPesan = new JList(modelPesan);
    TbLogin tlog = new TbLogin();
    JTextField txtName = new JTextField();
    JTextField txtUsername = new JTextField();
    JTextField txtPass = new JTextField();

    KafkaKonsumer() {
        f = new JFrame();
        t = new Thread(this);
        t.start();
        b = new JButton();
        b.setText("Tutup");
        b.setBounds(180, 450, 100, 50);
        b.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.exit(0);
        });
        f.add(b);
        listPesan.setBounds(10, 10, 660, 200);
        listPesan.setVisible(true);
        f.add(listPesan);
        txtName.setBounds(10, 270, 460, 20);
        txtName.setVisible(true);
        f.add(txtName);
        txtUsername.setBounds(10, 290, 460, 20);
        txtUsername.setVisible(true);
        f.add(txtUsername);
        txtPass.setBounds(10, 310, 460, 20);
        txtPass.setVisible(true);
        f.add(txtPass);
        f.setSize(700, 550);
        f.setTitle("Contoh Kafka Konsumer");
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        try {
            Properties props = new Properties();
            props.setProperty("bootstrap.servers", "localhost:9092");
            props.setProperty("group.id", "mhsMySql");
            props.setProperty("enable.auto.commit", "true");
            props.setProperty("auto.commit.interval.ms", "1000");
            props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList("register"));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        tlog.toObject(record.value());
                        txtName.setText(tlog.getName());
                        txtUsername.setText(tlog.getUsername());
                        txtPass.setText(tlog.getPass());
                        System.out.printf("offset = %d, key = %s, value = %s%n, Partition = %d, Topik = %s ", record.offset(),
                                record.key(), record.value(), record.partition(), record.topic());
                        modelPesan.add(modelPesan.getSize(), record.value());
                        simpanKeDatabase();
                        if (record.value().equalsIgnoreCase("kosongkan")) {
                            modelPesan.clear();
                        }
                    } catch (Exception e) {
                        System.out.println("Error : " + e.getMessage());
                    }
                }
                t.sleep(100); // interval given in milliseconds
            }
        } catch (Exception e) {
        }
    }

    public void simpanKeDatabase() {
//Buat Koneksi kemysql
        String urlvalue = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            urlvalue = "jdbc:mysql://localhost/sparepart?user=root&password=";
            conn = DriverManager.getConnection(urlvalue);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
        }
//Buat perintah Insert SQL
        String sql = " insert into login (name, username, password)"
                + " values (?, ?, ?)";
//isi field dengan data
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, tlog.getName());
            preparedStmt.setString(2, tlog.getUsername());
            preparedStmt.setString(3, tlog.getPass());
        } catch (SQLException ex) {
            System.out.println("Statement eror : " + ex.getMessage());
        }
        //Eksekusi PreparedStatement.
        try {
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.out.println("Eksekusi perintah SQL Gagal : " + ex.getMessage());
        }
        try {
//Tutup Koneksi
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Tutup Koneksi Gagal : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new KafkaKonsumer();
    }
}
