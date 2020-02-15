package com.npdevs.healthbit.bloodbank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setTitle("HealthBit");
                frame.setResizable(false);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 634, 526);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("Blood Bank");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        label.setBounds(210, 50, 200, 50);
        contentPane.add(label);

        JLabel lblPassword = new JLabel("Private Key:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPassword.setBounds(64, 208, 92, 35);
        contentPane.add(lblPassword);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_1.setBounds(193, 208, 347, 35);
        contentPane.add(textField_1);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnLogin.setBounds(246, 358, 116, 35);
        contentPane.add(btnLogin);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnSignUp.setBounds(246, 418, 116, 35);
        contentPane.add(btnSignUp);

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                register.setVisible(true);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctor_key = textField_1.getText();
                Front front = new Front(textField_1.getText());
                front.setTitle("Health Bit");
                front.setResizable(false);
                front.setVisible(true);
            }
        });
    }
}


