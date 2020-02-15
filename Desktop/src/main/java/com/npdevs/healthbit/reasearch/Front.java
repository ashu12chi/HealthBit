package com.npdevs.healthbit.reasearch;

import com.npdevs.healthbit.contracts.Processor;
import com.npdevs.healthbit.contracts.SignUp;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;

public class Front extends JFrame {

    private JPanel contentPane;
    private String doctor_key;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
    private String address_signup = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    /**
     * Create the frame.
     */
    public Front(String doctor_key1) {
        doctor_key = doctor_key1;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 720, 522);
        contentPane = new JPanel();
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
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Medicine:");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNewLabel.setBounds(69, 54, 91, 25);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField.setBounds(216, 54, 340, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblDisease = new JLabel("Disease:");
        lblDisease.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblDisease.setBounds(69, 116, 91, 25);
        contentPane.add(lblDisease);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_1.setColumns(10);
        textField_1.setBounds(216, 115, 340, 26);
        contentPane.add(textField_1);

        JLabel lblCountry = new JLabel("Country:");
        lblCountry.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCountry.setBounds(69, 174, 91, 25);
        contentPane.add(lblCountry);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_2.setColumns(10);
        textField_2.setBounds(216, 174, 340, 26);
        contentPane.add(textField_2);

        JLabel lblState = new JLabel("State:");
        lblState.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblState.setBounds(69, 231, 91, 25);
        contentPane.add(lblState);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_3.setColumns(10);
        textField_3.setBounds(216, 230, 340, 26);
        contentPane.add(textField_3);

        JLabel lblDistrict = new JLabel("District:");
        lblDistrict.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblDistrict.setBounds(69, 288, 91, 25);
        contentPane.add(lblDistrict);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_4.setColumns(10);
        textField_4.setBounds(216, 287, 340, 26);
        contentPane.add(textField_4);

        JLabel lblCity = new JLabel("City:");
        lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCity.setBounds(69, 350, 91, 25);
        contentPane.add(lblCity);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_5.setColumns(10);
        textField_5.setBounds(216, 349, 340, 26);
        contentPane.add(textField_5);

        JLabel lblLocality = new JLabel("Locality:");
        lblLocality.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblLocality.setBounds(69, 408, 91, 25);
        contentPane.add(lblLocality);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField_6.setColumns(10);
        textField_6.setBounds(216, 407, 340, 26);
        contentPane.add(textField_6);

        JButton btnNewButton = new JButton("Generate");
        btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnNewButton.setBounds(568, 231, 122, 25);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medicine = textField.getText().trim();
                String disease = textField_1.getText().trim();
                String country = textField_2.getText().trim();
                String state = textField_3.getText().trim();
                String district = textField_4.getText().trim();
                String city = textField_5.getText().trim();
                String locality = textField_6.getText().trim();
                Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
                Credentials credentials = Credentials.create(doctor_key);
                Processor processor = Processor.load(address_processor,web3j,credentials,GAS_PRICE,GAS_LIMIT);
                SignUp signUp = SignUp.load(address_signup,web3j,credentials,GAS_PRICE,GAS_LIMIT);
                try {
                    int count = processor.presCount().send().intValue();
                    boolean ar[] = new boolean[count];
                    for(int i=0;i<count;i++)
                        ar[i] = true;
                    if(!medicine.equals("")) {
                        for(int i=0;i<count;i++) {
                            String m1 = processor.getMedicine(BigInteger.valueOf(i)).send();
                            if(!m1.contains(medicine)) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!disease.equals("")) {
                        for(int i=0;i<count;i++) {
                            String m2 = processor.getDiseases(BigInteger.valueOf(i)).send();
                            if(!m2.contains(disease)) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!country.equals("")) {
                        for(int i=0;i<count;i++) {
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            if(!country.equals(m4.getValue1())) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!state.equals("")) {
                        for(int i=0;i<count;i++) {
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            if(!state.equals(m4.getValue2())) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!district.equals("")) {
                        for(int i=0;i<count;i++) {
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            if(!district.equals(m4.getValue3())) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!city.equals("")) {
                        for(int i=0;i<count;i++) {
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            if(!city.equals(m4.getValue4())) {
                                ar[i] = false;
                            }
                        }
                    }

                    if(!locality.equals("")) {
                        for(int i=0;i<count;i++) {
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            if(!locality.equals(m4.getValue5())) {
                                ar[i] = false;
                            }
                        }
                    }

                    String fileString = "";
                    for(int i=0;i<count;i++) {
                        if(ar[i]) {
                            String m1 = processor.getMedicine(BigInteger.valueOf(i)).send();
                            String m2 = processor.getDiseases(BigInteger.valueOf(i)).send();
                            Tuple4<String, String, String, BigInteger> m3 = processor.getPrescription(BigInteger.valueOf(i)).send();
                            String id = m3.getValue2();
                            Tuple6<String, String, String, String, String, String> m4 = signUp.getUserDetails(id).send();
                            String final1 = m1 + " " + m2 + " " + m4.getValue1() + " " + m4.getValue2() + " " + m4.getValue3() + " " + m4.getValue4() + " " + m4.getValue5();
                            fileString = final1 + "\n";
                        }
                    }

                    FileWriter fileWriter = new FileWriter(new File("dataset.txt"));
                    fileWriter.write(fileString);
                    fileWriter.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

