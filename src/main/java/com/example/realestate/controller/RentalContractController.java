package com.example.realestate.controller;

// REST-контроллер для взаимодействия с клиентом
import com.example.realestate.model.RentalContract;
import com.example.realestate.service.RentalContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class RentalContractController {

    private final RentalContractService rentalContractService;

    public RentalContractController(RentalContractService rentalContractService) {
        this.rentalContractService = rentalContractService;
    }

    @GetMapping
    public List<RentalContract> getAllContracts() {
        return rentalContractService.getAllContracts();
    }

    @PostMapping
    public RentalContract createContract(@RequestBody RentalContract contract) {
        return rentalContractService.createContract(contract);
    }
}
