package com.npdevs.healthbit.comapny;

import com.npdevs.healthbit.contracts.Processor;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.math.BigInteger;

public class Customer extends JPanel implements ListSelectionListener {

    String doctor_key;
    DefaultListModel listModel;
    private String address_processor = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    /**
     * Create the frame.
     */
    public Customer(String doctor_key1) {

        super(null);
        doctor_key = doctor_key1;
        listModel = new DefaultListModel();
        Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(doctor_key);
        String doctor_address = credentials.getAddress();

        Processor processor = Processor.load(address_processor,web3j,credentials,GAS_PRICE,GAS_LIMIT);
        try {
            int presCount = processor.insuranceCount().send().intValue();
            // System.out.println(presCount);
            for(int i=0;i<presCount;i++) {
                Tuple3<String, String, BigInteger> pres = processor.insuranceGlobal(BigInteger.valueOf(i)).send();
                if(pres.getValue2().equals(doctor_address))
                {
                    listModel.addElement(i + " " +pres.getValue1());
                    //System.out.println(i);
                }
                //System.out.println(pres.getValue2() + " " +pres.getValue3() +" " + pres.getValue1());
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(5,5,570,560);
        add(scrollPane);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}

