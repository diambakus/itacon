package com.kikia.itacon.services;

import com.kikia.itacon.domain.PublicInstitution;
import com.kikia.itacon.domain.Service;

public interface IServiceService {
	
	Iterable<Service> listAllServices();
	Iterable<Service> listAllServicesByPublicInstitution(PublicInstitution publicInstitution);
	Service getServicebyId(Long Id);
	Service saveService(Service service);
	void deleteService(Long Id);

}
