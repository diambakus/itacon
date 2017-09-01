package com.kikia.itacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.PublicInstitution;
import com.kikia.itacon.repository.PublicInstitutionRepository;

@Service
public class PublicInstitutionServiceImpl implements PublicInstitutionService {

	private PublicInstitutionRepository publicInstitutionRepository;

	@Autowired
	public void setPublicInstitutionRepository(PublicInstitutionRepository publicInstitutionRepository) {
		this.publicInstitutionRepository = publicInstitutionRepository;
	}

	@Override
	public Iterable<PublicInstitution> listAllPublicInstitutions() {
		return publicInstitutionRepository.findAll();
	}

	@Override
	public PublicInstitution getPublicInstitution(Long Id) {
		return publicInstitutionRepository.findOne(Id);
	}

	@Override
	public PublicInstitution savePublicInstitution(PublicInstitution publicInstitution) {

		PublicInstitution publicInstitutionVariable = publicInstitution;

		if (publicInstitutionVariable != null)
			return publicInstitutionRepository.save(publicInstitutionVariable);
		return publicInstitutionVariable;

	}

	@Override
	public void deletePublicInstitution(Long Id) {
		publicInstitutionRepository.delete(Id);
	}

}
