package com.npdevs.healthbit.contracts;

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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
	public static final String FUNC_ACCEPTEDBLOODREQUEST = "acceptedBloodRequest";
	public static final String FUNC_BLOOD_REQUEST_COUNT = "blood_request_count";
	public static final String FUNC_BLOOD_REQUEST_GLOBAL = "blood_request_global";
	public static final String FUNC_DIAGNOSEPATIENT = "diagnosePatient";
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
	public static final String FUNC_RESPONDBLOODREQUEST = "respondBloodRequest";
	private static final String BINARY = "608060405234801561001057600080fd5b506040516111d53803806111d58339818101604052602081101561003357600080fd5b5051600080546001600160a01b03199081163317825560028290556003829055600491909155600180546001600160a01b039093169282168317905560088054909116909117905561114b8061008a6000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c8063cfeb350211610097578063dde0aa5f11610066578063dde0aa5f146105c6578063e1aef1e31461060d578063ece2182a1461062a578063fad404951461063257610100565b8063cfeb35021461039e578063d213f8ac146103ca578063d35066c4146103e7578063ddde624b1461052257610100565b80639cd7a243116100d35780639cd7a24314610198578063a6742265146101c4578063a6f27faf14610256578063b938935f1461027357610100565b8063521a6e9414610105578063649bb3ee1461011f5780636ee6aece1461013e5780636f16af1014610146575b600080fd5b61010d610655565b60408051918252519081900360200190f35b61013c6004803603602081101561013557600080fd5b503561065b565b005b61010d610671565b6101636004803603602081101561015c57600080fd5b5035610677565b604080516001600160a01b03958616815293851660208501529190931682820152606082019290925290519081900360800190f35b61013c600480360360408110156101ae57600080fd5b506001600160a01b0381351690602001356106b4565b6101e1600480360360208110156101da57600080fd5b503561079d565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561021b578181015183820152602001610203565b50505050905090810190601f1680156102485780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61013c6004803603602081101561026c57600080fd5b5035610855565b6102906004803603602081101561028957600080fd5b50356108bc565b604080519081018690526001600160a01b0380861660608301528481166080830152831660a082015260c0810182905260e0808252885190820152875181906020808301916101008401918c019080838360005b838110156102fc5781810151838201526020016102e4565b50505050905090810190601f1680156103295780820380516001836020036101000a031916815260200191505b5083810382528951815289516020918201918b019080838360005b8381101561035c578181015183820152602001610344565b50505050905090810190601f1680156103895780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b61013c600480360360408110156103b457600080fd5b506001600160a01b038135169060200135610a2a565b6101e1600480360360208110156103e057600080fd5b5035610aa9565b61013c600480360360808110156103fd57600080fd5b81359190810190604081016020820135600160201b81111561041e57600080fd5b82018360208201111561043057600080fd5b803590602001918460018302840111600160201b8311171561045157600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156104a357600080fd5b8201836020820111156104b557600080fd5b803590602001918460018302840111600160201b831117156104d657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550505090356001600160a01b03169150610b139050565b61013c6004803603602081101561053857600080fd5b810190602081018135600160201b81111561055257600080fd5b82018360208201111561056457600080fd5b803590602001918460018302840111600160201b8311171561058557600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610be8945050505050565b6105e3600480360360208110156105dc57600080fd5b5035610f5e565b604080516001600160a01b0394851681529290931660208301528183015290519081900360600190f35b6105e36004803603602081101561062357600080fd5b5035610f8c565b61010d610fba565b61013c6004803603604081101561064857600080fd5b5080359060200135610fc0565b60035481565b6000908152600560205260409020600290810155565b60045481565b600090815260066020819052604090912060038101546004820154600583015492909301546001600160a01b039182169493821693919092169190565b6106bc610fd5565b42603c8302016040808301919091526001600160a01b03841660a0830152336080830152600060c0830181905260025481526006602090815291902082518051849361070c92849291019061102d565b506020828101518051610725926001850192019061102d565b50604082015160028083019190915560608301516003830180546001600160a01b039283166001600160a01b031991821617909155608085015160048501805491841691831691909117905560a085015160058501805491909316911617905560c09092015160069091015580546001019055505050565b6060600660008381526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108495780601f1061081e57610100808354040283529160200191610849565b820191906000526020600020905b81548152906001019060200180831161082c57829003601f168201915b50505050509050919050565b60008181526006602052604090206002015442116108a45760405162461bcd60e51b815260040180806020018281038252602e8152602001806110e9602e913960400191505060405180910390fd5b60009081526006602081905260409091206002910155565b60066020908152600091825260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084529092918391908301828280156109535780601f1061092857610100808354040283529160200191610953565b820191906000526020600020905b81548152906001019060200180831161093657829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109f15780601f106109c6576101008083540402835291602001916109f1565b820191906000526020600020905b8154815290600101906020018083116109d457829003601f168201915b5050506002840154600385015460048601546005870154600690970154959692956001600160a01b039283169550908216935091169087565b610a326110ab565b603c91909102420160408083019182526001600160a01b0393841660208085019182523385526003805460009081526007909252929020935184546001600160a01b031990811691871691909117855590516001808601805490931691909616179055905160029092019190915580549091019055565b60008181526006602090815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156108495780601f1061081e57610100808354040283529160200191610849565b6000848152600660205260409020600201544211610b625760405162461bcd60e51b815260040180806020018281038252602e8152602001806110e9602e913960400191505060405180910390fd5b60008481526006602090815260409091208451610b819286019061102d565b5060008481526006602090815260409091208351610ba79260019092019185019061102d565b5060009384526006602081905260409094206003810180546001600160a01b0319166001600160a01b03939093169290921790915560019301929092555050565b6008546040805163a4a1e26360e01b815290516000926001600160a01b03169163a4a1e263916004808301926020929190829003018186803b158015610c2d57600080fd5b505afa158015610c41573d6000803e3d6000fd5b505050506040513d6020811015610c5757600080fd5b5051905060005b81811015610f5957826040516020018082805190602001908083835b60208310610c995780518252601f199092019160209182019101610c7a565b51815160209384036101000a60001901801990921691161790526040805192909401828103601f190183528085528251929091019190912060085463c2c379e960e01b83526004830188905293519095506001600160a01b03909316935063c2c379e99260248083019350600092829003018186803b158015610d1b57600080fd5b505afa158015610d2f573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610d5857600080fd5b8101908080516040519392919084600160201b821115610d7757600080fd5b908301906020820185811115610d8c57600080fd5b8251600160201b811182820188101715610da557600080fd5b82525081516020918201929091019080838360005b83811015610dd2578181015183820152602001610dba565b50505050905090810190601f168015610dff5780820380516001836020036101000a031916815260200191505b506040525050506040516020018082805190602001908083835b60208310610e385780518252601f199092019160209182019101610e19565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001201415610f515760048054600090815260056020908152604080832060020192909255600854825163486cbb7160e11b815293840185905291516001600160a01b03909216926390d976e29260248083019392829003018186803b158015610ed957600080fd5b505afa158015610eed573d6000803e3d6000fd5b505050506040513d6020811015610f0357600080fd5b50516004805460009081526005602052604080822080546001600160a01b039095166001600160a01b0319958616179055825482529020600190810180549093163317909255805490910190555b600101610c5e565b505050565b6005602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b6007602052600090815260409020805460018201546002909201546001600160a01b03918216929091169083565b60025481565b60009182526005602052604090912060020155565b6040518060e0016040528060608152602001606081526020016000815260200160006001600160a01b0316815260200160006001600160a01b0316815260200160006001600160a01b03168152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061106e57805160ff191683800117855561109b565b8280016001018555821561109b579182015b8281111561109b578251825591602001919060010190611080565b506110a79291506110cb565b5090565b604080516060810182526000808252602082018190529181019190915290565b6110e591905b808211156110a757600081556001016110d1565b9056fe54696d652070617373656420617761792c20796f7520617265206e6f206c6f6e67657220617574686f72697a6564a265627a7a72315820015f36fea7353b110331290fb8ca8f961d82d09eb95cc5b4bc1c31cd7dd8b31864736f6c634300050c0032";

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
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteCall<Tuple3<String, String, BigInteger>> blood_request_global(BigInteger param0) {
		final Function function = new Function(FUNC_BLOOD_REQUEST_GLOBAL,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
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

	public RemoteCall<TransactionReceipt> diagnosePatient(BigInteger itr, String diseases, String medicines, String id) {
		final Function function = new Function(
				FUNC_DIAGNOSEPATIENT,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr),
						new org.web3j.abi.datatypes.Utf8String(diseases),
						new org.web3j.abi.datatypes.Utf8String(medicines),
						new org.web3j.abi.datatypes.Address(id)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<String> getDiseases(BigInteger itr) {
		final Function function = new Function(FUNC_GETDISEASES,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<String> getMedicine(BigInteger itr) {
		final Function function = new Function(FUNC_GETMEDICINE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<Tuple4<String, String, String, BigInteger>> getPrescription(BigInteger itr) {
		final Function function = new Function(FUNC_GETPRESCRIPTION,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
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
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteCall<Tuple3<String, String, BigInteger>> insuranceGlobal(BigInteger param0) {
		final Function function = new Function(FUNC_INSURANCEGLOBAL,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
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
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>> presGlobal(BigInteger param0) {
		final Function function = new Function(FUNC_PRESGLOBAL,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}, new TypeReference<Utf8String>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
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

	public RemoteCall<TransactionReceipt> respondBloodRequest(BigInteger itr, BigInteger response) {
		final Function function = new Function(
				FUNC_RESPONDBLOODREQUEST,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(itr),
						new org.web3j.abi.datatypes.generated.Uint256(response)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}
}
