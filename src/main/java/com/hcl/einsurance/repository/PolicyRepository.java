package com.hcl.einsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.entity.Policies;

@Repository
public interface PolicyRepository extends JpaRepository<Policies, Integer> {

}
