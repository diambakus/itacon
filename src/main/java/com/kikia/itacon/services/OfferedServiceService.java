package com.kikia.itacon.services;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.OfferedService;

public interface OfferedServiceService {
	
	Iterable<OfferedService> listAllOfferedServices();
	Iterable<OfferedService> listAllOfferedServicesByInstitution(Institution institution);
	OfferedService getOfferedServicebyId(Long Id);
	OfferedService saveOfferedService(OfferedService offeredService);
	void deleteOfferedService(Long Id);
	OfferedService findOfferedServiceByName(String name);
	OfferedService findOfferedServiceByCode(String code);
}
