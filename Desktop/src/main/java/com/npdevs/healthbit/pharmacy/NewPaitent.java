package com.npdevs.healthbit.pharmacy;

import com.npdevs.healthbit.contracts.Processor;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class NewPaitent extends JPanel implements ListSelectionListener {

    Web3j web3j;
    String doctor_key;
    DefaultListModel listModel;
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    /**
     * Create the frame.
     */
    public NewPaitent(String doctor_key1) {
        super(null);
        doctor_key = doctor_key1;
        listModel = new DefaultListModel();
        Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(doctor_key);
        String doctor_address = credentials.getAddress();

        Processor processor = Processor.load(address_processor,web3j,credentials,GAS_PRICE,GAS_LIMIT);
        try {
            int presCount = processor.presCount().send().intValue();
            //System.out.println(doctor_address);
            for(int i=0;i<presCount;i++) {
                Tuple4<String, String, String, BigInteger> pres = processor.getPrescription(BigInteger.valueOf(i)).send();
                if(pres.getValue4().equals(BigInteger.ONE) && pres.getValue1().equals(doctor_address))
                {
                    listModel.addElement(i + " " +pres.getValue2());
                    //System.out.println(i);
                }
                //System.out.println(pres.getValue4() + " " +pres.getValue3() +" " + pres.getValue1());
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(5,5,570,400);
        add(scrollPane);

        JLabel jTextField_2 = new JLabel();
        jTextField_2.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jTextField_2.setBounds(110,490,465,25);
        add(jTextField_2);

        JLabel jLabel_2 = new JLabel();
        jLabel_2.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jLabel_2.setBounds(5,490,100,25);
        jLabel_2.setText("Medicine");
        add(jLabel_2);

        JButton jButton = new JButton("Give Medicine");
        jButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jButton.setBounds(50,525,200,30);
        add(jButton);

        JButton jButton1 = new JButton("Get Medicine");
        jButton1.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jButton1.setBounds(300,525,200,30);
        add(jButton1);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String item = (String) listModel.get(index);
                try {
                    processor.giveMedicines(BigInteger.valueOf(Integer.parseInt(item.substring(0,item.indexOf(' '))))).send();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                listModel.remove(index);
                index--;
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String item = (String) listModel.get(index);
                try {
                    String medicine = processor.getMedicine(BigInteger.valueOf(Integer.parseInt(item.substring(0,item.indexOf(' '))))).send();
                    jTextField_2.setText(medicine);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}

