package com.npdevs.healthbit.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Processor extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610f7d380380610f7d8339818101604052602081101561003357600080fd5b5051600080546001600160a01b03199081163317825560028290556003829055600491909155600180546001600160a01b0390931692821683179055600880549091169091179055610ef38061008a6000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c8063a6742265116100a2578063ddde624b11610071578063ddde624b1461046a578063dde0aa5f1461050e578063e1aef1e31461052b578063ece2182a14610548578063fad40495146105505761010b565b8063a674226514610372578063a6f27faf14610404578063cfeb350214610421578063d213f8ac1461044d5761010b565b80636af1f5c9116100de5780636af1f5c9146102a55780636ee6aece146102ec5780636f16af10146102f45780639cd7a243146103465761010b565b80634b6dff0d14610110578063521a6e941461013e5780635df907b314610158578063649bb3ee14610288575b600080fd5b61013c6004803603604081101561012657600080fd5b50803590602001356001600160a01b0316610573565b005b6101466105f5565b60408051918252519081900360200190f35b61013c6004803603606081101561016e57600080fd5b81359190810190604081016020820135600160201b81111561018f57600080fd5b8201836020820111156101a157600080fd5b803590602001918460018302840111600160201b831117156101c257600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561021457600080fd5b82018360208201111561022657600080fd5b803590602001918460018302840111600160201b8311171561024757600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506105fb945050505050565b61013c6004803603602081101561029e57600080fd5b503561065b565b6102c2600480360360208110156102bb57600080fd5b5035610671565b604080516001600160a01b0394851681529290931660208301528183015290519081900360600190f35b6101466106a0565b6103116004803603602081101561030a57600080fd5b50356106a6565b604080516001600160a01b03958616815293851660208501529190931682820152606082019290925290519081900360800190f35b61013c6004803603604081101561035c57600080fd5b506001600160a01b0381351690602001356106e3565b61038f6004803603602081101561038857600080fd5b50356107cc565b6040805160208082528351818301528351919283929083019185019080838360005b838110156103c95781810151838201526020016103b1565b50505050905090810190601f1680156103f65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61013c6004803603602081101561041a57600080fd5b5035610884565b61013c6004803603604081101561043757600080fd5b506001600160a01b03813516906020013561089c565b61038f6004803603602081101561046357600080fd5b503561091b565b61013c6004803603602081101561048057600080fd5b810190602081018135600160201b81111561049a57600080fd5b8201836020820111156104ac57600080fd5b803590602001918460018302840111600160201b831117156104cd57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610985945050505050565b6102c26004803603602081101561052457600080fd5b5035610cfb565b6102c26004803603602081101561054157600080fd5b5035610d29565b610146610d57565b61013c6004803603604081101561056657600080fd5b5080359060200135610d5d565b600082815260066020819052604090912001546001146105c45760405162461bcd60e51b8152600401808060200182810382526039815260200180610e866039913960400191505060405180910390fd5b60009182526006602052604090912060030180546001600160a01b0319166001600160a01b03909216919091179055565b60035481565b6000838152600660209081526040909120835161061a92850190610d72565b506000838152600660209081526040909120825161064092600190920191840190610d72565b50505060009081526006602081905260409091206001910155565b6000908152600560205260409020600290810155565b6000908152600560205260409020805460018201546002909201546001600160a01b0391821693919092169190565b60045481565b600090815260066020819052604090912060038101546004820154600583015492909301546001600160a01b039182169493821693919092169190565b6106eb610df0565b42603c8302016040808301919091526001600160a01b03841660a0830152336080830152600060c0830181905260025481526006602090815291902082518051849361073b928492910190610d72565b5060208281015180516107549260018501920190610d72565b50604082015160028083019190915560608301516003830180546001600160a01b039283166001600160a01b031991821617909155608085015160048501805491841691831691909117905560a085015160058501805491909316911617905560c09092015160069091015580546001019055505050565b6060600660008381526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108785780601f1061084d57610100808354040283529160200191610878565b820191906000526020600020905b81548152906001019060200180831161085b57829003601f168201915b50505050509050919050565b60009081526006602081905260409091206002910155565b6108a4610e48565b603c91909102420160408083019182526001600160a01b0393841660208085019182523385526003805460009081526007909252929020935184546001600160a01b031990811691871691909117855590516001808601805490931691909616179055905160029092019190915580549091019055565b60008181526006602090815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156108785780601f1061084d57610100808354040283529160200191610878565b6008546040805163a4a1e26360e01b815290516000926001600160a01b03169163a4a1e263916004808301926020929190829003018186803b1580156109ca57600080fd5b505afa1580156109de573d6000803e3d6000fd5b505050506040513d60208110156109f457600080fd5b5051905060005b81811015610cf657826040516020018082805190602001908083835b60208310610a365780518252601f199092019160209182019101610a17565b51815160209384036101000a60001901801990921691161790526040805192909401828103601f190183528085528251929091019190912060085463c2c379e960e01b83526004830188905293519095506001600160a01b03909316935063c2c379e99260248083019350600092829003018186803b158015610ab857600080fd5b505afa158015610acc573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610af557600080fd5b8101908080516040519392919084600160201b821115610b1457600080fd5b908301906020820185811115610b2957600080fd5b8251600160201b811182820188101715610b4257600080fd5b82525081516020918201929091019080838360005b83811015610b6f578181015183820152602001610b57565b50505050905090810190601f168015610b9c5780820380516001836020036101000a031916815260200191505b506040525050506040516020018082805190602001908083835b60208310610bd55780518252601f199092019160209182019101610bb6565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001201415610cee5760048054600090815260056020908152604080832060020192909255600854825163486cbb7160e11b815293840185905291516001600160a01b03909216926390d976e29260248083019392829003018186803b158015610c7657600080fd5b505afa158015610c8a573d6000803e3d6000fd5b505050506040513d6020811015610ca057600080fd5b50516004805460009081526005602052604080822080546001600160a01b039095166001600160a01b0319958616179055825482529020600190810180549093163317909255805490910190555b6001016109fb565b505050565b6005602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b6007602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b60025481565b60009182526005602052604090912060020155565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610db357805160ff1916838001178555610de0565b82800160010185558215610de0579182015b82811115610de0578251825591602001919060010190610dc5565b50610dec929150610e68565b5090565b6040518060e0016040528060608152602001606081526020016000815260200160006001600160a01b0316815260200160006001600160a01b0316815260200160006001600160a01b03168152602001600081525090565b604080516060810182526000808252602082018190529181019190915290565b610e8291905b80821115610dec5760008155600101610e6e565b9056fe596f7520617265206e6f74207072657363726962656420627920646f63746f72206f722068617665206d65646963696e6520616c7265616479a265627a7a72315820883b812e73d12c0ea096ead1b73cbff4a2fc579316e0d9e9b54fc75fde51d74c64736f6c634300050c0032";

    public static final String FUNC_ACCEPTEDBLOODREQUEST = "acceptedBloodRequest";

    public static final String FUNC_BLOOD_REQUEST_COUNT = "blood_request_count";

    public static final String FUNC_BLOOD_REQUEST_GLOBAL = "blood_request_global";

    public static final String FUNC_DIAGNOSEPATIENT = "diagnosePatient";

    public static final String FUNC_GETBLOODDETAILS = "getBloodDetails";

    public static final String FUNC_GETDISEASES = "getDiseases";

    public static final String FUNC_GETMEDICINE = "getMedicine";

    public static final String FUNC_GETPRESCRIPTION = "getPrescription";

    public static final String FUNC_GIVEMEDICINES = "giveMedicines";

    public static final String FUNC_INSURANCECOUNT = "insuranceCount";

    public static final String FUNC_INSURANCEGLOBAL = "insuranceGlobal";

    public static final String FUNC_PRESCOUNT = "presCount";

    public static final String FUNC_REQUESTBLOOD = "requestBlood";

    public static final String FUNC_REQUESTCOMPANY = "requestCompany";

    public static final String FUNC_REQUESTDOCTOR = "requestDoctor";

    public static final String FUNC_REQUESTPHARMACY = "requestPharmacy";

    public static final String FUNC_RESPONDBLOODREQUEST = "respondBloodRequest";

    @Deprecated
    protected Processor(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Processor(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Processor(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Processor(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Processor> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(c)));
        return deployRemoteCall(Processor.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Processor> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(c)));
        return deployRemoteCall(Processor.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Processor> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(c)));
        return deployRemoteCall(Processor.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Processor> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(c)));
        return deployRemoteCall(Processor.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> acceptedBloodRequest(BigInteger itr) {
        final Function function = new Function(
                FUNC_ACCEPTEDBLOODREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> blood_request_count() {
        final Function function = new Function(FUNC_BLOOD_REQUEST_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> blood_request_global(BigInteger param0) {
        final Function function = new Function(FUNC_BLOOD_REQUEST_GLOBAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> diagnosePatient(BigInteger itr, String diseases, String medicines) {
        final Function function = new Function(
                FUNC_DIAGNOSEPATIENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr), 
                new org.web3j.abi.datatypes.Utf8String(diseases), 
                new org.web3j.abi.datatypes.Utf8String(medicines)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> getBloodDetails(BigInteger itr) {
        final Function function = new Function(FUNC_GETBLOODDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<String> getDiseases(BigInteger itr) {
        final Function function = new Function(FUNC_GETDISEASES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getMedicine(BigInteger itr) {
        final Function function = new Function(FUNC_GETMEDICINE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple4<String, String, String, BigInteger>> getPrescription(BigInteger itr) {
        final Function function = new Function(FUNC_GETPRESCRIPTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<String, String, String, BigInteger>>(
                new Callable<Tuple4<String, String, String, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> giveMedicines(BigInteger itr) {
        final Function function = new Function(
                FUNC_GIVEMEDICINES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> insuranceCount() {
        final Function function = new Function(FUNC_INSURANCECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> insuranceGlobal(BigInteger param0) {
        final Function function = new Function(FUNC_INSURANCEGLOBAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> presCount() {
        final Function function = new Function(FUNC_PRESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> requestBlood(String _bloodGroup) {
        final Function function = new Function(
                FUNC_REQUESTBLOOD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_bloodGroup)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestCompany(String id_company, BigInteger time) {
        final Function function = new Function(
                FUNC_REQUESTCOMPANY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id_company), 
                new org.web3j.abi.datatypes.generated.Uint256(time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestDoctor(String id_doctor, BigInteger time) {
        final Function function = new Function(
                FUNC_REQUESTDOCTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id_doctor), 
                new org.web3j.abi.datatypes.generated.Uint256(time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestPharmacy(BigInteger itr, String id) {
        final Function function = new Function(
                FUNC_REQUESTPHARMACY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr), 
                new org.web3j.abi.datatypes.Address(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> respondBloodRequest(BigInteger itr, BigInteger response) {
        final Function function = new Function(
                FUNC_RESPONDBLOODREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr), 
                new org.web3j.abi.datatypes.generated.Uint256(response)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Processor load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Processor(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Processor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Processor(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Processor load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Processor(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Processor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Processor(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
