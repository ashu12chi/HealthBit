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
import org.web3j.tuples.generated.Tuple7;
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
    private static final String BINARY = "608060405234801561001057600080fd5b506040516112213803806112218339818101604052602081101561003357600080fd5b5051600080546001600160a01b03199081163317825560028290556003829055600491909155600180546001600160a01b03909316928216831790556008805490911690911790556111978061008a6000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063a6f27faf116100a2578063ddde624b11610071578063ddde624b146105a0578063dde0aa5f14610644578063e1aef1e314610661578063ece2182a1461067e578063fad404951461068657610116565b8063a6f27faf1461040f578063b938935f1461042c578063cfeb350214610557578063d213f8ac1461058357610116565b80636af1f5c9116100e95780636af1f5c9146102b05780636ee6aece146102f75780636f16af10146102ff5780639cd7a24314610351578063a67422651461037d57610116565b80634b6dff0d1461011b578063521a6e94146101495780635df907b314610163578063649bb3ee14610293575b600080fd5b6101476004803603604081101561013157600080fd5b50803590602001356001600160a01b03166106a9565b005b61015161072b565b60408051918252519081900360200190f35b6101476004803603606081101561017957600080fd5b81359190810190604081016020820135600160201b81111561019a57600080fd5b8201836020820111156101ac57600080fd5b803590602001918460018302840111600160201b831117156101cd57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561021f57600080fd5b82018360208201111561023157600080fd5b803590602001918460018302840111600160201b8311171561025257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610731945050505050565b610147600480360360208110156102a957600080fd5b5035610791565b6102cd600480360360208110156102c657600080fd5b50356107a7565b604080516001600160a01b0394851681529290931660208301528183015290519081900360600190f35b6101516107d6565b61031c6004803603602081101561031557600080fd5b50356107dc565b604080516001600160a01b03958616815293851660208501529190931682820152606082019290925290519081900360800190f35b6101476004803603604081101561036757600080fd5b506001600160a01b038135169060200135610819565b61039a6004803603602081101561039357600080fd5b5035610902565b6040805160208082528351818301528351919283929083019185019080838360005b838110156103d45781810151838201526020016103bc565b50505050905090810190601f1680156104015780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101476004803603602081101561042557600080fd5b50356109ba565b6104496004803603602081101561044257600080fd5b50356109d2565b604080519081018690526001600160a01b0380861660608301528481166080830152831660a082015260c0810182905260e0808252885190820152875181906020808301916101008401918c019080838360005b838110156104b557818101518382015260200161049d565b50505050905090810190601f1680156104e25780820380516001836020036101000a031916815260200191505b5083810382528951815289516020918201918b019080838360005b838110156105155781810151838201526020016104fd565b50505050905090810190601f1680156105425780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b6101476004803603604081101561056d57600080fd5b506001600160a01b038135169060200135610b40565b61039a6004803603602081101561059957600080fd5b5035610bbf565b610147600480360360208110156105b657600080fd5b810190602081018135600160201b8111156105d057600080fd5b8201836020820111156105e257600080fd5b803590602001918460018302840111600160201b8311171561060357600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610c29945050505050565b6102cd6004803603602081101561065a57600080fd5b5035610f9f565b6102cd6004803603602081101561067757600080fd5b5035610fcd565b610151610ffb565b6101476004803603604081101561069c57600080fd5b5080359060200135611001565b600082815260066020819052604090912001546001146106fa5760405162461bcd60e51b815260040180806020018281038252603981526020018061112a6039913960400191505060405180910390fd5b60009182526006602052604090912060030180546001600160a01b0319166001600160a01b03909216919091179055565b60035481565b6000838152600660209081526040909120835161075092850190611016565b506000838152600660209081526040909120825161077692600190920191840190611016565b50505060009081526006602081905260409091206001910155565b6000908152600560205260409020600290810155565b6000908152600560205260409020805460018201546002909201546001600160a01b0391821693919092169190565b60045481565b600090815260066020819052604090912060038101546004820154600583015492909301546001600160a01b039182169493821693919092169190565b610821611094565b42603c8302016040808301919091526001600160a01b03841660a0830152336080830152600060c08301819052600254815260066020908152919020825180518493610871928492910190611016565b50602082810151805161088a9260018501920190611016565b50604082015160028083019190915560608301516003830180546001600160a01b039283166001600160a01b031991821617909155608085015160048501805491841691831691909117905560a085015160058501805491909316911617905560c09092015160069091015580546001019055505050565b6060600660008381526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ae5780601f10610983576101008083540402835291602001916109ae565b820191906000526020600020905b81548152906001019060200180831161099157829003601f168201915b50505050509050919050565b60009081526006602081905260409091206002910155565b60066020908152600091825260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452909291839190830182828015610a695780601f10610a3e57610100808354040283529160200191610a69565b820191906000526020600020905b815481529060010190602001808311610a4c57829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b075780601f10610adc57610100808354040283529160200191610b07565b820191906000526020600020905b815481529060010190602001808311610aea57829003601f168201915b5050506002840154600385015460048601546005870154600690970154959692956001600160a01b039283169550908216935091169087565b610b486110ec565b603c91909102420160408083019182526001600160a01b0393841660208085019182523385526003805460009081526007909252929020935184546001600160a01b031990811691871691909117855590516001808601805490931691909616179055905160029092019190915580549091019055565b60008181526006602090815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156109ae5780601f10610983576101008083540402835291602001916109ae565b6008546040805163a4a1e26360e01b815290516000926001600160a01b03169163a4a1e263916004808301926020929190829003018186803b158015610c6e57600080fd5b505afa158015610c82573d6000803e3d6000fd5b505050506040513d6020811015610c9857600080fd5b5051905060005b81811015610f9a57826040516020018082805190602001908083835b60208310610cda5780518252601f199092019160209182019101610cbb565b51815160209384036101000a60001901801990921691161790526040805192909401828103601f190183528085528251929091019190912060085463c2c379e960e01b83526004830188905293519095506001600160a01b03909316935063c2c379e99260248083019350600092829003018186803b158015610d5c57600080fd5b505afa158015610d70573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610d9957600080fd5b8101908080516040519392919084600160201b821115610db857600080fd5b908301906020820185811115610dcd57600080fd5b8251600160201b811182820188101715610de657600080fd5b82525081516020918201929091019080838360005b83811015610e13578181015183820152602001610dfb565b50505050905090810190601f168015610e405780820380516001836020036101000a031916815260200191505b506040525050506040516020018082805190602001908083835b60208310610e795780518252601f199092019160209182019101610e5a565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001201415610f925760048054600090815260056020908152604080832060020192909255600854825163486cbb7160e11b815293840185905291516001600160a01b03909216926390d976e29260248083019392829003018186803b158015610f1a57600080fd5b505afa158015610f2e573d6000803e3d6000fd5b505050506040513d6020811015610f4457600080fd5b50516004805460009081526005602052604080822080546001600160a01b039095166001600160a01b0319958616179055825482529020600190810180549093163317909255805490910190555b600101610c9f565b505050565b6005602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b6007602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b60025481565b60009182526005602052604090912060020155565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061105757805160ff1916838001178555611084565b82800160010185558215611084579182015b82811115611084578251825591602001919060010190611069565b5061109092915061110c565b5090565b6040518060e0016040528060608152602001606081526020016000815260200160006001600160a01b0316815260200160006001600160a01b0316815260200160006001600160a01b03168152602001600081525090565b604080516060810182526000808252602082018190529181019190915290565b61112691905b808211156110905760008155600101611112565b9056fe596f7520617265206e6f74207072657363726962656420627920646f63746f72206f722068617665206d65646963696e6520616c7265616479a265627a7a7231582062db06df5653ac3a8afdaac9d96ce6ad784902545deb4451c72a54fe494957ae64736f6c634300050c0032";

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

    public static final String FUNC_PRESGLOBAL = "presGlobal";

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

    public RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>> presGlobal(BigInteger param0) {
        final Function function = new Function(FUNC_PRESGLOBAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>(
                new Callable<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, BigInteger, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, BigInteger, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
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
