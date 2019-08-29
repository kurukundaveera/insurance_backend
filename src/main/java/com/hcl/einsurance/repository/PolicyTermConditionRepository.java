package com.hcl.einsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.entity.PolicyTermCondition;

@Repository
public interface PolicyTermConditionRepository extends JpaRepository<PolicyTermCondition, Integer>{

}
