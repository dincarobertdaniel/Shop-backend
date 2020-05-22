package com.shop.backend.repository;

import com.shop.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {

    Set<Payment> findByCustomerId(Integer customerId);

}
