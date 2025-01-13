package com.example.realestate.service;

// Работа с Ethereum, транзакциями и контрактами
import com.example.realestate.model.RentalContract;
import com.example.realestate.repository.RentalContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalContractService {

    private final RentalContractRepository rentalContractRepository;
    private final BlockchainService blockchainService;

    public RentalContractService(RentalContractRepository rentalContractRepository, BlockchainService blockchainService) {
        this.rentalContractRepository = rentalContractRepository;
        this.blockchainService = blockchainService;
    }

    public List<RentalContract> getAllContracts() {
        return rentalContractRepository.findAll();
    }

    public RentalContract createContract(RentalContract contract) {
        return rentalContractRepository.save(contract);
    }
    public RentalContract createAndDeployContract(RentalContract contract) throws Exception {
        // Сохранение в БД
        RentalContract savedContract = rentalContractRepository.save(contract);

        // Деплой в блокчейн
        blockchainService.createRentalContract(
                contract.getTenantAddress(),
                contract.getAmount().toBigInteger(),
                contract.getStartDate(),
                contract.getEndDate()
        );

        return savedContract;
    }
}