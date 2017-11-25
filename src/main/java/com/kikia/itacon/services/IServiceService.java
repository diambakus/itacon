package com.kikia.itacon.services;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.Service;

public interface IServiceService {
	
	Iterable<Service> listAllServices();
	Iterable<Service> listAllServicesByInstitution(Institution institution);
	Service getServicebyId(Long Id);
	Service saveService(Service service);
	void deleteService(Long Id);

}
