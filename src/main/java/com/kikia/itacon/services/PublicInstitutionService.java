package com.kikia.itacon.services;

import com.kikia.itacon.domain.PublicInstitution;

public interface PublicInstitutionService {

	Iterable<PublicInstitution> listAllPublicInstitutions();

	PublicInstitution getPublicInstitution(Long Id);

	PublicInstitution savePublicInstitution(PublicInstitution publicInstitution);

	void deletePublicInstitution(Long Id);
}
