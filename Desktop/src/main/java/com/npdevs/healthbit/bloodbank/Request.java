package com.npdevs.healthbit.bloodbank;

import com.npdevs.healthbit.contracts.Processor;
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

public class Request extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    String doctor_key;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private String address_signup = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";

    /**
     * Create the frame.
     */
    public Request(String doctor_key1) {
        doctor_key = doctor_key1;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 634, 526);
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
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("");
        label.setBounds(5, 5, 0, 469);
        contentPane.add(label);

        JLabel lblAddress = new JLabel("BloodGroup");
        lblAddress.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblAddress.setBounds(42, 135, 139, 29);
        contentPane.add(lblAddress);

        textField = new JTextField();
        textField.setBounds(193, 139, 347, 22);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnSignUp = new JButton("Send");
        btnSignUp.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnSignUp.setBounds(246, 418, 116, 25);
        contentPane.add(btnSignUp);

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
                Credentials credentials = Credentials.create(doctor_key);
                Processor processor = Processor.load(address_processor,web3j,credentials,GAS_PRICE,GAS_LIMIT);
                try {
                    TransactionReceipt a = processor.requestBlood(textField.getText()).send();
                    System.out.println(a.isStatusOK());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
