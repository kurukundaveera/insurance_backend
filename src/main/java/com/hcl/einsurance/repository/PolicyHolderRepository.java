package com.hcl.einsurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.entity.PolicyHolder;

@Repository
public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {
	
	Optional<PolicyHolder> findByMobileNumber(String mobileNumber);

}
