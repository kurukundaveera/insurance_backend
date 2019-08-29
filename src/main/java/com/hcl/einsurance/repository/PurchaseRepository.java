package com.hcl.einsurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	Optional<Purchase> findByPolicyIdAndPolicyHolderId(Integer policyId, Integer policyHolderId);

}
