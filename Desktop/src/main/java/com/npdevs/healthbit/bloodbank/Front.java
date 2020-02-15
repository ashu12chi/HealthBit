package com.npdevs.healthbit.bloodbank;

import com.npdevs.healthbit.doctor.NewPaitent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JButton btnNewButton = new JButton("Request Blood");
        btnNewButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnNewButton.setBounds(247, 155, 205, 25);
        contentPane.add(btnNewButton);

        JButton btnNewPaitent = new JButton("Pending");
        btnNewPaitent.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        btnNewPaitent.setBounds(247, 216, 205, 25);
        contentPane.add(btnNewPaitent);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request(doctor_key);
                request.setVisible(true);
                request.setResizable(false);
                request.setTitle("HealthBit");
            }
        });

        btnNewPaitent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        JFrame jFrame = new JFrame();
                        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        }catch (Exception e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }

                        JComponent jComponent = new Pending(doctor_key);
                        jComponent.setOpaque(true);
                        jFrame.setContentPane(jComponent);

                        jFrame.setSize(600,620);
                        jFrame.setVisible(true);
                    }
                });
            }
        });

    }
}

