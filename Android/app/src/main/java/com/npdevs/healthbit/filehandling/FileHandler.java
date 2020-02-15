package com.npdevs.healthbit.filehandling;

import java.io.File;
import java.io.IOException;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

public class FileHandler {
	IPFS ipfs;

	public FileHandler() {
		ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
	}

	public String addFile(String fileName) {
		NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File((fileName)));
		try {
			MerkleNode addResult = ipfs.add(file).get(0);
			System.out.println(addResult.hash.toBase58());
			return addResult.hash.toBase58();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public byte[] getFile(String hash) {
		Multihash filePointer = Multihash.fromBase58(hash);
		try {
			byte[] fileContents = ipfs.cat(filePointer);
			System.out.println(new String(fileContents));
			return fileContents;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
