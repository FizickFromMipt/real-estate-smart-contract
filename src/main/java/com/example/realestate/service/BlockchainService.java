package com.example.realestate.blockchain;

import com.example.realestate.model.RentalContract;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Service
public class BlockchainService {

    private final Web3j web3j;
    private final Credentials credentials;

    // Контрактный адрес (обновите после деплоя)
    private String contractAddress;

    public BlockchainService(Web3j web3j) {
        this.web3j = web3j;
        this.credentials = Credentials.create("YOUR_PRIVATE_KEY"); // Убедитесь, что ключ безопасен!
    }

    public String deployContract() throws Exception {
        RentalContract contract = RentalContract.deploy(
                web3j, credentials, new DefaultGasProvider()).send();
        this.contractAddress = contract.getContractAddress();
        return contractAddress;
    }

    public TransactionReceipt createRentalContract(
            String tenantAddress, BigInteger amount, String startDate, String endDate) throws Exception {

        RentalContract contract = RentalContract.load(
                contractAddress, web3j, credentials, new DefaultGasProvider());

        return contract.createContract(
                tenantAddress, amount, startDate, endDate).send();
    }

    public RentalContract.Contract getRentalContract(BigInteger id) throws Exception {
        RentalContract contract = RentalContract.load(
                contractAddress, web3j, credentials, new DefaultGasProvider());

        return contract.contracts(id).send();
    }
}
