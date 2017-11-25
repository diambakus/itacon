package com.kikia.itacon.services;

import com.kikia.itacon.domain.Institution;

public interface InstitutionService {

	Iterable<Institution> listAllInstitutions();

	Institution getInstitution(Long Id);

	Institution saveInstitution(Institution institution);

	void deleteInstitution(Long Id);

	int getNUmberOfServices(Institution institution);
}
