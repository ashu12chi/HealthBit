package com.npdevs.healthbit.admin;

import com.npdevs.healthbit.contracts.SignUp;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Authorize extends JPanel implements ListSelectionListener {

    JButton jButton,jButton_1;
    DefaultListModel listModel;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private String address_signup = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
    private final static String admin_key = "89888a531c4d86fc1d62652614e4aef5ccc7ba2409b4f47fa973607cdc975ac0";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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

                JComponent jComponent = new Authorize();
                jComponent.setOpaque(true);
                jFrame.setContentPane(jComponent);

                jFrame.setSize(600,600);
                jFrame.setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     */
    public Authorize() {

        super(null);
        listModel = new DefaultListModel();
        Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(admin_key);
        SignUp signUp = SignUp.load(address_signup,web3j,credentials,GAS_PRICE,GAS_LIMIT);
        try {
            int authorize_list_count = signUp.authorize_list_count().send().intValue();
            for(int i=0;i<authorize_list_count;i++)
            {
                String address = signUp.authorize_list(BigInteger.valueOf(i)).send();
                String name = signUp.getAuthorizeDetails(address).send();
                if(signUp.isAuthorized(address,name).send().equals(BigInteger.valueOf(0)))
                {
                    listModel.addElement(address);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(5,5,580,500);
        add(scrollPane);

        jButton = new JButton("Authorize");
        jButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jButton.setBounds(100,510,150,40);
        add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String address = (String) listModel.get(index);
                String name = null;
                try {
                    name = signUp.getAuthorizeDetails(address).send();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    signUp.authorize(address,name).send();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                listModel.remove(index);
                index--;
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        });

        jButton_1 = new JButton("Reject");
        jButton_1.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jButton_1.setBounds(350,510,150,40);
        add(jButton_1);

        jButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String address = (String) listModel.get(index);
                String name = null;
                try {
                    name = signUp.getAuthorizeDetails(address).send();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    signUp.reject(address,name).send();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                listModel.remove(index);
                index--;
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
