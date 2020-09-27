package com.capg.pbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.pbms.model.LoanRequest;

public interface ILoanRequestRepository extends JpaRepository<LoanRequest, String>{

}
