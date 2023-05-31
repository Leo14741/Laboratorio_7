package com.example.lab7.Repositorys;

import com.example.lab7.Entitys.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Integer> {
}