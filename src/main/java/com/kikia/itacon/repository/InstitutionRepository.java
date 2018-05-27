package com.kikia.itacon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kikia.itacon.domain.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

	@Query("select institution from Institution institution where institution.name = ?1")
	Institution findInstitutionByName(String name);
	@Query("select institution from Institution institution where institution.code = ?1")
	Institution findInstitutionByCode(String code);
}
