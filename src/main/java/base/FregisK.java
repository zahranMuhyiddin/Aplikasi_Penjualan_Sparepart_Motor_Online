package base;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.*;
import myEntity.TbLogin;
import myFunction.SysLogin;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class FregisK implements Runnable {

    private JFrame frame;
    private JButton btnKirim;
    private DefaultListModel<String> modelPesan;
    private JList<String> listPesan;
    private TbLogin tlog;
    private JTextField txtNama;
    private JTextField txtUsername;
    private JTextField txtPass;
    private JTextField txtStatus;

    public FregisK() {
        initializeUI();
        new Thread(this).start();
    }

    private void initializeUI() {
        frame = new JFrame("Kafka Konsumer");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(85, 85, 85));

        JLabel lbExit = new JLabel("X");
        lbExit.setBounds(270, 10, 20, 20);
        lbExit.setForeground(java.awt.Color.WHITE);
        lbExit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lbExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.exit(0);
            }
        });

        JLabel lbMini = new JLabel("_");
        lbMini.setBounds(240, 10, 20, 20);
        lbMini.setForeground(java.awt.Color.WHITE);
        lbMini.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lbMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                frame.setState(JFrame.ICONIFIED);
            }
        });

        JLabel lbName = new JLabel("Nama:");
        lbName.setBounds(20, 220, 100, 25);
        lbName.setForeground(java.awt.Color.WHITE);

        JLabel lbUsername = new JLabel("Username:");
        lbUsername.setBounds(20, 260, 100, 25);
        lbUsername.setForeground(java.awt.Color.WHITE);

        JLabel lbPassword = new JLabel("Password:");
        lbPassword.setBounds(20, 300, 100, 25);
        lbPassword.setForeground(java.awt.Color.WHITE);
        
        JLabel lbStatus = new JLabel("Status:");
        lbStatus.setBounds(20, 340, 100, 25);
        lbStatus.setForeground(java.awt.Color.WHITE);

        txtNama = new JTextField();
        txtNama.setBounds(120, 220, 150, 25);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 260, 150, 25);

        txtPass = new JTextField();
        txtPass.setBounds(120, 300, 150, 25);
        
        txtStatus = new JTextField();
        txtStatus.setBounds(120, 340, 150, 25);

        btnKirim = new JButton("KIRIM");
        btnKirim.setBounds(90, 380, 100, 30);
        btnKirim.setBackground(new java.awt.Color(85, 85, 85));
        btnKirim.setForeground(java.awt.Color.WHITE);
        btnKirim.addActionListener(this::onKirimClicked);

        modelPesan = new DefaultListModel<>();
        listPesan = new JList<>(modelPesan);
        JScrollPane scrollPane = new JScrollPane(listPesan);
        scrollPane.setBounds(20, 40, 250, 150);

        panel.add(lbExit);
        panel.add(lbMini);
        panel.add(lbName);
        panel.add(txtNama);
        panel.add(lbUsername);
        panel.add(txtUsername);
        panel.add(lbPassword);
        panel.add(txtPass);
        panel.add(lbStatus);
        panel.add(txtStatus);
        panel.add(btnKirim);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setSize(300, 420);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void onKirimClicked(ActionEvent evt) {
        String nama = txtNama.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPass.getText().trim();
        String status = txtStatus.getText().trim();

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
            tlog = new TbLogin(nama, username, password, status); // Pastikan TbLogin memiliki constructor ini
            saveToDatabase();
            JOptionPane.showMessageDialog(frame,
                    "Data berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            modelPesan.clear();

            // Kosongkan field setelah data tersimpan
            txtNama.setText("");
            txtUsername.setText("");
            txtPass.setText("");
            txtStatus.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Terjadi kesalahan: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "konsumer-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try ( KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("register"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        if (tlog == null) {
                            tlog = new TbLogin(); // Inisialisasi tlog jika belum ada
                        }

                        tlog.toObject(record.value());
                        txtNama.setText(tlog.getName());
                        txtUsername.setText(tlog.getUsername());
                        txtPass.setText(tlog.getPass());
                        txtStatus.setText(tlog.getStatus());
                        modelPesan.addElement(record.value());

                        if ("kosongkan".equalsIgnoreCase(record.value())) {
                            modelPesan.clear();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToDatabase() {
        SysLogin sp = new SysLogin();
        sp.Regis(tlog);
    }

    public static void main(String[] args) {
        new FregisK();
    }
}
