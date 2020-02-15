package com.npdevs.healthbit.bloodbank;

import com.npdevs.healthbit.contracts.SignUp;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_5;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private Web3j web3j;
    private String address_signup = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";

    /**
     * Create the frame.
     */
    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 663, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblNewLabel.setBounds(51, 100, 61, 24);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(184, 102, 363, 22);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(184, 45, 363, 22);
        contentPane.add(textField_5);

        JLabel lblPrivateKey = new JLabel("Private Key");
        lblPrivateKey.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblPrivateKey.setBounds(51, 45, 121, 24);
        contentPane.add(lblPrivateKey);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnSubmit.setBounds(230, 421, 115, 25);
        contentPane.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
                Credentials credentials = Credentials.create(textField_5.getText());
                System.out.println(credentials.getAddress());
                SignUp signUp = SignUp.load(address_signup,web3j,credentials,GAS_PRICE,GAS_LIMIT);
                try {
                    signUp.signupBloodBank(textField.getText()).send();
                    JOptionPane.showMessageDialog(null,"Sucess!!!");
                    textField.setText("");
                    textField_5.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }

}
