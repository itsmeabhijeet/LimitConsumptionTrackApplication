package com.example.demo.service;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.*;

public interface LimitRepository extends JpaRepository<Transaction, Long> {

}
