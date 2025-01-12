package com.example.realestate.service;

// Работа с Ethereum, транзакциями и контрактами
import com.example.realestate.model.RentalContract;
import com.example.realestate.repository.RentalContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalContractService {

    private final RentalContractRepository rentalContractRepository;

    public RentalContractService(RentalContractRepository rentalContractRepository) {
        this.rentalContractRepository = rentalContractRepository;
    }

    public List<RentalContract> getAllContracts() {
        return rentalContractRepository.findAll();
    }

    public RentalContract createContract(RentalContract contract) {
        return rentalContractRepository.save(contract);
    }
}