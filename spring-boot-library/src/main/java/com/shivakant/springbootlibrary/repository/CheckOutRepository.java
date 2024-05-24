package com.shivakant.springbootlibrary.repository;


import com.shivakant.springbootlibrary.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckOutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);
    List<Checkout> findBooksByUserEmail(String userEmail);

}
