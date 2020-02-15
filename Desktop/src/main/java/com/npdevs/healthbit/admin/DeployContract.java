package com.npdevs.healthbit.admin;

import com.npdevs.healthbit.contracts.Processor;
import com.npdevs.healthbit.contracts.SignUp;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;

import javax.swing.*;
import java.math.BigInteger;

public class DeployContract {

    private static SignUp signUp;
    private static Processor processor;
    private static String address;
    private static String address1;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private final static String admin_key = "89888a531c4d86fc1d62652614e4aef5ccc7ba2409b4f47fa973607cdc975ac0";

    public static void main(String args[]) {
        Web3j web3j = Web3j.build(new HttpService("HTTP://192.168.43.185:8545"));
        Credentials credentials = Credentials.create(admin_key);
        try {
            ContractGasProvider contractGasProvider = new ContractGasProvider() {
                @Override
                public BigInteger getGasPrice(String s) {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasPrice() {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasLimit(String s) {
                    return GAS_LIMIT;
                }

                @Override
                public BigInteger getGasLimit() {
                    return GAS_LIMIT;
                }
            };
            signUp = SignUp.deploy(web3j, credentials, contractGasProvider).send();
            address = signUp.getContractAddress();
            processor = Processor.deploy(web3j, credentials, contractGasProvider,address).send();
            address1 = processor.getContractAddress();
            System.out.println(address);
            System.out.println(address1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
