package com.kikia.itacon.services;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.repository.InstitutionRepository;
import com.kikia.itacon.utils.CodeGenerator;

@Service
public class InstitutionServiceImpl implements InstitutionService {

	private InstitutionRepository institutionRepository;
	private CodeGenerator codeGeneratorSingleton;
	private final String PREFIX = "IN";

	@Autowired
	public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
		this.institutionRepository = institutionRepository;
		this.codeGeneratorSingleton = CodeGenerator.getInstance();
		codeGeneratorSingleton.initAlphanumericGenerator(6, ThreadLocalRandom.current());
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
	public int getNumberOfOfferedServices(Institution institution) {
		return institution.getOfferedServices().size();
	}

	@Override
	public Institution saveInstitution(Institution institution) {

		Institution institutionLocal = institution;
		if (institutionLocal != null) {
			institution.setCode(PREFIX + codeGeneratorSingleton.nextString(institution.getName()));
			return institutionRepository.save(institution);
		}
		return institutionLocal;
	}

	@Override
	public void deleteInstitution(Long Id) {
		institutionRepository.delete(Id);
	}

}
