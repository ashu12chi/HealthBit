package com.npdevs.healthbit.pharmacy;

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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 634, 526);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("");
        label.setBounds(5, 5, 0, 469);
        contentPane.add(label);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblPassword.setBounds(64, 204, 92, 29);
        contentPane.add(lblPassword);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(193, 208, 347, 22);
        contentPane.add(textField_1);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnLogin.setBounds(246, 358, 116, 25);
        contentPane.add(btnLogin);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnSignUp.setBounds(246, 418, 116, 25);
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
                Front front = new Front(textField_1.getText());
                front.setVisible(true);
            }
        });
    }
}

