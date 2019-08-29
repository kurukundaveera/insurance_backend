package com.hcl.einsurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.dto.PolicyResponseDto;
import com.hcl.einsurance.entity.Policies;

@Repository
public interface PolicyRepository extends JpaRepository<Policies, Integer> {

	List<Policies> findByPolicyId(Integer policyId);
	
	@Query("select New com.hcl.einsurance.dto.PolicyResponseDto (p.policyId,l.policyName,l.policyMinAge,l.policyMaxAge,l.policyPrice) from Purchase p,Policies l where p.status='P' and p.policyId=l.policyId group by p.policyId")
	List<PolicyResponseDto> getAllSuggestingPolicies();
}
