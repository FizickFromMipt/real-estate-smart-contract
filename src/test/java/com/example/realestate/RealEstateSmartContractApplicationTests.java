package com.example.realestate;

import com.example.realestate.model.RentalContract;
import com.example.realestate.repository.RentalContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RealEstateSmartContractApplicationTests {

    @Autowired
    private RentalContractRepository rentalContractRepository;

    @Test
    public void testCreateAndRetrieveContract() {
        RentalContract contract = new RentalContract();
        contract.setLandlordAddress("0x1234");
        contract.setTenantAddress("0x5678");
        contract.setAmount(BigDecimal.valueOf(1000));
        contract.setStartDate("2025-01-01");
        contract.setEndDate("2025-12-31");

        RentalContract savedContract = rentalContractRepository.save(contract);
        assertNotNull(savedContract.getId());

        RentalContract retrievedContract = rentalContractRepository.findById(savedContract.getId()).orElse(null);
        assertNotNull(retrievedContract);
        assertEquals("0x1234", retrievedContract.getLandlordAddress());
    }

    @Test
    public void testBlockchainIntegration() throws Exception {
        String tenantAddress = "0x5678";
        BigDecimal amount = BigDecimal.valueOf(1000);
        String startDate = "2025-01-01";
        String endDate = "2025-12-31";

        TransactionReceipt receipt = blockchainService.createRentalContract(
                tenantAddress, amount.toBigInteger(), startDate, endDate);

        assertNotNull(receipt.getTransactionHash());
    }

}