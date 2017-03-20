package com.kikia.itacon.services;

import com.kikia.itacon.entities.PublicInstitution;

public interface PublicInstitutionService {

	Iterable<PublicInstitution> listAllPublicInstitutions();

	PublicInstitution getPublicInstitution(Long Id);

	PublicInstitution savePublicInstitution(PublicInstitution publicInstitution);

	void deletePublicInstitution(Long Id);
}
