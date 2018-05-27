package com.kikia.itacon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kikia.itacon.domain.OfferedService;

public interface OfferedServiceRepository extends JpaRepository<OfferedService, Long>{

	@Query("select offeredService from OfferedService offeredService where offeredService.name = ?1")
	OfferedService findOfferedServiceByName(String name);
	@Query("select offeredService from OfferedService offeredService where offeredService.code = ?1")
	OfferedService findOfferedServiceByCode(String code);
}
