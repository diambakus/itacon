package com.kikia.itacon.services;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.OfferedService;
import com.kikia.itacon.repository.OfferedServiceRepository;
import com.kikia.itacon.utils.CodeGenerator;

@Service
public class OfferedServiceServiceImpl implements OfferedServiceService {

	private OfferedServiceRepository offeredServiceRepository;
	private CodeGenerator codeGeneratorSingleton;
	private final String PREFIX = "SV";

	@Autowired
	public OfferedServiceServiceImpl(OfferedServiceRepository offeredServiceRepository) {
		this.offeredServiceRepository = offeredServiceRepository;
		codeGeneratorSingleton = CodeGenerator.getInstance();
		codeGeneratorSingleton.initAlphanumericGenerator(6, ThreadLocalRandom.current());
	}

	@Override
	public Iterable<OfferedService> listAllOfferedServices() {
		return offeredServiceRepository.findAll();
	}

	@Override
	public Iterable<OfferedService> listAllOfferedServicesByInstitution(Institution institution) {
		Iterable<OfferedService> offeredServices = null;
		if ((institution != null) && (institution instanceof Institution))
			offeredServices = institution.getOfferedServices();
		return offeredServices;
	}

	@Override
	public OfferedService getOfferedServicebyId(Long id) {
		return offeredServiceRepository.getOne(id);
	}

	@Override
	public OfferedService saveOfferedService(OfferedService offeredService) {
		offeredService.setCode(PREFIX + codeGeneratorSingleton.nextString());
		return offeredServiceRepository.save(offeredService);
	}

	@Override
	public void deleteOfferedService(Long id) {
		offeredServiceRepository.delete(id);
	}
}
