package com.npdevs.healthbit.reasearch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Front extends JFrame {

    private JPanel contentPane;
    String doctor_key;

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

        JButton btnNewButton = new JButton("Medicine");
        btnNewButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnNewButton.setBounds(79, 106, 205, 25);
        contentPane.add(btnNewButton);

        JButton btnNewPaitent = new JButton("Disease");
        btnNewPaitent.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnNewPaitent.setBounds(372, 106, 205, 25);
        contentPane.add(btnNewPaitent);

        JButton btnCountry = new JButton("Country");
        btnCountry.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnCountry.setBounds(79, 194, 205, 25);
        contentPane.add(btnCountry);

        JButton btnState = new JButton("State");
        btnState.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnState.setBounds(372, 194, 205, 25);
        contentPane.add(btnState);

        JButton btnDistrict = new JButton("District");
        btnDistrict.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnDistrict.setBounds(79, 293, 205, 25);
        contentPane.add(btnDistrict);

        JButton btnCity = new JButton("City");
        btnCity.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnCity.setBounds(372, 294, 205, 25);
        contentPane.add(btnCity);

        JButton btnLocality = new JButton("Locality");
        btnLocality.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnLocality.setBounds(236, 389, 205, 25);
        contentPane.add(btnLocality);
    }
}
