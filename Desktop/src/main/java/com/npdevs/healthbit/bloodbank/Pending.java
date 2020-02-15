package com.npdevs.healthbit.bloodbank;

import com.npdevs.healthbit.contracts.Processor;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Pending extends JPanel implements ListSelectionListener {

    Web3j web3j;
    String doctor_key;
    DefaultListModel listModel;
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public Pending(String doctor_key1) {
        super((null));
        doctor_key = doctor_key1;
        listModel = new DefaultListModel();
        Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(doctor_key);
        String blood_address = credentials.getAddress();

        Processor processor = Processor.load(address_processor,web3j,credentials,GAS_PRICE,GAS_LIMIT);
        try {

            int count = processor.blood_request_count().send().intValue();
            for(int i=0;i<count;i++) {
                Tuple3<String, String, BigInteger> details = processor.getBloodDetails(BigInteger.valueOf(i)).send();
                if(details.getValue2().equals(credentials.getAddress()) && details.getValue3().intValue()==1) {
                    listModel.addElement(i + " " + details.getValue1());
                }
               // System.out.println(details.getValue1()+" "+details.getValue2()+" "+details.getValue3());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(5,5,570,450);
        add(scrollPane);

        JButton jButton = new JButton("Respond");
        jButton.setFont(new Font("Fira Code Retina", Font.PLAIN, 18));
        jButton.setBounds(200,525,150,30);
        add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                String item = (String) listModel.get(index);
                try {
                        processor.acceptedBloodRequest(BigInteger.valueOf(Integer.parseInt(item.substring(0,item.indexOf(' '))))).send();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
