package com.hcl.einsurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.einsurance.entity.SalientFeatures;

@Repository
public interface SalientFeaturesRepository extends JpaRepository<SalientFeatures, Integer>{

}
