package com.kikia.itacon.services;

import com.kikia.itacon.domain.Institution;

public interface InstitutionService {

	public static final String PREFIX = "IN";
	
	Iterable<Institution> listAllInstitutions();

	Institution getInstitution(Long Id);

	Institution saveInstitution(Institution institution);

	void deleteInstitution(Long Id);

	int getNumberOfOfferedServices(Institution institution);
	
	Institution findInstitutionByName(String name);
	
	Institution findInstitutionByCode(String code);
}
