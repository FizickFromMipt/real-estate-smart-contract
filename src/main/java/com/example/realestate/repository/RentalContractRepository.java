package com.example.realestate.repository;

//Репозиторий для работы с базой данных
import com.example.realestate.model.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {
}

