package com.shop.backend.repository;

import com.shop.backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Set<Invoice> findByCustomerId(Integer customerId);
}
