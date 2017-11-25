package com.kikia.itacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.repository.InstitutionRepository;

@Service
public class InstitutionServiceImpl implements InstitutionService {

	private InstitutionRepository institutionRepository;

	@Autowired
	public void setInstitutionRepository(InstitutionRepository institutionRepository) {
		this.institutionRepository = institutionRepository;
	}

	@Override
	public Iterable<Institution> listAllInstitutions() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution getInstitution(Long Id) {
		return institutionRepository.findOne(Id);
	}

	@Override
	public int getNUmberOfServices(Institution institution) {
		return institution.getServices().size();
	}

	@Override
	public Institution saveInstitution(Institution institution) {

		Institution institutionLocal = institution;
		if (institutionLocal != null)
			return institutionRepository.save(institution);
		return institutionLocal;
	}

	@Override
	public void deleteInstitution(Long Id) {
		institutionRepository.delete(Id);
	}

}
