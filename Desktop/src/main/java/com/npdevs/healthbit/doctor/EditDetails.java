package com.npdevs.healthbit.doctor;

import com.npdevs.healthbit.contracts.Processor;
import com.npdevs.healthbit.contracts.SignUp;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class EditDetails extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    String doctor_key;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private Web3j web3j;
    private String address_signup = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";

    /**
     * Create the frame.
     */
    public EditDetails(String doctor_key1) {
        doctor_key = doctor_key1;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 693, 537);
        contentPane = new JPanel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblName.setBounds(85, 70, 70, 24);
        contentPane.add(lblName);

        JLabel lblDoctorsName = new JLabel("Doctor's Name");
        lblDoctorsName.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblDoctorsName.setBounds(201, 67, 357, 31);
        contentPane.add(lblDoctorsName);

        JLabel lblHospital_1 = new JLabel("Hospital:");
        lblHospital_1.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblHospital_1.setBounds(85, 178, 99, 24);
        contentPane.add(lblHospital_1);

        textField = new JTextField();
        textField.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        textField.setBounds(211, 179, 369, 22);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblOpenTime = new JLabel("Open Time:");
        lblOpenTime.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblOpenTime.setBounds(85, 245, 110, 24);
        contentPane.add(lblOpenTime);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        textField_1.setColumns(10);
        textField_1.setBounds(211, 247, 369, 22);
        contentPane.add(textField_1);

        JLabel lblCloseTime = new JLabel("Close Time:");
        lblCloseTime.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        lblCloseTime.setBounds(85, 324, 121, 24);
        contentPane.add(lblCloseTime);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        textField_2.setColumns(10);
        textField_2.setBounds(211, 325, 369, 22);
        contentPane.add(textField_2);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnNewButton.setBounds(245, 418, 136, 25);
        contentPane.add(btnNewButton);

        /*******************************************************************************************/

        web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(doctor_key);
        SignUp signUp = SignUp.load(address_signup,web3j,credentials,GAS_PRICE,GAS_LIMIT);
        try {
            Tuple4<String, String, String, String> doctor_details =signUp.getDoctorDetails(credentials.getAddress()).send();
            lblDoctorsName.setText(doctor_details.getValue1());
            textField.setText(doctor_details.getValue2());
            textField_1.setText(doctor_details.getValue3());
            textField_2.setText(doctor_details.getValue4());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        /*******************************************************************************************/

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    signUp.editDoctorDetails(textField_1.getText(),textField_2.getText(),textField.getText()).send();
                    EditDetails.this.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

